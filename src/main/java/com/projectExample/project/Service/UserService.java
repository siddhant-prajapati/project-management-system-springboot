package com.projectExample.project.Service;

import com.projectExample.project.DTO.UserDTO;
import com.projectExample.project.Entity.UserDAO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public ResponseEntity<List<UserDTO>> getAllUsers();

    public ResponseEntity<UserDTO> getUserById(int id);

    public ResponseEntity<UserDTO> createUser(UserDAO user);

    public ResponseEntity<UserDTO> updateUser(int id, UserDAO user);

    public ResponseEntity<UserDTO> deleteUserById(int id);

    public ResponseEntity<UserDTO> deleteAllUsers();
}
