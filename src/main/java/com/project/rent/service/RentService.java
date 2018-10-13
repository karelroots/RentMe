package com.project.rent.service;

import com.project.rent.model.Offer;
import com.project.rent.model.Wish;
import com.project.rent.repository.OfferRepository;
import com.project.rent.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rentService")
public class RentService {
    private OfferRepository offerRepository;
    private WishRepository wishRepository;

    @Autowired
    public RentService(OfferRepository offerRepository, WishRepository wishRepository) {
        this.offerRepository = offerRepository;
        this.wishRepository = wishRepository;
    }

    public Offer findOfferByUserId(int id) {
        return offerRepository.findByUserId(id);
    }

    public Wish findWishByUserId(int id) {
        return wishRepository.findByUserId(id);
    }

    public void saveOffer(Offer offer) {
        offerRepository.save(offer);
    }

    public void saveWish(Wish wish) {
        wishRepository.save(wish);
    }


}
