-- Drop existing tables in reverse order of creation to avoid foreign key constraints
DROP TABLE IF EXISTS user_warehouse_association;
DROP TABLE IF EXISTS user_company_role;
DROP TABLE IF EXISTS inventory_transaction;
DROP TABLE IF EXISTS inventory_item;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS warehouse;
DROP TABLE IF EXISTS company;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_mfa_tokens;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS organization_type;
DROP TABLE IF EXISTS country;
CREATE ENUM warehouse_type AS ENUM (
  'DISTRIBUTION_CENTER',
  'REGIONAL_WAREHOUSE',
  'FULFILLMENT_CENTER',
  'COLD_STORAGE',
  'CROSS_DOCKING_FACILITY',
  'BULK_STORAGE_WAREHOUSE',
  'AUTOMATED_WAREHOUSE',
  'RETAIL_WAREHOUSE',
  'MANUFACTURING_WAREHOUSE',
  'PUBLIC_WAREHOUSE',
  'PRIVATE_WAREHOUSE',
  'VIRTUAL_WAREHOUSE'
);
CREATE TYPE warehouse_capacity_unit AS ENUM (
  'METER',
  'SQUARE_METER',
  'CUBIC_METER',
  'KILOGRAM',
  'TONNE',
  'LITER',
  'MILLILITER',
  'PIECE',
  'PALLET',
  'CONTAINER'
);
CREATE TYPE product_unit_measure AS ENUM (
  'PIECES',
  'KILOGRAMS',
  'GRAMS',
  'LITERS',
  'MILLILITERS',
  'BOXES',
  'PACKS',
  'DOZENS',
  'SETS',
  'PAIRS'
);
CREATE TYPE product_weight_unit AS ENUM ('KILOGRAMS', 'GRAMS', 'POUNDS', 'OUNCES');
CREATE TYPE product_dimension_unit AS ENUM ('CENTIMETERS', 'METERS', 'INCHES', 'FEET');
CREATE TYPE product_type AS ENUM (
  'GOODS',
  'SERVICES',
  'DIGITAL',
  'SUBSCRIPTION',
  'BUNDLE',
  'OTHER'
) -- Create lookup tables first as they have no dependencies
-- Table for countries
CREATE TABLE country (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE
);
-- Table for organization types
CREATE TABLE organization_type (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE
);
-- Table for user roles
CREATE TABLE role (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE,
  description VARCHAR(255) NOT NULL
);
-- Table for users
CREATE TABLE users (
  user_uuid UUID PRIMARY KEY,
  username VARCHAR(50) NOT NULL UNIQUE,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  phone_number VARCHAR(15) NOT NULL UNIQUE,
  hashed_password VARCHAR(255) NOT NULL,
  is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
  is_active BOOLEAN NOT NULL DEFAULT TRUE,
  last_login TIMESTAMPTZ,
  created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
  company_company_uuid UUID -- This will be a foreign key to the company table
);
-- Table for companies
CREATE TABLE company (
  company_uuid UUID PRIMARY KEY,
  name VARCHAR(100) NOT NULL UNIQUE,
  registration_number VARCHAR(100) NOT NULL UNIQUE,
  tax_number VARCHAR(50) NOT NULL UNIQUE,
  fk_user_uuid UUID NOT NULL,
  country_id INT NOT NULL,
  fk_organization_type_id INT NOT NULL,
  FOREIGN KEY (fk_user_uuid) REFERENCES users (user_uuid),
  FOREIGN KEY (country_id) REFERENCES country (id),
  FOREIGN KEY (fk_organization_type_id) REFERENCES organization_type (id)
);
-- Now, add the foreign key from users to company
ALTER TABLE users
ADD CONSTRAINT fk_company FOREIGN KEY (company_company_uuid) REFERENCES company (company_uuid);
-- Table for product categories
CREATE TABLE product_category (
  product_category_id SERIAL PRIMARY KEY,
  fk_company_uuid UUID NOT NULL,
  product_category_name VARCHAR(50) NOT NULL UNIQUE,
  productCategoryCode VARCHAR(50) NOT NULL,
  description TEXT NOT NULL,
  is_active BOOLEAN NOT NULL DEFAULT TRUE,
  created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (fk_company_uuid) REFERENCES company (company_uuid)
);
-- Table for warehouses
CREATE TABLE warehouse (
  warehouse_uuid UUID PRIMARY KEY,
  fk_company_uuid UUID NOT NULL,
  warehouse_name VARCHAR(100) NOT NULL UNIQUE,
  warehouse_code VARCHAR(50) NOT NULL,
  description TEXT NOT NULL,
  address_line_1 VARCHAR(255) NOT NULL,
  address_line_2 VARCHAR(255),
  city VARCHAR(100) NOT NULL,
  state_province VARCHAR(100) NOT NULL,
  postal_code VARCHAR(20) NOT NULL,
  country_id INT NOT NULL,
  warehouse_type warehouse_type NOT NULL,
  is_active BOOLEAN NOT NULL DEFAULT TRUE,
  capacity_limit DECIMAL(10, 2),
  capacity_unit warehouse_capacity_unit NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by_user_uuid UUID,
  updated_by_user_uuid UUID,
  FOREIGN KEY (fk_company_uuid) REFERENCES company (company_uuid),
  FOREIGN KEY (country_id) REFERENCES country (id),
  FOREIGN KEY (created_by_user_uuid) REFERENCES users (user_uuid),
  FOREIGN KEY (updated_by_user_uuid) REFERENCES users (user_uuid)
);
-- Table for products
CREATE TABLE product (
  product_uuid UUID PRIMARY KEY,
  fk_company_uuid UUID NOT NULL,
  fk_product_category_id INT NOT NULL,
  sku VARCHAR(100) NOT NULL UNIQUE,
  product_name VARCHAR(100) NOT NULL,
  product_description TEXT NOT NULL,
  brand VARCHAR(100) NOT NULL,
  model VARCHAR(100) NOT NULL,
  product_type product_type NOT NULL,
  unit_measure product_unit_measure,
  base_cost DECIMAL(15, 2) DEFAULT 0.00,
  selling_price DECIMAL(15, 2) DEFAULT 0.00,
  weight DECIMAL(10, 3),
  weight_unit product_weight_unit NOT NULL,
  dimensions_length DECIMAL(10, 2),
  dimensions_width DECIMAL(10, 2),
  dimensions_height DECIMAL(10, 2),
  dimension_unit product_dimension_unit NOT NULL,
  barcode VARCHAR(100),
  qr_code VARCHAR(255),
  minimum_stock_level DECIMAL(15, 2) DEFAULT 0,
  reorder_point DECIMAL(15, 2) DEFAULT 0,
  reorder_quantity DECIMAL(15, 2) DEFAULT 0,
  is_active BOOLEAN DEFAULT TRUE,
  is_serialized BOOLEAN DEFAULT FALSE,
  is_lot_tracked BOOLEAN DEFAULT FALSE,
  expiration_tracking BOOLEAN DEFAULT FALSE,
  fk_user_uuid_created_by UUID NOT NULL,
  fk_user_uuid_updated_by UUID NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (fk_company_uuid) REFERENCES company (company_uuid),
  FOREIGN KEY (fk_product_category_id) REFERENCES product_category (product_category_id),
  FOREIGN KEY (fk_user_uuid_created_by) REFERENCES users (user_uuid),
  FOREIGN KEY (fk_user_uuid_updated_by) REFERENCES users (user_uuid)
);
-- Table for inventory items
CREATE TABLE inventory_item (
  inventory_item_uuid UUID PRIMARY KEY,
  fk_company_uuid UUID NOT NULL,
  fk_warehouse_uuid UUID NOT NULL,
  quantity_on_hand DECIMAL(15, 2) NOT NULL,
  quantity_allocated DECIMAL(15, 2) NOT NULL,
  quantity_available DECIMAL(15, 2) NOT NULL,
  quantity_on_order DECIMAL(15, 2) NOT NULL,
  average_cost DECIMAL(15, 2) NOT NULL,
  last_cost DECIMAL(15, 2) NOT NULL,
  location_code VARCHAR(50) NOT NULL,
  lot_number VARCHAR(100),
  serial_number VARCHAR(100),
  expiration_date TIMESTAMPTZ,
  last_counted_date TIMESTAMPTZ,
  last_movement_date TIMESTAMPTZ,
  created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (fk_company_uuid) REFERENCES company (company_uuid),
  FOREIGN KEY (fk_warehouse_uuid) REFERENCES warehouse (warehouse_uuid)
);
-- Table for inventory transactions
CREATE TABLE inventory_transaction (
  inventory_transaction_uuid UUID PRIMARY KEY,
  transaction_type VARCHAR(100) NOT NULL,
  transaction_reason VARCHAR(100) NOT NULL,
  fk_warehouse_uuid UUID NOT NULL,
  quantity DECIMAL(15, 2) NOT NULL,
  unit_cost DECIMAL(15, 2) NOT NULL,
  total_cost DECIMAL(15, 2) NOT NULL,
  transaction_date TIMESTAMPTZ NOT NULL,
  reference_type VARCHAR(50),
  reference_id VARCHAR(50),
  reference_number VARCHAR(50),
  running_balance DECIMAL(15, 2) NOT NULL,
  fk_user_uuid UUID NOT NULL,
  notes TEXT,
  lot_number VARCHAR(50),
  serialNumber VARCHAR(100),
  expiration_date TIMESTAMPTZ,
  location_code VARCHAR(50),
  created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (fk_warehouse_uuid) REFERENCES warehouse (warehouse_uuid),
  FOREIGN KEY (fk_user_uuid) REFERENCES users (user_uuid)
);
-- Table for user, company, and role associations
CREATE TABLE user_company_role (
  uuid UUID PRIMARY KEY,
  user_uuid UUID NOT NULL,
  company_id UUID NOT NULL,
  role_id INT NOT NULL,
  FOREIGN KEY (user_uuid) REFERENCES users (user_uuid),
  FOREIGN KEY (company_id) REFERENCES company (company_uuid),
  FOREIGN KEY (role_id) REFERENCES role (id)
);
-- Table for user and warehouse associations (many-to-many)
CREATE TABLE user_warehouse_association (
  user_uuid UUID NOT NULL,
  warehouse_uuid UUID NOT NULL,
  PRIMARY KEY (user_uuid, warehouse_uuid),
  FOREIGN KEY (user_uuid) REFERENCES users (user_uuid),
  FOREIGN KEY (warehouse_uuid) REFERENCES warehouse (warehouse_uuid)
);
-- Table for user MFA tokens
CREATE TABLE user_mfa_tokens (
  uuid UUID PRIMARY KEY -- Add other relevant columns for MFA tokens, e.g., token, user_id, expiry_date
);
-- Insert initial data for lookup tables
INSERT INTO public.country (id, name)
SELECT row_number() OVER (
    ORDER BY code
  ) AS id,
  CONCAT (code, ' - ', country_name) AS name
