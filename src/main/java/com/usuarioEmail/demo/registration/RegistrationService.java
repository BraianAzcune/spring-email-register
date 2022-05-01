package com.usuarioEmail.demo.registration;

import com.usuarioEmail.demo.appuser.AppUser;
import com.usuarioEmail.demo.appuser.AppUserRole;
import com.usuarioEmail.demo.appuser.AppUserServices;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {
  private final AppUserServices appUserServices;
  private final EmailValidator emailValidator;

  public String register(RegistrationRequest request) {
    boolean isNotEmailValid = !emailValidator.test(request.getEmail());
    if (isNotEmailValid) {
      throw new IllegalStateException("email not valid");
    }
    return appUserServices.signUpUser(new AppUser(
        request.getEmail(),
        request.getPassword(),
        AppUserRole.USER));
  }
}
