package com.project.rent.repository;

import com.project.rent.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> { // siin asuvad meetodid, mille abil saame Role tabelist andmeid otsida
    Role findByRole(String roll);

}