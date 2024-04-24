package com.projectExample.project.Controller;

import com.projectExample.project.DTO.UserDTO;
import com.projectExample.project.Entity.UserDAO;
import com.projectExample.project.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAll(){
        return this.userServiceImpl.getAllUsers();
    }

    @GetMapping("/get/{pid}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("pid") int id){
        return this.userServiceImpl.getUserById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDAO user){
        return this.userServiceImpl.createUser(user);
    }


    @DeleteMapping("/delete/{uid}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable("uid") int id){
        return this.userServiceImpl.deleteUserById(id);
    }

    @PutMapping("/update/{pid}")
    public ResponseEntity<UserDTO> updateUserById( @PathVariable("pid") int id, @RequestBody UserDAO user){
        return this.userServiceImpl.updateUser(id,user);
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<UserDTO> deleteAllUser(){
        return this.userServiceImpl.deleteAllUsers();
    }

}
