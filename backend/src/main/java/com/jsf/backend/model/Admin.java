package com.jsf.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Admins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @Id
    private ObjectId id;
    private String username;
    private String password;
    private ObjectId managedClubId;
    private Date createdAt;
}