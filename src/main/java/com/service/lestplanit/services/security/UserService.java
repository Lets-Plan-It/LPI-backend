package com.service.lestplanit.services.security;

import com.service.lestplanit.models.security.RoleEntity;
import com.service.lestplanit.models.security.UserEntity;
import com.service.lestplanit.repositories.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Service class to handle user-related operations.
 */
@Service
public class UserService {

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
     * Creates a new user with the specified roles.
     *
     * @param roles A Set of RoleEntity representing the roles to assign to the user.
     * @param user  The UserEntity object representing the new user.
     * @return UserEntity representing the newly created user.
     */
    public UserEntity createUser(Set<RoleEntity> roles, UserEntity user) {
        user.setRoles(roles);
        return userRepository.save(user);
    }
}
