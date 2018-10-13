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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
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
        Offer offer = new Offer();
        Wish wish = new Wish();

        modelAndView.addObject("offers", offerList);
        modelAndView.addObject("wishes", wishesList);
        modelAndView.addObject("myOffers", userOfferList);
        modelAndView.addObject("myWishes", userWishList);
        modelAndView.addObject(offer);
        modelAndView.addObject(wish);
        modelAndView.addObject(user);
        modelAndView.setViewName("rentimine");
        return modelAndView;
    }

    @RequestMapping(value = "rentimine/addOffer")
    public String addOffer(@Valid Offer offer, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LocalDateTime ldt = LocalDateTime.now();

        offer.setDatetime(ldt.toString()); // lisame pakkumisele postitamise aja
        offer.setUserId(userService.findUserByEmail(auth.getName()).getId()); // lisame pakkumisele kasutaja id

        rentService.saveOffer(offer); // salvestame pakkumise andmebaasi
        redirectAttributes.addFlashAttribute("postSuccess", "Pakkumine on edukalt postitatud!");

        return "redirect:/rentimine#lisa-pakkumine";
    }

    @RequestMapping(value = "rentimine/addWish")
    public String addWish(@Valid Wish wish, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LocalDateTime ldt = LocalDateTime.now();

        wish.setDatetime(ldt.toString()); // lisame soovile postitamise aja
        wish.setUserId(userService.findUserByEmail(auth.getName()).getId()); // lisame soovile kasutaja id

        rentService.saveWish(wish); // salvestame pakkumise andmebaasi
        redirectAttributes.addFlashAttribute("postSuccess", "Soov on edukalt postitatud!");

        return "redirect:/rentimine#lisa-soov";
    }

    @RequestMapping(value ="rentimine/removeOffer")
    public String removeOffer(@RequestParam int id, RedirectAttributes redirectAttributes) {

        System.out.println("Offer id on: "+id);
        rentService.removeOffer(rentService.findOfferById(id));

        return "redirect:/rentimine#sinu-pakkumised";
    }

    @RequestMapping(value ="rentimine/removeWish")
    public String removeWish(@RequestParam int id, RedirectAttributes redirectAttributes) {

        System.out.println("Wish id on: "+id);
        rentService.removeWish(rentService.findWishById(id));

        return "redirect:/rentimine#sinu-soovid";
    }
}
