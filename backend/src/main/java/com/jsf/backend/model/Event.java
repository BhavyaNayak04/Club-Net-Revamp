package com.jsf.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    private ObjectId id;
    private ObjectId clubId;
    private String eventName;
    private String description;
    private String banner;
    private Date dateAndTime;
    private String location;
    private Integer entryFee;
    private Integer teamCapacity;
    private String organizerContactNumber;
}
