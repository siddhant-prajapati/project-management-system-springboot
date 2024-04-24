package com.projectExample.project.Service;

import com.projectExample.project.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * fetch data from UserService and bind it with UserPrincipal
 */
@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

  @Autowired
  private  UserServiceImpl userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      System.out.println("Inside Custom user Detail");
      var user = userService.findByEmail(username);
      return UserPrincipal.builder()
          .userId(Objects.requireNonNull(user.getBody()).getUserId())
          .email(user.getBody().getEmail())
          .authorities(List.of(new SimpleGrantedAuthority("USER")))
          .password(user.getBody().getPassword())
          .build();
    } catch (UsernameNotFoundException e){
      throw new UsernameNotFoundException("Given User not Fount");
    }
  }
}
