package com.jsf.backend.repository;
import com.jsf.backend.model.User;
import jakarta.validation.constraints.NotEmpty;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;


public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUsn(@NotEmpty(message = "USN is required") String usn);
}
