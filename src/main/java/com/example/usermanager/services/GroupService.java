package com.example.usermanager.services;

import com.example.usermanager.dto.GroupDto;
import com.example.usermanager.entities.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {

    Page<GroupDto> getPageByName(String name, Pageable pageable);

    Group create(Group group);

    Group get(Long id);

    Group update(Group group);

    void delete(Long id);
}
