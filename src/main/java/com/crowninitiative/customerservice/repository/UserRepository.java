package com.crowninitiative.customerservice.repository;

import com.crowninitiative.customerservice.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
