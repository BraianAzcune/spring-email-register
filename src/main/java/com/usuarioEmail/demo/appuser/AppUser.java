package com.usuarioEmail.demo.appuser;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class AppUser implements UserDetails  {
  @Id
  @SequenceGenerator(name = "appUserSeq", sequenceName = "APP_USER_SEQ", allocationSize = 1)
  @GeneratedValue(generator = "appUserSeq", strategy = GenerationType.SEQUENCE)
  private Long id;
  private String email;
  private String password;
  @Enumerated(EnumType.STRING)
  private AppUserRole appUserRole;
  private Boolean locked = false;
  private Boolean enabled = false;

  public AppUser(String email, String password, AppUserRole appUserRole) {
    this.email = email;
    this.password = password;
    this.appUserRole = appUserRole;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
    return Collections.singletonList(authority);
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    //usamos el email como nombre de usuario para que se logue el UserDetails
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
  
}
