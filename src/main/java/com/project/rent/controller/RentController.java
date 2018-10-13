package com.project.rent.controller;

import com.project.rent.model.Offer;
import com.project.rent.model.User;
import com.project.rent.model.Wish;
import com.project.rent.service.RentService;
import com.project.rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RentController {

    @Autowired
    UserService userService;

    @Autowired
    RentService rentService;

    @RequestMapping(value = "/rentimine")
    public ModelAndView rentimine() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName()); //leiame kasutaja objekt
        List<Offer> offerList = rentService.getOffersList(); // saame kõikide pakkumiste listi
        List<Wish> wishesList = rentService.getWishesList(); // saame kõikide soovide listi
        List<Offer> userOfferList = rentService.getUserOffersList(user.getId()); // saame autoriseeritud kasutaja pakkumised
        List<Wish> userWishList = rentService.getUserWishesList(user.getId()); // saame autoriseeritud kasutaja soovid

        modelAndView.addObject("offers", offerList);
        modelAndView.addObject("wishes", wishesList);
        modelAndView.addObject("myOffers", userOfferList);
        modelAndView.addObject("myWishes", userWishList);
        modelAndView.addObject(user);
        modelAndView.setViewName("rentimine");
        return modelAndView;
    }
}
