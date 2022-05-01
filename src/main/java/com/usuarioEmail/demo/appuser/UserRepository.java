package com.usuarioEmail.demo.appuser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface UserRepository  extends JpaRepository<AppUser,Long>{
  Optional<AppUser> findByEmail(String email);
}
