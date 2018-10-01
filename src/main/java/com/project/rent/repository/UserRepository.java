package com.project.rent.repository;

import com.project.rent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { // siin asuvad meetodid, mille abil saame User tabelist infot otsida
    User findByEmail(String email);
    User findById(int id);
    User findByUsername(String username);

    @Query(
            value = "SELECT count(*) FROM userAuth",
            nativeQuery = true)
    String findCount();
}