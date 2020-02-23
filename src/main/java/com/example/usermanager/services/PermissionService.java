package com.example.usermanager.services;

import com.example.usermanager.entities.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findAll();

    Permission create(Permission permission);

    Permission update(Permission permission);

    Permission get(Long id);

    void delete(Long id);
}
