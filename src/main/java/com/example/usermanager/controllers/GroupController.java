package com.example.usermanager.controllers;

import com.example.usermanager.dto.GroupDto;
import com.example.usermanager.entities.Group;
import com.example.usermanager.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/groups")
@PreAuthorize("hasAuthority('GROUPS')")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public Page<GroupDto> getAllByName(@Param("name") String name, Pageable pageable) {
        return groupService.getPageByName(name, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Group create(@Valid @RequestBody Group group) {
        return groupService.create(group);
    }


    @PutMapping
    public Group update(@Valid @RequestBody Group group) {
        return groupService.update(group);
    }

    @GetMapping(value = "/{id}")
    public Group get(@PathVariable("id") Long id) {
        return groupService.get(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        groupService.delete(id);
    }
}
