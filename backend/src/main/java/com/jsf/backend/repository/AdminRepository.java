package com.jsf.backend.repository;
import com.jsf.backend.model.Admin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends MongoRepository<Admin, ObjectId> {

    Optional<Admin> findByUsername(String username);
}