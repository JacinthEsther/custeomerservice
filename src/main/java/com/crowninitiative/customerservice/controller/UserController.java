package com.crowninitiative.customerservice.controller;

import com.crowninitiative.customerservice.dtos.request.UserRequest;
import com.crowninitiative.customerservice.dtos.response.ApiResponse;
import com.crowninitiative.customerservice.exceptions.CustomerServiceException;
import com.crowninitiative.customerservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody UserRequest user){
        try {
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
        } catch (CustomerServiceException ex) {
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @GetMapping("/{email}")
    public ResponseEntity<?> retrieveACustomer(@PathVariable String email) {
        try {
            return new ResponseEntity<>(userService.retrieveACustomerBy(email), HttpStatus.FOUND);
        }  catch (CustomerServiceException ex) {
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> retrieveAllCustomers() {
        try {
            return new ResponseEntity<>(userService.retrieveAllCustomers(), HttpStatus.FOUND);
        } catch (CustomerServiceException ex) {
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
