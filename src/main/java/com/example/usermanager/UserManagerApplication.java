package com.example.usermanager;

import com.example.usermanager.entities.Group;
import com.example.usermanager.entities.Permission;
import com.example.usermanager.repositories.GroupRepository;
import com.example.usermanager.repositories.PermissionRepository;
import com.example.usermanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
}
)
public class UserManagerApplication implements CommandLineRunner {

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(UserManagerApplication.class, args);
    }

    @Override
    public void run(String... args) {

        /*Permission permission = new Permission("PERMISSIONS", "Permissions");
        Permission permission1 = new Permission("GROUPS", "Groups");
        Permission permission2 = new Permission("USERS", "Users");

        permissionRepository.save(permission);
        permissionRepository.save(permission1);
        permissionRepository.save(permission2);

        List<Permission> permissionsAdmin = new ArrayList<>();
        permissionsAdmin.add(permission);
        permissionsAdmin.add(permission1);
        permissionsAdmin.add(permission2);

        Group groupAdmin = new Group("Admins", permissionsAdmin);

        List<Permission> permissionsUser = new ArrayList<>();
        permissionsUser.add(permission);
        permissionsUser.add(permission2);

        Group groupUser = new Group("Users", permissionsUser);

        groupRepository.save(groupUser);
        groupRepository.save(groupAdmin);*/
    }
}
