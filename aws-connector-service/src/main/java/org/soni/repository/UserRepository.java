package org.soni.repository;


import org.soni.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>, Serializable {
    long serialVersionUID = 7874493593505141603L;

    Optional<User> findByUserIdIgnoreCase(String userId);

    Optional<User> findByEmailIgnoreCase(String email);

    Optional<User> findByUsernameIgnoreCase(String username);

    Optional<User> findByDigitalIdIgnoreCase(String digitalId);
}