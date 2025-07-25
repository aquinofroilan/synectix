package com.froilan.synectix.service.auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.froilan.synectix.config.security.jwt.JWTUtil;
import com.froilan.synectix.exception.authentication.ConflictException;
import com.froilan.synectix.exception.authentication.UserNotFoundException;
import com.froilan.synectix.exception.authentication.WrongPasswordException;
import com.froilan.synectix.model.Company;
import com.froilan.synectix.model.User;
import com.froilan.synectix.model.dto.request.authentication.NewClientSignUpRequest;
import com.froilan.synectix.model.lookup.Country;
import com.froilan.synectix.model.lookup.OrganizationType;
import com.froilan.synectix.repository.CompanyRepository;
import com.froilan.synectix.repository.CountryRepository;
import com.froilan.synectix.repository.OrganizationTypeRepository;
import com.froilan.synectix.repository.user.UserRepository;
import com.froilan.synectix.exception.validation.NotFoundException;

@Service
public class AuthenticationService {
    @Autowired
    private JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final CountryRepository countryRepository;
    private final OrganizationTypeRepository organizationTypeRepository;

    public AuthenticationService(PasswordEncoder passwordEncoder, UserRepository userRepository,
            CompanyRepository companyRepository, CountryRepository countryRepository,
            OrganizationTypeRepository organizationTypeRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
        this.organizationTypeRepository = organizationTypeRepository;
    }

    public Map<String, String> SignInUser(String username, String password) {
        if (!userRepository.existsByEmail(username))
            throw new UserNotFoundException("User with that email or username does not exist.");

        if (!userRepository.existsByUsername(username))
            throw new UserNotFoundException("User with that email or username does not exist.");
        User user = userRepository.findByEmail(username).orElseGet(() -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with that email or username does not exist.")));
        if (!passwordEncoder.matches(password, user.getHashedPassword()))
            throw new WrongPasswordException("Wrong password.");
        String accessToken = jwtUtil.generateToken(user.getUsername(), user.getUuid().toString());
        String refreshToken = jwtUtil.refreshToken(user.getUsername(), user.getUuid().toString());
        return Map.of("accessToken", accessToken, "refreshToken", refreshToken);
    }

    public void SignUpUser(NewClientSignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail()))
            throw new ConflictException("email", "Email already in use.");

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber()))
            throw new ConflictException("phone number", "Phone number already in use.");

        if (userRepository.existsByUsername(request.getUsername()))
            throw new ConflictException("username", "Username already in use.");

        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new NotFoundException("Country not found."));
        OrganizationType organizationType = organizationTypeRepository.findById(request.getOrganizationTypeId())
                .orElseThrow(() -> new NotFoundException("Organization type not found."));

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User user = User.builder().username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .hashedPassword(hashedPassword)
                .build();
        userRepository.save(user);
        Company company = Company.builder()
                .name(request.getCompanyName())
                .registrationNumber(request.getRegistrationNumber())
                .taxNumber(request.getTaxNumber())
                .country(country)
                .organizationType(organizationType)
                .build();
        companyRepository.save(company);

    }
}
