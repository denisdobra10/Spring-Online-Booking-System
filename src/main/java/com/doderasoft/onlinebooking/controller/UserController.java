package com.doderasoft.onlinebooking.controller;

import com.doderasoft.onlinebooking.model.User;
import com.doderasoft.onlinebooking.response.RequestResponse;
import com.doderasoft.onlinebooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/user")
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {this.userService = userService;}


    @GetMapping(path = "{id}")
    public User getUserById(@PathVariable UUID id) {
        return userService.selectUserById(id).orElse(null);
    }

    @GetMapping(path = "/all")
    public List<User> getAllUsers() {
        return userService.selectAllUsers();
    }

    @PostMapping(path = "/register")
    public void register(@RequestBody User newUser) {
        userService.addUser(newUser);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUserById(id);
    }

    @PutMapping(path = "{id}")
    public String updateUserById(@PathVariable("id") UUID id, @RequestBody User user) {
        return userService.updateUserById(id, user) == 1 ? "User has been successfully updated"
                : String.format("Warning: There was nothing updated due the reason: User with UUID %s not found", id);
    }


    // Login
    @PostMapping(path = "/login")
    public int login(@RequestBody HashMap<String, String> credentials) {
        return userService.loginToAccount(credentials);
    }

    // Balance
    @PostMapping(path = "{id}/balance")
    public int setBalance(@PathVariable UUID id, @RequestBody float balance) {
        return userService.setBalance(id, balance);
    }

    // Buy tickets
    @PostMapping(path = "/buy-tickets")
    public RequestResponse buyTickets(@RequestBody HashMap<String, String> purchaseData) {
        if(purchaseData.containsKey("userUUID") && purchaseData.containsKey("eventUUID") && purchaseData.containsKey("tickets"))
            return userService.buyTickets(UUID.fromString(purchaseData.get("userUUID")),
                    UUID.fromString(purchaseData.get("eventUUID")),
                    Integer.parseInt(purchaseData.get("tickets")));

        return RequestResponse.throwBadRequestResponse("Invalid body format! You should include userUUID, eventUUID and tickets as keys" +
                " in your request! userUUID of type UUID (String); eventUUID of type UUID (String); tickets of type Integer");

    }

}
