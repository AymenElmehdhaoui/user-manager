package com.example.usermanager.repositories;

import com.example.usermanager.entities.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Page<Group> findByNameContains(String name, Pageable pageable);
    Group findByName(String name);
}
