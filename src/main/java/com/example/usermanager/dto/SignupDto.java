package com.example.usermanager.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SignupDto extends LoginDto {

    @NotNull
    @Size(min = 2, max = 24)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 24)
    private String lastName;
}
