package com.example.usermanager.dto;

import com.example.usermanager.entities.Group;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateUserDto {
    @NotNull
    private Long id;

    @NotNull
    @Size(min = 2, max = 24)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 24)
    private String lastName;

    @NotNull
    @Email
    private String email;

    private Group group;
}
