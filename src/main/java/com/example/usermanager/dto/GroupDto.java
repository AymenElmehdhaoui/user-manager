package com.example.usermanager.dto;

import com.example.usermanager.entities.Permission;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GroupDto {
    private Long id;
    private String name;
    private List<Permission> permissions;
}