FROM (
    VALUES ('AD', 'Andorra'),
      ('AE', 'United Arab Emirates'),
      ('AF', 'Afghanistan'),
      ('AG', 'Antigua and Barbuda'),
      ('AI', 'Anguilla'),
      ('AL', 'Albania'),
      ('AM', 'Armenia'),
      ('AO', 'Angola'),
      ('AQ', 'Antarctica'),
      ('AR', 'Argentina'),
      ('AS', 'American Samoa'),
      ('AT', 'Austria'),
      ('AU', 'Australia'),
      ('AW', 'Aruba'),
      ('AX', 'Åland Islands'),
      ('AZ', 'Azerbaijan'),
      ('BA', 'Bosnia and Herzegovina'),
      ('BB', 'Barbados'),
      ('BD', 'Bangladesh'),
      ('BE', 'Belgium'),
      ('BF', 'Burkina Faso'),
      ('BG', 'Bulgaria'),
      ('BH', 'Bahrain'),
      ('BI', 'Burundi'),
      ('BJ', 'Benin'),
      ('BL', 'Saint Barthélemy'),
      ('BM', 'Bermuda'),
      ('BN', 'Brunei Darussalam'),
      ('BO', 'Bolivia'),
      ('BQ', 'Caribbean Netherlands'),
      ('BR', 'Brazil'),
      ('BS', 'Bahamas'),
      ('BT', 'Bhutan'),
      ('BV', 'Bouvet Island'),
      ('BW', 'Botswana'),
      ('BY', 'Belarus'),
      ('BZ', 'Belize'),
      ('CA', 'Canada'),
      ('CC', 'Cocos (Keeling) Islands'),
      ('CD', 'Congo (Democratic Republic)'),
      ('CF', 'Central African Republic'),
      ('CG', 'Congo'),
      ('CH', 'Switzerland'),
      ('CI', 'Côte d Ivoire'),
      ('CK', 'Cook Islands'),
      ('CL', 'Chile'),
      ('CM', 'Cameroon'),
      ('CN', 'China'),
      ('CO', 'Colombia'),
      ('CR', 'Costa Rica'),
      ('CU', 'Cuba'),
      ('CV', 'Cabo Verde'),
      ('CW', 'Curaçao'),
      ('CX', 'Christmas Island'),
      ('CY', 'Cyprus'),
      ('CZ', 'Czechia'),
      ('DE', 'Germany'),
      ('DJ', 'Djibouti'),
      ('DK', 'Denmark'),
      ('DM', 'Dominica'),
      ('DO', 'Dominican Republic'),
      ('DZ', 'Algeria'),
      ('EC', 'Ecuador'),
      ('EE', 'Estonia'),
      ('EG', 'Egypt'),
      ('EH', 'Western Sahara'),
      ('ER', 'Eritrea'),
      ('ES', 'Spain'),
      ('ET', 'Ethiopia'),
      ('FI', 'Finland'),
      ('FJ', 'Fiji'),
      ('FK', 'Falkland Islands'),
      ('FM', 'Micronesia'),
      ('FO', 'Faroe Islands'),
      ('FR', 'France'),
      ('GA', 'Gabon'),
      ('GB', 'United Kingdom'),
      ('GD', 'Grenada'),
      ('GE', 'Georgia'),
      ('GF', 'French Guiana'),
      ('GG', 'Guernsey'),
      ('GH', 'Ghana'),
      ('GI', 'Gibraltar'),
      ('GL', 'Greenland'),
      ('GM', 'Gambia'),
      ('GN', 'Guinea'),
      ('GP', 'Guadeloupe'),
      ('GQ', 'Equatorial Guinea'),
      ('GR', 'Greece'),
      (
        'GS',
        'South Georgia and the South Sandwich Islands'
      ),
      ('GT', 'Guatemala'),
      ('GU', 'Guam'),
      ('GW', 'Guinea-Bissau'),
      ('GY', 'Guyana'),
      ('HK', 'Hong Kong'),
      ('HM', 'Heard Island and McDonald Islands'),
      ('HN', 'Honduras'),
      ('HR', 'Croatia'),
      ('HT', 'Haiti'),
      ('HU', 'Hungary'),
      ('ID', 'Indonesia'),
      ('IE', 'Ireland'),
      ('IL', 'Israel'),
      ('IM', 'Isle of Man'),
      ('IN', 'India'),
      ('IO', 'British Indian Ocean Territory'),
      ('IQ', 'Iraq'),
      ('IR', 'Iran'),
      ('IS', 'Iceland'),
      ('IT', 'Italy'),
      ('JE', 'Jersey'),
      ('JM', 'Jamaica'),
      ('JO', 'Jordan'),
      ('JP', 'Japan'),
      ('KE', 'Kenya'),
      ('KG', 'Kyrgyzstan'),
      ('KH', 'Cambodia'),
      ('KI', 'Kiribati'),
      ('KM', 'Comoros'),
      ('KN', 'Saint Kitts and Nevis'),
      ('KP', 'North Korea'),
      ('KR', 'South Korea'),
      ('KW', 'Kuwait'),
      ('KY', 'Cayman Islands'),
      ('KZ', 'Kazakhstan'),
      ('LA', 'Laos'),
      ('LB', 'Lebanon'),
      ('LC', 'Saint Lucia'),
      ('LI', 'Liechtenstein'),
      ('LK', 'Sri Lanka'),
      ('LR', 'Liberia'),
      ('LS', 'Lesotho'),
      ('LT', 'Lithuania'),
      ('LU', 'Luxembourg'),
      ('LV', 'Latvia'),
      ('LY', 'Libya'),
      ('MA', 'Morocco'),
      ('MC', 'Monaco'),
      ('MD', 'Moldova'),
      ('ME', 'Montenegro'),
      ('MF', 'Saint Martin'),
      ('MG', 'Madagascar'),
      ('MH', 'Marshall Islands'),
      ('MK', 'North Macedonia'),
      ('ML', 'Mali'),
      ('MM', 'Myanmar'),
      ('MN', 'Mongolia'),
      ('MO', 'Macao'),
      ('MP', 'Northern Mariana Islands'),
      ('MQ', 'Martinique'),
      ('MR', 'Mauritania'),
      ('MS', 'Montserrat'),
      ('MT', 'Malta'),
      ('MU', 'Mauritius'),
      ('MV', 'Maldives'),
      ('MW', 'Malawi'),
      ('MX', 'Mexico'),
      ('MY', 'Malaysia'),
      ('MZ', 'Mozambique'),
      ('NA', 'Namibia'),
      ('NC', 'New Caledonia'),
      ('NE', 'Niger'),
      ('NF', 'Norfolk Island'),
      ('NG', 'Nigeria'),
      ('NI', 'Nicaragua'),
      ('NL', 'Netherlands'),
      ('NO', 'Norway'),
      ('NP', 'Nepal'),
      ('NR', 'Nauru'),
      ('NU', 'Niue'),
      ('NZ', 'New Zealand'),
      ('OM', 'Oman'),
      ('PA', 'Panama'),
      ('PE', 'Peru'),
      ('PF', 'French Polynesia'),
      ('PG', 'Papua New Guinea'),
      ('PH', 'Philippines'),
      ('PK', 'Pakistan'),
      ('PL', 'Poland'),
      ('PM', 'Saint Pierre and Miquelon'),
      ('PN', 'Pitcairn Islands'),
      ('PR', 'Puerto Rico'),
      ('PS', 'Palestine'),
      ('PT', 'Portugal'),
      ('PW', 'Palau'),
      ('PY', 'Paraguay'),
      ('QA', 'Qatar'),
      ('RE', 'Réunion'),
      ('RO', 'Romania'),
      ('RS', 'Serbia'),
      ('RU', 'Russia'),
      ('RW', 'Rwanda'),
      ('SA', 'Saudi Arabia'),
      ('SB', 'Solomon Islands'),
      ('SC', 'Seychelles'),
      ('SD', 'Sudan'),
      ('SE', 'Sweden'),
      ('SG', 'Singapore'),
      ('SH', 'Saint Helena'),
      ('SI', 'Slovenia'),
      ('SJ', 'Svalbard and Jan Mayen'),
      ('SK', 'Slovakia'),
      ('SL', 'Sierra Leone'),
      ('SM', 'San Marino'),
      ('SN', 'Senegal'),
      ('SO', 'Somalia'),
      ('SR', 'Suriname'),
      ('SS', 'South Sudan'),
      ('ST', 'São Tomé and Príncipe'),
      ('SV', 'El Salvador'),
      ('SX', 'Sint Maarten'),
      ('SY', 'Syria'),
      ('SZ', 'Eswatini'),
      ('TC', 'Turks and Caicos Islands'),
      ('TD', 'Chad'),
      ('TF', 'French Southern Territories'),
      ('TG', 'Togo'),
      ('TH', 'Thailand'),
      ('TJ', 'Tajikistan'),
      ('TK', 'Tokelau'),
      ('TL', 'Timor-Leste'),
      ('TM', 'Turkmenistan'),
      ('TN', 'Tunisia'),
      ('TO', 'Tonga'),
      ('TR', 'Turkey'),
      ('TT', 'Trinidad and Tobago'),
      ('TV', 'Tuvalu'),
      ('TW', 'Taiwan'),
      ('TZ', 'Tanzania'),
      ('UA', 'Ukraine'),
      ('UG', 'Uganda'),
      ('UM', 'U.S. Minor Outlying Islands'),
      ('US', 'United States of America'),
      ('UY', 'Uruguay'),
      ('UZ', 'Uzbekistan'),
      ('VA', 'Vatican City'),
      ('VC', 'Saint Vincent and the Grenadines'),
      ('VE', 'Venezuela'),
      ('VG', 'British Virgin Islands'),
      ('VI', 'U.S. Virgin Islands'),
      ('VN', 'Vietnam'),
      ('VU', 'Vanuatu'),
      ('WF', 'Wallis and Futuna'),
      ('WS', 'Samoa'),
      ('YE', 'Yemen'),
      ('YT', 'Mayotte'),
      ('ZA', 'South Africa'),
      ('ZM', 'Zambia'),
      ('ZW', 'Zimbabwe')
  ) AS t (code, country_name);
