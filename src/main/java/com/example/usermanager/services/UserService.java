package com.example.usermanager.services;

import com.example.usermanager.dto.SignupDto;
import com.example.usermanager.dto.UpdateUserDto;
import com.example.usermanager.entities.Group;
import com.example.usermanager.entities.User;
import com.example.usermanager.exceptions.ConflictException;
import com.example.usermanager.exceptions.NotFoundException;
import com.example.usermanager.repositories.GroupRepository;
import com.example.usermanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Page<User> getPageBy(String firstName, String lastName, String email, Pageable pageable) {
        if (firstName == null)
            firstName = "";
        if (lastName == null)
            lastName = "";
        if (email == null)
            email = "";

        Page<User> users =
                userRepository
                        .findByFirstNameContainingAndLastNameContainingAndEmailContaining(firstName, lastName, email, pageable);
        return users;
    }

    public User create(SignupDto signupDTO) {

        if (userRepository.existsByEmail(signupDTO.getEmail())) {
            throw new ConflictException("Email Address already in use!");
        }

        // Creating user account
        User user = new User();
        user.setFirstName(signupDTO.getFirstName());
        user.setLastName(signupDTO.getLastName());
        user.setEmail(signupDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        Group group = groupRepository.findById(1L).orElse(null);
        user.setGroup(group);
        return userRepository.save(user);
    }

    public User update(UpdateUserDto updateUserDto) {
        User user = get(updateUserDto.getId());

        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setEmail(updateUserDto.getEmail());
        user.setGroup(updateUserDto.getGroup());

        userRepository.save(user);
        return user;
    }

    public User get(Long id) {
        throwJump:
        {
            if (id == null)
                break throwJump;
            User user = userRepository.findById(id).orElse(null);
            if (user == null)
                break throwJump;
            return user;
        }

        throw new NotFoundException("Permission doesn't exist");
    }

    public void delete(Long id) {
        get(id);
        userRepository.deleteById(id);
    }
}
