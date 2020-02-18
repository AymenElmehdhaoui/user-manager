package com.example.usermanager.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 24)
    @Column(name = "first_name", length = 24, nullable = false)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 24)
    @Column(name = "last_name", length = 24, nullable = false)
    private String lastName;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @OneToOne
    private Group group;

    public User(@NotNull @Size(min = 2, max = 24) String firstName, @NotNull @Size(min = 2, max = 24) String lastName, @NotNull @Email String email, @NotNull String password, Group group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.group = group;
    }
}
