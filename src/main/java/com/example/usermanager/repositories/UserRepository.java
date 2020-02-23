package com.example.usermanager.repositories;

import com.example.usermanager.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
    Page<User> findByFirstNameContainingAndLastNameContainingAndEmailContaining
            (String firstName, String lastName, String email, Pageable pageable);
}
