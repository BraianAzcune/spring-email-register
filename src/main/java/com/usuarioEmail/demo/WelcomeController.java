package com.usuarioEmail.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WelcomeController {

  @GetMapping
  public String confirm() {
    return "Welcome to Spring Boot";
  }

}
