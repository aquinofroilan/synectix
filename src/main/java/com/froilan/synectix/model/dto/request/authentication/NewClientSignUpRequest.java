package com.froilan.synectix.model.dto.request.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewClientSignUpRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    private String email;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    @NotBlank
    @Size(min = 8, max = 100)
    private String confirmPassword;

    @NotBlank
    private String companyName;

    @NotBlank
    private Integer countryId;

    @NotBlank
    private Integer organizationTypeId;

    @NotBlank
    private String registrationNumber;

    @NotBlank
    private String taxNumber;

}
