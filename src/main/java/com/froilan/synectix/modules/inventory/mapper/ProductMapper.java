package com.froilan.synectix.modules.inventory.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

//    @Mapping(target = "productCategory", source = "category")
//    @Mapping(target = "createdBy", source = "user")
//    @Mapping(target = "updatedBy", source = "user")
//    @Mapping(target = "company", source = "company")
//    @Mapping(target = "isActive", constant = "true")
//    @Mapping(target = "isLotTracked", ignore = true)
//    @Mapping(target = "isSerialized", ignore = true)
//    @Mapping(target = "productUuid", ignore = true)
//    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "updatedAt", ignore = true)
//    Product toEntity(ProductCreateBody dto, ProductCategory category, User user, Company company);
//
//    @Mapping(target = "productUuid", expression = "java(product.getProductUuid().toString())")
//    @Mapping(target = "productCategory", expression = "java(product.getProductCategory().toString())")
//    @Mapping(target = "productType", expression = "java(product.getProductType().toString())")
//    @Mapping(target = "unitMeasure", expression = "java(product.getUnitMeasure().toString())")
//    @Mapping(target = "weightUnit", expression = "java(product.getWeightUnit().toString())")
//    @Mapping(target = "dimensionUnit", expression = "java(product.getDimensionUnit().toString())")
//    @Mapping(target = "createdByUuid", expression = "java(product.getCreatedBy().getUserUuid().toString())")
//    @Mapping(target = "updatedByUuid", expression = "java(product.getUpdatedBy().getUserUuid().toString())")
//    ProductDetailsDTO toDTO(Product product);
}
