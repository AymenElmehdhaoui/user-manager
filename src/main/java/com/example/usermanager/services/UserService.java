package com.example.usermanager.services;

import com.example.usermanager.dto.SignupDto;
import com.example.usermanager.dto.UpdateUserDto;
import com.example.usermanager.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<User> getPageBy(String firstName, String lastName, String email, Pageable pageable);

    User create(SignupDto signupDTO);

    User update(UpdateUserDto updateUserDto);

    User get(Long id);

    void delete(Long id);
}
