package com.jsf.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Queries")
public class Query {
    @Id
    private ObjectId id;
    private ObjectId eventId;
    private ObjectId userId;
    private String queryText;
    private Date createdAt;
    private Boolean resolved;
    private String responseText;
    private Date respondedAt;
}

