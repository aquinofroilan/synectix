package com.froilan.synectix.model.dto.request.authentication;

import org.springframework.lang.NonNull;

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
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 100)
    private String password;

    @NotBlank(message = "Confirm password cannot be blank")
    @Size(min = 8, max = 100)
    private String confirmPassword;

    @NotBlank(message = "Company name cannot be blank")
    private String companyName;

    @NonNull
    private Integer countryId;

    @NonNull
    private Integer organizationTypeId;

    @NotBlank(message = "Registration number cannot be blank")
    @Size(max = 100, message = "Registration number cannot exceed 100 characters")
    private String registrationNumber;

    @NotBlank
    @Size(max = 100, message = "Tax number cannot exceed 100 characters")
    private String taxNumber;

}
