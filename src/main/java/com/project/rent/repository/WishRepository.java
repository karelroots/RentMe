package com.project.rent.repository;

import com.project.rent.model.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wish, Integer> { // siin asuvad meetodid, mille abil saame Role tabelist andmeid otsida
    Wish findByUserId(int id);
    Wish findByItemName(String name);
    Wish findWishById(int id);

    @Query(
            value = "SELECT wishCount from userCounts where user_id = ?1", // userCounts on andmebaasis vaade
            nativeQuery = true)
    int findWishCount(int id);


}
