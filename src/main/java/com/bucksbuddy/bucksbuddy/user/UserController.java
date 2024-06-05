package com.bucksbuddy.bucksbuddy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public boolean login(@RequestBody UserLoginRequest request) {
        return userService.loginUser(request.getEmail(), request.getPassword());
    }

    @GetMapping("/id/{email}")
    public ResponseEntity<Integer> getUserIdByEmail(@PathVariable String email) {
        Optional<Integer> userId = userService.getUserIdByEmail(email);
        return userId.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }
}
