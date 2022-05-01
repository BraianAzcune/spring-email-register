package com.usuarioEmail.demo.appuser;

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
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return appUserRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
  }

  public String signUpUser(AppUser user){
    boolean exist = appUserRepository.findByEmail(user.getEmail()).isPresent();
    if(exist){
      throw new IllegalStateException("User with email " + user.getEmail() + " already exists");
    }
    String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
    user.setPassword(encryptedPassword);
    appUserRepository.save(user);
    // TODO: send confirmation token
    return "it works";
  }
}
