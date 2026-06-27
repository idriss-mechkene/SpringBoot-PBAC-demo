package org.example.authproject.repository;

import java.util.Optional;
import org.example.authproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);


}
