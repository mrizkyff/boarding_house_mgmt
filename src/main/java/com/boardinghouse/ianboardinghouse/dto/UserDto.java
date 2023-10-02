package com.boardinghouse.ianboardinghouse.dto;

import com.boardinghouse.ianboardinghouse.enums.*;
import com.boardinghouse.ianboardinghouse.model.Room;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record UserDto(
        UUID id,
        @NotBlank(message = "First Name is required")
        @Size(max = 255, message = "Max is 255 characters")
        String firstName,

        @NotBlank(message = "Last Name is required")
        @Size(max = 255, message = "Max is 255 characters")
        String lastName,

        @NotBlank(message = "Place Of Birth is required")
        @Size(max = 255, message = "Max is 255 characters")
        String place_of_birth,

        @NotNull(message = "Date of Birth is required")
        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd")
        @Past(message = "Date of Birth must be in the past")
//        @Future(message = "Date of Birth cannot be in the future")
        LocalDate date_of_birth,

        @NotBlank(message = "Phone is required")
        @Pattern(regexp = "\\d{10,12}", message = "Phone number must be 10 to 12 digits")
        String phone,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Address is required")
        @Size(max = 255, message = "Max is 255 characters")
        String address,

        @NotNull(message = "Gender is required")
        Gender gender,

        @NotNull(message = "Marital Status is required")
        MaritalStatus marital_status,

        @NotNull(message = "Religion is required")
        Religion religion,

        @NotNull(message = "Identity Type is required")
        IdentityType identity_type,

        @NotNull(message = "Identity is required")
        String identity,

        Boolean status,

        @NotNull(message = "User Type is required")
        Role role,

        @NotBlank(message = "Password is required")
        String password,

        UUID room_id,

        Room room
) {
}