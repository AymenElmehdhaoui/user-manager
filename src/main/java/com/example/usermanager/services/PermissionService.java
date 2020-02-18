package com.example.usermanager.services;

import com.example.usermanager.entities.Permission;
import com.example.usermanager.exceptions.ConflictException;
import com.example.usermanager.exceptions.NotFoundException;
import com.example.usermanager.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    public Permission create(Permission permission) {
        if (permission.getId() != null) {
            throw new ConflictException("Permission id must be empty to create");
        }
        return permissionRepository.save(permission);
    }

    public Permission update(Permission permission) {
        Permission toUpdate = get(permission.getId());

        toUpdate.setLabel(permission.getLabel());
        toUpdate.setName(permission.getName());
        toUpdate.setGroups(permission.getGroups());

        return permissionRepository.save(toUpdate);
    }

    public Permission get(Long id) {
        throwJump:
        {
            if (id == null)
                break throwJump;
            Permission permission = permissionRepository.findById(id).orElse(null);
            if (permission == null)
                break throwJump;
            return permission;
        }

        throw new NotFoundException("Permission doesn't exist");
    }

    public void delete(Long id) {
        get(id);
        permissionRepository.deleteById(id);
    }
}
