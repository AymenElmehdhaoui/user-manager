package com.example.usermanager.repositories;

import com.example.usermanager.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
    public boolean existsByEmail(String email);
    public Page<User> findByFirstNameContainingAndLastNameContainingAndEmailContaining
            (String firstName, String lastName, String email, Pageable pageable);
}
