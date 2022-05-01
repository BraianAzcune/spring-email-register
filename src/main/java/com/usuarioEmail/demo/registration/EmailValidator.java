package com.usuarioEmail.demo.registration;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String>{
  private final Pattern  patternValidEmailRFC5322 = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
  @Override
  public boolean test(String t) {
    return patternValidEmailRFC5322.matcher(t).matches();
  }
}
