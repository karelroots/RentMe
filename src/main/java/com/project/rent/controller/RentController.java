package com.project.rent.controller;

import com.project.rent.model.*;
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
        List<Contract> userOwnerContractList = rentService.getUserOwnerContractList(user.getId());
        List<Contract> userRentContractList = rentService.getUserRentContractList(user.getId());
        List<ContractOffer> userOwnerContractOfferList = rentService.getUserOwnerContractOfferList(user.getId());
        List<ContractOffer> userRentContractOfferList = rentService.getUserRentContractOfferList(user.getId());
        Offer offer = new Offer();
        Wish wish = new Wish();

        modelAndView.addObject("offers", offerList);
        modelAndView.addObject("wishes", wishesList);
        modelAndView.addObject("myOffers", userOfferList);
        modelAndView.addObject("myWishes", userWishList);
        modelAndView.addObject("myOwnerContracts", userOwnerContractList);
        modelAndView.addObject("myRentContracts", userRentContractList);
        modelAndView.addObject("myOwnerContractOffers", userOwnerContractOfferList);
        modelAndView.addObject("myRentContractOffers", userRentContractOfferList);
        modelAndView.addObject(offer);
        modelAndView.addObject(wish);
        modelAndView.addObject(user);
        modelAndView.setViewName("rentimine");

        return modelAndView;
    }

    @RequestMapping(value = "rentimine/acceptContractOffer")
    public String acceptContractOffer(@RequestParam int id, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        LocalDateTime ldt = LocalDateTime.now();
        ContractOffer coffer = rentService.findContractOfferById(id);
        Offer offer = rentService.findOfferById(coffer.getOfferId());
        Wish wish = rentService.findWishById(coffer.getWishId());
        if(offer != null) {
            rentService.removeOffer(offer);
        }
        if(wish != null) {
            rentService.removeWish(wish);
        }

        Contract contract = new Contract();
        contract.setItemDesc(coffer.getItemDesc());
        contract.setItemName(coffer.getItemName());
        contract.setOwnerId(coffer.getUserId());
        contract.setUserId(user.getId());
        //contract.setPictureName(coffer.getPictureName());
        contract.setLocation(coffer.getLocation());
        contract.setRentDateTime(ldt.toString());
        contract.setReturnDateTime(coffer.getReturnDateTime());
        contract.setOwner(userService.findUserById(contract.getOwnerId()).getUsername());
        contract.setUserName(userService.findUserById(contract.getUserId()).getUsername());

        rentService.saveContract(contract);
        rentService.removeContractOffer(coffer);

        return "redirect:/rentimine";
    }

    @RequestMapping(value = "rentimine/rentOffer")
    public String rentOffer(@RequestParam int id, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LocalDateTime ldt = LocalDateTime.now();
        Offer offer = rentService.findOfferById(id);

        ContractOffer coffer = new ContractOffer();

        coffer.setItemDesc(offer.getItemDesc());
        coffer.setItemName(offer.getItemName());
        coffer.setOwnerId(offer.getUserId());
        coffer.setUserId(userService.findUserByEmail(auth.getName()).getId());
        //coffer.setPictureName(offer.getPictureName());
        coffer.setLocation(offer.getLocation());
        coffer.setOfferDateTime(ldt.toString());
        coffer.setReturnDateTime(offer.getReturnDateTime());
        coffer.setUserName(userService.findUserById(coffer.getUserId()).getUsername());
        coffer.setOwner(userService.findUserById(coffer.getOwnerId()).getUsername());
        coffer.setOfferId(id);

        rentService.saveContractOffer(coffer);

        return "redirect:/rentimine#pakkumised";
    }

    @RequestMapping(value = "rentimine/offerWish")
    public String offerWish(@RequestParam int id, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LocalDateTime ldt = LocalDateTime.now();
        Wish wish = rentService.findWishById(id);

        ContractOffer coffer = new ContractOffer();

        coffer.setItemDesc(wish.getItemDesc());
        coffer.setItemName(wish.getItemName());
        coffer.setOwnerId(wish.getUserId());
        coffer.setUserId(userService.findUserByEmail(auth.getName()).getId());
        //coffer.setPictureName(offer.getPictureName());
        coffer.setLocation(wish.getLocation());
        coffer.setOfferDateTime(ldt.toString());
        //coffer.setReturnDateTime(wish.getReturnDateTime());
        coffer.setUserName(userService.findUserById(coffer.getUserId()).getUsername());
        coffer.setOwner(userService.findUserById(coffer.getOwnerId()).getUsername());
        coffer.setWishId(id);

        rentService.saveContractOffer(coffer);

        return "redirect:/rentimine#pakkumised";
    }

    @RequestMapping(value = "rentimine/removeContract")
    public String removeContract(@RequestParam int id, RedirectAttributes redirectAttributes) {
        rentService.removeContract(rentService.findContractById(id));

        return "redirect:/rentimine#sinu-pakkumised";  // muuda seda!
    }

    @RequestMapping(value = "rentimine/removeContractOffer")
    public String removeContractOffer(@RequestParam int id, RedirectAttributes redirectAttributes) {
        rentService.removeContractOffer(rentService.findContractOfferById(id));

        return "redirect:/rentimine#sinu-pakkumised";  // muuda seda!
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
