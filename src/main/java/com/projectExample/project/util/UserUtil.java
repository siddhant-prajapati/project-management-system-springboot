package com.projectExample.project.util;

import com.projectExample.project.DTO.UserDTO;
import com.projectExample.project.Entity.UserDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserUtil {
    public List<UserDTO> userDaoToDtoConversion(List<UserDAO> userDAOs){
        System.out.println("UserDAO is "+ userDAOs);
        List<UserDTO> userDTOS = new ArrayList<>();
        for(UserDAO userDAO : userDAOs){
            UserDTO userDTO = new UserDTO();
            userDTO.setUserName(userDAO.getUserName());
            userDTO.setUserId(userDAO.getUserId());
            userDTO.setEmail(userDAO.getEmail());
            userDTO.setAge(userDAO.getAge());
            userDTO.setPassword(userDAO.getPassword());
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    public List<UserDAO> userDtoToDaoConversion(List<UserDTO> userDTOS){
        System.out.println("User DTO is = " + userDTOS);
        List<UserDAO> userDAOS = new ArrayList<>();
        for(UserDTO userDTO : userDTOS){
            UserDAO userDAO = new UserDAO();
            userDAO.setEmail(userDTO.getEmail());
            userDAO.setUserName(userDTO.getUserName());
            userDAO.setPassword(userDTO.getPassword());
            userDAO.setUserId(userDTO.getUserId());
            userDAO.setAge(userDTO.getAge());
            userDAOS.add(userDAO);
        }
        return userDAOS;
    }
}
