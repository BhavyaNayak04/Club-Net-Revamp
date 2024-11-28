package com.jsf.backend.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface QueryRepository extends MongoRepository<Query, ObjectId> {
}
