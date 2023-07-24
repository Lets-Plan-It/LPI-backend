package com.service.lestplanit.repositories.security;

import com.service.lestplanit.models.security.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing Role data.
 */
@Repository
public interface RoleRepository extends CrudRepository<UserEntity, Long> {
}
