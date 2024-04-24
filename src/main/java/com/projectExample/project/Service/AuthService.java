package com.projectExample.project.Service;

import com.projectExample.project.model.LoginResponse;
import com.projectExample.project.security.JwtIssuer;
import com.projectExample.project.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @implNote Contain all implementation and steps how use authenticate
 */
@Service
public class AuthService {

  @Autowired
  private JwtIssuer jwtIssuer;


  @Autowired
  AuthenticationManager authenticationManager;
  public LoginResponse attemptLogin(String email, String password){
    try{

      // check value of email and password with UserPrincipal's email and encrypted password
      var authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(email, password)
      );

      // set authentication to SecurityContextHolder
      SecurityContextHolder.getContext().setAuthentication(authentication);
      var principal = (UserPrincipal) authentication.getPrincipal();

      var roles = principal.getAuthorities().stream()
          .map(GrantedAuthority::getAuthority)
          .toList();
      System.out.println(roles);

      //call issuer() to generate new token
      var token = jwtIssuer.issue(principal.getUserId(), principal.getEmail(),roles);

      var userID = principal.getUserId();
      System.out.println(userID);

      return LoginResponse
          .builder()
          .userId(userID)
          .accessToken(token)
          .build();
    } catch (Exception e){
      System.out.println("Error is \n"+e.getMessage());
      return null;
    }
  }
}
