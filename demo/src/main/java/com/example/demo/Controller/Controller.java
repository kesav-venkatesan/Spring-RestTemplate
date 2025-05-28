package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Exception.UserNotFound;
import com.example.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    UserRepo userRepo;

    @GetMapping("getall")
    public List<User> getUsers() {
        return userRepo.findAll();
    }
    @PostMapping("adduser")
    public User createUser(@RequestBody User user) {
        return userRepo.save(user);
    }
    @GetMapping("getbyid/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepo.findById(id).orElseThrow(()-> new UserNotFound("User Not Found"));
    }

    @DeleteMapping("deleteuser/{id}")
    public User deleteUser(@PathVariable Long id) {
        User user = userRepo.findById(id).orElseThrow(()-> new UserNotFound("User Not Found"));
        userRepo.delete(user);
        return user;
    }

    @PutMapping("update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        User old=userRepo.findById(id).orElseThrow(()-> new UserNotFound("User Not Found"));
        user.setId(old.getId());
        return userRepo.save(user);
    }
}
