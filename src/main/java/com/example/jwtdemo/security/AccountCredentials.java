package com.example.jwtdemo.security;

import lombok.Data;

@Data
public class AccountCredentials {
  private String username;
  private String password;
}
