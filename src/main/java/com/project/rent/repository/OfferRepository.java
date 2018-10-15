package com.project.rent.repository;

import com.project.rent.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> { // siin asuvad meetodid, mille abil saame Role tabelist andmeid otsida
    Offer findByUserId(int id);
    Offer findByItemName(String name);
    Offer findOfferById(int id);

    @Query(
            value = "SELECT offerCount from userCounts where user_id = ?1", // userCounts on andmebaasis vaade
            nativeQuery = true)
    int findOfferCount(int id);

}
