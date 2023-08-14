package org.soni.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface RoleRepository<Role> extends JpaRepository<Role, Integer>, Serializable {
    long serialVersionUID = 7874493593505141603L;

    Role findByRoleIgnoreCase(String role);

    Role findByNameIgnoreCase(String name);

}