package com.example.usermanager.controllers;

import com.example.usermanager.entities.Permission;
import com.example.usermanager.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/permissions")
@PreAuthorize("hasAuthority('PERMISSIONS')")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public List<Permission> findAll() {
        return permissionService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Permission create(@Valid @RequestBody Permission permission) {
        return permissionService.create(permission);
    }

    @PutMapping
    public Permission update(@Valid @RequestBody Permission permission) {
        return permissionService.update(permission);
    }

    @GetMapping(value = "/{id}")
    public Permission get(@PathVariable("id") Long id) {
        return permissionService.get(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        permissionService.delete(id);
    }
}
