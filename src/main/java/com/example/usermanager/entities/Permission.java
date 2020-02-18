package com.example.usermanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "permissions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 24, message = "label size must be between 2 and 24")
    @NotNull
    @Column(length = 24, nullable = false)
    private String label;

    @Size(min = 2, max = 24, message = "name size must be between 2 and 24")
    @NotNull
    @Column(length = 24, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "permissions")
    @JsonIgnore
    private List<Group> groups = new ArrayList<>();

    public Permission(String label, String name) {
        this.label = label;
        this.name = name;
    }
}
