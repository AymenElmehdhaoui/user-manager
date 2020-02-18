package com.example.usermanager.security;

import com.example.usermanager.entities.Group;
import com.example.usermanager.entities.Permission;
import com.example.usermanager.entities.User;
import com.example.usermanager.repositories.PermissionRepository;
import com.example.usermanager.repositories.UserRepository;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found with username: " + username);

        JwtUser jwtUser = new JwtUser(user);

        try {
            jwtUser.getAuthorities();
        } catch (LazyInitializationException ex) {
            List<Permission> permissions = permissionRepository.getByGroups_Id(user.getGroup().getId());
            Group group = user.getGroup();
            group.setPermissions(permissions);
            user.setGroup(group);
            jwtUser = new JwtUser(user);
        }
        return jwtUser;
    }
}
