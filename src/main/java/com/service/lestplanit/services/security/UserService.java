package com.service.lestplanit.services.security;

import com.service.lestplanit.models.security.ERole;
import com.service.lestplanit.models.security.RoleEntity;
import com.service.lestplanit.models.security.UserEntity;
import com.service.lestplanit.repositories.security.RoleRepository;
import com.service.lestplanit.repositories.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class to handle user-related operations.
 */
@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves a list of all users.
     *
     * @return List of UserEntity objects representing all users.
     */
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Creates a new user with the specified roleNames.
     *
     * @param roleNames A Set of roleNames to assign to the user.
     * @param user  The UserEntity object representing the new user.
     * @return UserEntity representing the newly created user.
     */
    public UserEntity createUser(Set<String> roleNames, UserEntity user) {

        Set<RoleEntity> dbRoles = roleNames
                .stream().map(role -> new RoleEntity(ERole.valueOf(role)))
                .collect(Collectors.toSet());
        user.setRoles(dbRoles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
