package com.service.lestplanit.models.security;

import jakarta.persistence.*;

/**
 * Entity class representing user roles.
 */
@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Enum representing the role name (e.g., ROLE_USER, ROLE_ADMIN)
    private ERole role;

    /**
     * Constructor to create a RoleEntity object with the given role.
     *
     * @param role The ERole representing the role name.
     */
    public RoleEntity(ERole role) {
        this.role = role;
    }

    /**
     * Retrieves the role name.
     *
     * @return The ERole representing the role name.
     */
    public ERole getRole() {
        return role;
    }

    /**
     * Sets the role name.
     *
     * @param role The ERole representing the role name to set.
     */
    public void setRole(ERole role) {
        this.role = role;
    }
}
