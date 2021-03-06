package com.example.usermanager.repositories;

import com.example.usermanager.entities.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends BaseRepository<Group, Long> {
    Page<Group> findByNameContains(String name, Pageable pageable);
    Group findByName(String name);
}