INSERT INTO organization_type (name)
VALUES ('Sole Proprietorship'),
  ('Partnership'),
  ('Limited Liability Company (LLC)'),
  ('Corporation'),
  ('Non-Profit Organization'),
  ('Government Agency'),
  ('Educational Institution'),
  ('Cooperative'),
  ('Public Company'),
  ('Private Company'),
  ('Joint Venture'),
  ('Franchise'),
  ('Holding Company'),
  ('Subsidiary'),
  ('Startup'),
  ('Multinational Corporation'),
  ('Religious Institution'),
  ('Healthcare Provider'),
  ('Research Institution'),
  ('Trade Association'),
  ('Consultancy'),
  ('Charitable Foundation'),
  ('Trust'),
  ('Microenterprise'),
  ('Social Enterprise');
INSERT INTO role (name, description)
VALUES (
    'Chief Executive Officer',
    'Provides strategic leadership and oversees overall company operations and decision-making.'
  ),
  (
    'Chief Financial Officer',
    'Manages the company’s finances, including budgeting, forecasting, and financial reporting.'
  ),
  (
    'Chief Technology Officer',
    'Directs technology strategies, IT infrastructure, and software development initiatives.'
  ),
  (
    'ERP Administrator',
    'Maintains and configures the ERP system, manages user access, and ensures system integrity.'
  ),
  (
    'System Administrator',
    'Administers servers, networks, databases, and ensures system uptime and security.'
  ),
  (
    'HR Manager',
    'Oversees recruitment, employee relations, payroll, and organizational policies.'
  ),
  (
    'Recruitment Specialist',
    'Manages hiring processes, job postings, and applicant tracking within the ERP.'
  ),
  (
    'Payroll Specialist',
    'Processes payrolls, benefits, and tax deductions for all employees.'
  ),
  (
    'Inventory Manager',
    'Controls stock levels, ensures accurate inventory records, and coordinates procurement.'
  ),
  (
    'Procurement Officer',
    'Manages vendor relationships, purchase orders, and inventory acquisition.'
  ),
  (
    'Sales Manager',
    'Oversees the sales team, sales targets, client negotiations, and pipeline forecasting.'
  ),
  (
    'Sales Representative',
    'Conducts sales outreach, prepares quotations, and handles customer follow-ups.'
  ),
  (
    'Marketing Manager',
    'Leads marketing campaigns, brand awareness, and digital promotion strategies.'
  ),
  (
    'Production Manager',
    'Oversees manufacturing processes, schedules, quality control, and resource planning.'
  ),
  (
    'Quality Assurance Officer',
    'Ensures product or service quality through inspections and audits.'
  ),
  (
    'Logistics Coordinator',
    'Manages transportation, warehousing, and delivery tracking across the supply chain.'
  ),
  (
    'Finance Analyst',
    'Performs financial modeling, data analysis, and budget monitoring.'
  ),
  (
    'Accountant',
    'Handles general ledger entries, reconciliations, invoicing, and compliance.'
  ),
  (
    'Customer Support Agent',
    'Responds to client inquiries, technical issues, and after-sales support.'
  ),
  (
    'Compliance Officer',
    'Ensures regulatory compliance across departments and mitigates business risks.'
  ),
  (
    'IT Support Specialist',
    'Provides technical assistance to users, troubleshooting hardware and software.'
  ),
  (
    'Business Analyst',
    'Gathers business requirements and bridges communication between IT and operations.'
  ),
  (
    'Software Engineer',
    'Designs, builds, and maintains custom ERP modules and systems integrations.'
  ),
  (
    'Data Analyst',
    'Interprets ERP-generated data to generate insights and operational reports.'
  ),
  (
    'Project Manager',
    'Coordinates projects, allocates resources, manages timelines, and tracks deliverables.'
  );