package com.sip.ams.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.sip.ams.services.ProviderService;
import com.sip.ams.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sip.ams.entities.Provider;
import com.sip.ams.entities.User;
import com.sip.ams.repositories.ProviderRepository;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping({"/users"})
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers() {

        return userService.getAllUsers();

    }

    /*@PostMapping("/")
    public User createUser(@Valid @RequestBody User user) {
        return userService.saveUser2(user);
    }*/
    @PostMapping("/")
    public void createUser(@Valid @RequestBody User user) {
         userService.saveUser(user);
    }

    @PutMapping("/{userId}")
    public User updateProvider(@PathVariable Long userId, @Valid @RequestBody User userRequest) {
        return userService.updateUser(userId,userRequest);
    }


    @DeleteMapping("/{userId}")
    public User deleteProvider(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.getOneUserById(userId);
    }


}

