package com.jsf.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "Clubs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Club {
    @Id
    private ObjectId id;
    private String clubName;
    private String category;
    private String description;
    private String url;
    private String logo;
    private Integer followerCount;

}
