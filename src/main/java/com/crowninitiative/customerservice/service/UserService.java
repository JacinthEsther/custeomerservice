package com.crowninitiative.customerservice.service;

import com.crowninitiative.customerservice.dtos.request.UserRequest;
import com.crowninitiative.customerservice.dtos.response.UserResponse;
import com.crowninitiative.customerservice.models.User;

import java.util.List;

public interface UserService {
    UserResponse save(UserRequest request);

    void deleteAll();

    long count();

    User retrieveACustomerBy(String email);

    List<User> retrieveAllCustomers();
}
