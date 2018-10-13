package com.project.rent.service;

import com.project.rent.model.Offer;
import com.project.rent.model.Wish;
import com.project.rent.repository.OfferRepository;
import com.project.rent.repository.UserRepository;
import com.project.rent.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("rentService")
public class RentService {
    private OfferRepository offerRepository;
    private WishRepository wishRepository;
    private UserRepository userRepository;

    @Autowired
    public RentService(OfferRepository offerRepository, WishRepository wishRepository, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.wishRepository = wishRepository;
        this.userRepository = userRepository;
    }

    public Offer findOfferByUserId(int id) {
        return offerRepository.findByUserId(id);
    }

    public Offer findOfferById(int id) {
        return offerRepository.findOfferById(id);
    }

    public Wish findWishByUserId(int id) {
        return wishRepository.findByUserId(id);
    }

    public Wish findWishById(int id) {
        return wishRepository.findWishById(id);
    }

    public void saveOffer(Offer offer) {
        offerRepository.save(offer);
    }

    public void saveWish(Wish wish) {
        wishRepository.save(wish);
    }

    public void removeOffer(Offer offer) {
        offerRepository.delete(offer);
    }

    public void removeWish(Wish wish) {
        wishRepository.delete(wish);
    }

    public List<Offer> getOffersList() {
        List<Offer> offers = offerRepository.findAll();

        for(Offer offer:offers) {
            // lisame pakkumisele kasutaja id-le vastava kasutajanime
            offer.setUserName(userRepository.findById(offer.getUserId()).getUsername());
        }
        return offers;
    }

    public List<Wish> getWishesList() {
        List<Wish> wishes = wishRepository.findAll();

        for(Wish wish:wishes) {
            // lisame soovile kasutaja id-le vastava kasutajanime
            wish.setUserName(userRepository.findById(wish.getUserId()).getUsername());
        }

        return wishes;
    }

    public List<Offer> getUserOffersList(int id) {
        List<Offer> offers = offerRepository.findAll();
        List<Offer> userOffers = new ArrayList<>();

        for(Offer offer:offers) {
            if(offer.getUserId() == id) {
                userOffers.add(offer);
            }
        }

        return userOffers;
    }

    public List<Wish> getUserWishesList(int id) {
        List<Wish> wishes = wishRepository.findAll();
        List<Wish> userWishes = new ArrayList<>();

        for(Wish wish:wishes) {
            if(wish.getUserId() == id) {
                userWishes.add(wish);
            }
        }

        return userWishes;
    }

}
