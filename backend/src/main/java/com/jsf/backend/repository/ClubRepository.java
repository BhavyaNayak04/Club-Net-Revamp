package com.jsf.backend.repository;

import com.jsf.backend.model.Club;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends MongoRepository<Club, ObjectId> {

}
