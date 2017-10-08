package com.rr.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author roman.rudenko on 25-May-16.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
