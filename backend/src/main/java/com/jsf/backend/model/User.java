package com.jsf.backend.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private ObjectId id;

    @Email(message = "Invalid email format")
    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Full name is required")
    private String fullName;

    @NotEmpty(message = "Password is required")
    private String password;

    private Date createdAt = new Date();

    @NotNull(message = "Semester is required")
    private int semester;

    @NotEmpty(message = "Branch is required")
    private String branch;

    private List<String> tags;

    @NotEmpty(message = "USN is required")
    private String usn;
}

