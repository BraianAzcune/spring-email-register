package com.usuarioEmail.demo.appuser;

import java.time.LocalDateTime;
import java.util.UUID;

import com.usuarioEmail.demo.registration.token.ConfirmationToken;
import com.usuarioEmail.demo.registration.token.ConfirmationTokenService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserServices implements UserDetailsService {
  private final static String USER_NOT_FOUND = "User with email %s not found";
  private final UserRepository appUserRepository;
  private final ConfirmationTokenService confirmationTokenService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return appUserRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
  }

  public String signUpUser(AppUser user){
    var userBack = appUserRepository.findByEmail(user.getEmail());
    boolean exist = userBack.isPresent();

    if(exist){
      // check if are equal user with userBack, if it is, and expiryDate pass, we
      // continue.
      throw new IllegalStateException("User with email " + user.getEmail() + " already exists");
    }
    String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
    user.setPassword(encryptedPassword);
    appUserRepository.save(user);

    String token = UUID.randomUUID().toString();
    ConfirmationToken confirmationToken = new ConfirmationToken(
        token,
        LocalDateTime.now().plusMinutes(15),
        user);
    confirmationTokenService.save(confirmationToken);

    return token;
  }

  public void enableAppUser(String email) {
    var appUser = appUserRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalStateException("User with email " + email + " not found"));
    appUser.setEnabled(true);
    appUserRepository.save(appUser);
  }
}
