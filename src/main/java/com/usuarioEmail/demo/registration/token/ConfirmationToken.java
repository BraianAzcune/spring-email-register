package com.usuarioEmail.demo.registration.token;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.usuarioEmail.demo.appuser.AppUser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

  @Id
  @SequenceGenerator(name = "ConfirmationTokenSeq", sequenceName = "CONFIRMATION_TOKEN_SEQ", allocationSize = 1)
  @GeneratedValue(generator = "ConfirmationTokenSeq", strategy = GenerationType.SEQUENCE)
  private Long id;
  @Column(nullable = false, unique = true)
  private String token;
  @Column(nullable = false)
  private LocalDateTime expiryDate;
  private LocalDateTime confirmedAt;

  @ManyToOne
  @JoinColumn(nullable = false, name = "app_user_id")
  private AppUser appUser;

  public ConfirmationToken(String token, LocalDateTime expiryDate, AppUser appuser) {
    this.token = token;
    this.expiryDate = expiryDate;
    this.appUser = appuser;
  }

}
