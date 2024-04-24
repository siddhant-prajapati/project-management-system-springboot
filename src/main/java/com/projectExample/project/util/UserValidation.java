package com.projectExample.project.util;

import com.projectExample.project.Entity.UserDAO;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidation {
    /**
     *
     * @param userName :- name of Student
     * @param email :- email of student
     * @return boolean
     */
    private boolean validateNameAndEmail(String userName, String email){
        try{
            if(userName.isEmpty()){
                throw new Exception("User Name must not empty");
            }
            if(email.isEmpty()){
                throw new Exception("Email must not empty");
            }
        } catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * @implNote Use for validate type password
     * @param password : String (encoded Password)
     * @return boolean
     */
    private boolean validatePassword(String password){
        //        password must follow below all conditions
        //        ^ represents starting character of the string.
        //        (?=.*[0-9]) represents a digit must occur at least once.
        //        (?=.*[a-z]) represents a lower case alphabet must occur at least once.
        //        (?=.*[A-Z]) represents an upper case alphabet that must occur at least once.
        //        (?=.*[@#$%^&-+=()] represents a special character that must occur at least once.
        //        (?=\\S+$) white spaces don’t allowed in the entire string.
        //        {8, 20} represents at least 8 characters and at most 20 characters.
        //        $ represents the end of the string.
        String regex ="^(?=.*[0-9])"
                    + "(?=.*[a-z])(?=.*[A-Z])"
                    + "(?=.*[@#$%^&+=])"
                    + "(?=\\S+$).{8,20}$";

        try{
            if(password.length() < 8){
                throw new Exception("Password's length must be greater than 8");
            }

            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(password);

            // Return if the password
            // matched the ReGex
            if(!m.matches()){
                throw new Exception("Enter valid Password");
            }
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean validation(UserDAO user){
        UserValidation userValidation = new UserValidation();
        int counter = 0;
        if(userValidation.validateNameAndEmail(user.getUserName(), user.getEmail())){ counter++; }
        if(userValidation.validatePassword(user.getPassword())){ counter++; }
        return counter == 2;
    }
}
