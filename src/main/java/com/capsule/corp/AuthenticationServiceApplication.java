package com.capsule.corp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SuppressWarnings("HideUtilityClassConstructor")
public class AuthenticationServiceApplication {

  public static void main(final String[] args) {
    SpringApplication.run(AuthenticationServiceApplication.class, args);
  }
}
