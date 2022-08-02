package com.crowninitiative.customerservice;


import com.crowninitiative.customerservice.dtos.request.UserRequest;
import com.crowninitiative.customerservice.dtos.response.UserResponse;
import com.crowninitiative.customerservice.exceptions.CustomerServiceException;
import com.crowninitiative.customerservice.models.User;
import com.crowninitiative.customerservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    private UserRequest newCustomer;


    @BeforeEach
    void setUp(){
       newCustomer = UserRequest.builder()
                .email("testUser@gmail.com")
                .firstName("testFirst")
                .lastName("testLast")
                .build();
    }


    @Test
    void testThatANewCustomerCanBeSaved(){

      UserResponse response=  userService.save(newCustomer);

      assertThat(response.getEmail(), is("testUser@gmail.com") );
      assertThat(response.getTariff(), is(BigDecimal.ONE));

    }

    @Test
    void testThatASavedUserCannotRegisterTwice(){
          userService.save(newCustomer);
        UserRequest OneMoreTime = UserRequest.builder()
                .email("testUser@gmail.com")
                .firstName("testFirst")
                .lastName("testLast")
                .build();
        assertThrows(CustomerServiceException.class,()-> userService.save(OneMoreTime));
    }


    @Test
    void testThatInvalidEmailThrowsException(){
        UserRequest user = UserRequest.builder()
                .email("testUser")
                .firstName("testFirst")
                .lastName("testLast")
                .build();
        assertThrows(CustomerServiceException.class,()-> userService.save(user));
    }


    @Test
    void testThatMoreCustomersCanBeSaved(){
        saveMoreCustomer();
        assertThat(userService.count(), is(2L));
    }

    @Test
    void testForRetrievalOfACustomer(){
        saveMoreCustomer();

        User user=  userService.retrieveACustomerBy("testUser@gmail.com");

      assertThat(user.getEmail(), is("testUser@gmail.com"));
    }

    private void saveMoreCustomer() {
        userService.save(newCustomer);
        UserRequest moreCustomer = UserRequest.builder()
                .email("testUser2@gmail.com")
                .firstName("testFirst")
                .lastName("testLast")
                .build();
        userService.save(moreCustomer);
    }

    @Test
    void testThatUnsavedUserThrowsException(){
        userService.save(newCustomer);
        assertThrows(CustomerServiceException.class,()-> userService.retrieveACustomerBy("test@gmail.com"));

    }

    @Test
    void testThatAllUserCanBeRetrieved(){
        saveMoreCustomer();
    List<User> users = userService.retrieveAllCustomers();
    assertThat(users.size(), is(2));
    }

    @AfterEach
    void tearDown(){
        userService.deleteAll();
    }


}
