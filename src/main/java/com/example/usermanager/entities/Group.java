package com.example.usermanager.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "groups")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Group extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 24, message = "name size must be between 2 and 24")
    @NotNull
    @Column(length = 24, nullable = false)
    private String name;

    @ManyToMany
    private List<Permission> permissions = new ArrayList<>();

    public Group(String name, List<Permission> permissions) {
        this.name = name;
        this.permissions = permissions;
    }
}
