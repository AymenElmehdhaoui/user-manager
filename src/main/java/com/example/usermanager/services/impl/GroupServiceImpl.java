package com.example.usermanager.services.impl;

import com.example.usermanager.dto.GroupDto;
import com.example.usermanager.entities.Group;
import com.example.usermanager.exceptions.ConflictException;
import com.example.usermanager.exceptions.NotFoundException;
import com.example.usermanager.repositories.GroupRepository;
import com.example.usermanager.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Page<GroupDto> getPageByName(String name, Pageable pageable) {
        if (name == null)
            name = "";

        Page<GroupDto> groupPage =
                groupRepository.findByNameContains(name, pageable)
                        .map(group ->
                                new GroupDto(group.getId(), group.getName(), group.getPermissions())
                        );
        return groupPage;
    }

    public Group create(Group group) {
        if (group.getId() != null) {
            throw new ConflictException("Group id must be empty to create");
        }
        return groupRepository.save(group);
    }


    public Group get(Long id) {
        throwJump:
        {
            if (id == null)
                break throwJump;
            Group group = groupRepository.findById(id).orElse(null);
            if (group == null)
                break throwJump;
            return group;
        }

        throw new NotFoundException("Permission doesn't exist");
    }

    public Group update(Group group) {
        Group toUpdate = get(group.getId());

        toUpdate.setName(group.getName());
        toUpdate.setPermissions(group.getPermissions());

        return groupRepository.save(toUpdate);
    }

    public void delete(Long id) {
        get(id);
        groupRepository.deleteById(id);
    }
}
