package com.usuarioEmail.demo.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;

import com.usuarioEmail.demo.appuser.AppUser;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
  private final ConfirmationTokenRepository confirmationTokenRepository;

  public void save(ConfirmationToken confirmationToken) {
    confirmationTokenRepository.save(confirmationToken);
  }

  public Optional<ConfirmationToken> getToken(String token) {
    return confirmationTokenRepository.findByToken(token);
  }

  public void setConfirmed(ConfirmationToken confirmationToken) {
    confirmationToken.setConfirmedAt(LocalDateTime.now());
    confirmationTokenRepository.save(confirmationToken);
  }


  public void delete(ConfirmationToken confirmationToken) {
    confirmationTokenRepository.delete(confirmationToken);
  }
}
