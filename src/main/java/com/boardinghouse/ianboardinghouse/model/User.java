package com.boardinghouse.ianboardinghouse.model;

import com.boardinghouse.ianboardinghouse.enums.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "master_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Max is 255 characters")
    private String name;

    @NotBlank(message = "Place Of Birth is required")
    @Size(max = 255, message = "Max is 255 characters")
    private String place_of_birth;

    @NotBlank(message = "Date of Birt is required")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date_of_birth;

    @NotBlank(message = "Phone is required")
    @Column(unique = true)
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Format email not valid")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Max is 255 characters")
    private String address;

    @NotBlank(message = "Gender is required")
    private Gender gender;

    @NotBlank(message = "Marital Status is required")
    private MaritalStatus marital_status;

    @NotBlank(message = "Religion is required")
    private Religion religion;

    @NotBlank(message = "Identity Type is required")
    private String identity_type;

    @NotBlank(message = "Identity is required")
    private Identity identity;

    private Boolean status = true;

    @NotBlank(message = "User Type is required")
    private UserType user_type;

    @Column(unique = true)
    private String username;

    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Room room;
}
