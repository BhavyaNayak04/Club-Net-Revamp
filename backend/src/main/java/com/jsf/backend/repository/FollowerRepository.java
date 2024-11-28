package com.jsf.backend.repository;

import com.jsf.backend.model.Follower;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FollowerRepository extends MongoRepository<Follower, ObjectId> {
}
