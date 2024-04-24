package com.projectExample.project.Service;

import com.projectExample.project.DTO.UserDTO;
import com.projectExample.project.Entity.UserDAO;
import com.projectExample.project.repository.UserRepository;
import com.projectExample.project.util.UserUtil;
import com.projectExample.project.util.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService{
    @Autowired
    private UserValidation userValidation;

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private UserRepository userRepository;


    @Override
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDAO> users =(List<UserDAO>) this.userRepository.findAll();
        if(users.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of( userUtil.userDaoToDtoConversion(users)));
    }

    @Override
    public ResponseEntity<UserDTO> getUserById(int id) {
        UserDTO userDTO;
        try {
            userDTO = userUtil.userDaoToDtoConversion(Collections.singletonList(userRepository.findById(id))).get(0);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of( userDTO));
    }

    @Override
    public ResponseEntity<UserDTO> createUser(UserDAO user) {
        UserDTO userDTO;
        try{
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            if(userValidation.validation(user)){
                String password = user.getPassword();
                user.setPassword(passwordEncoder.encode(password));
                try{
                    userDTO = userUtil.userDaoToDtoConversion(
                        List.of(this.userRepository.save(user))
                    ).get(0);
                    return ResponseEntity.of(Optional.of(userDTO));
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @Override
    public ResponseEntity<UserDTO> updateUser(int id, UserDAO user) {
        UserDTO userDTO;
        try {
            if(userValidation.validation(user)){
                user.setUserId(id);
                userUtil.userDaoToDtoConversion(
                        List.of(this.userRepository.save(user))
                ).get(0);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @Override
    public ResponseEntity<UserDTO> deleteUserById(int id) {
        try{
            userUtil.userDaoToDtoConversion(List.of(userRepository.deleteById(id)));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<UserDTO> deleteAllUsers() {
        try {
            userRepository.deleteAll();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<UserDTO> findByEmail(String email){
        UserDTO userDTO;
        try {
            userDTO = userUtil.userDaoToDtoConversion(Collections.singletonList(userRepository.findByEmail(email))).get(0);
            System.out.println("UserDTO = "+userDTO);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of( userDTO));
    }
}
