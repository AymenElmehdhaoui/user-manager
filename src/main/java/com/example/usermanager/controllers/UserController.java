package com.example.usermanager.controllers;

import com.example.usermanager.dto.SignupDto;
import com.example.usermanager.dto.UpdateUserDto;
import com.example.usermanager.dto.UserDto;
import com.example.usermanager.entities.User;
import com.example.usermanager.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAuthority('USERS')")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public Page<UserDto> getAllByName(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("email") String email,
            Pageable pageable) {

        return userService.getPageBy(firstName, lastName, email, pageable)
                .map(this::convertToUserDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@Valid @RequestBody SignupDto signupDTO) {
        User user = userService.create(signupDTO);
        return convertToUserDto(user);
    }


    @PutMapping
    public UserDto update(@Valid @RequestBody UpdateUserDto updateUserDto) {
        User user = userService.update(updateUserDto);
        return convertToUserDto(user);
    }

    @GetMapping(value = "/{id}")
    public UserDto get(@PathVariable("id") Long id) {
        User user = userService.get(id);
        return convertToUserDto(user);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }


    private UserDto convertToUserDto(User user) {
        return mapper.map(user, UserDto.class);
    }
}
