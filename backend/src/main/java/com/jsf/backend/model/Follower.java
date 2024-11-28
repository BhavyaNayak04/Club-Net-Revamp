package com.jsf.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Followers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Follower {
    @Id
    private ObjectId id;
    private ObjectId userId;
    private ObjectId clubId;
    private Date followedAt;
}
