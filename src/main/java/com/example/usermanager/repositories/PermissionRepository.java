package com.example.usermanager.repositories;

import com.example.usermanager.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> getByGroups_Id(Long groupID);
}
