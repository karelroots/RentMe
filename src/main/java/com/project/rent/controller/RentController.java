package com.project.rent.controller;

import com.project.rent.model.*;
import com.project.rent.service.RentService;
import com.project.rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class RentController {

    @Autowired
    UserService userService;

    @Autowired
    RentService rentService;

    private static String UPLOADED_FOLDER = System.getProperty("user.dir")+"/src/main/webapp/resources/images/"; //TESTSYSTEM
    //private static String UPLOADED_FOLDER = "/opt/tomcat/webapps/rent/resources/images/"; //DEPLOYMENT

    @RequestMapping(value = "/rentimine")
    public ModelAndView rentimine() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName()); //leiame kasutaja objekt
        List<Offer> offerList = rentService.getOffersList(); // saame kõikide pakkumiste listi
        List<Wish> wishesList = rentService.getWishesList(); // saame kõikide soovide listi
        List<Offer> userOfferList = rentService.getUserOffersList(user.getId()); // saame autoriseeritud kasutaja pakkumised
        List<Wish> userWishList = rentService.getUserWishesList(user.getId()); // saame autoriseeritud kasutaja soovid
        List<Invoice> userInvoiceList = rentService.getUserInvoiceList(user.getId()); // saame autoriseeritud kasutaja arved
        List<Contract> userOwnerContractList = rentService.getUserOwnerContractList(user.getId());
        List<Contract> userRentContractList = rentService.getUserRentContractList(user.getId());
        List<Contract> userContractList = new ArrayList<>(userOwnerContractList);
        userContractList.addAll(userRentContractList);
        List<ContractOffer> userOwnerContractOfferList = rentService.getUserOwnerContractOfferList(user.getId());
        List<ContractOffer> userRentContractOfferList = rentService.getUserRentContractOfferList(user.getId());
        List<ContractOffer> userContractOfferList = new ArrayList<>(userOwnerContractOfferList);
        userContractOfferList.addAll(userRentContractOfferList);
        Offer offer = new Offer();
        Wish wish = new Wish();

        modelAndView.addObject("offers", offerList);
        modelAndView.addObject("wishes", wishesList);
        modelAndView.addObject("myOffers", userOfferList);
        modelAndView.addObject("myWishes", userWishList);
        modelAndView.addObject("myInvoices", userInvoiceList);
        modelAndView.addObject("myContracts", userContractList);
        modelAndView.addObject("myContractOffers", userContractOfferList);
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
        if(offer != null) {  // eemaldame pakkumise avalike pakkumiste hulgast
            rentService.removeOffer(offer);
        }
        if(wish != null) {  // eemaldame soovi avalike pakkumiste hulgast
            rentService.removeWish(wish);
        }

        Contract contract = new Contract();
        contract.setItemDesc(coffer.getItemDesc());
        contract.setItemName(coffer.getItemName());
        contract.setOwnerId(coffer.getOwnerId());
        contract.setUserId(coffer.getUserId());
        contract.setPictureName(coffer.getPictureName());
        contract.setPrice(coffer.getPrice());
        contract.setLocation(coffer.getLocation());
        contract.setRentDateTime(ldt.toString());
        contract.setReturnDate(coffer.getReturnDate());
        contract.setOwner(userService.findUserById(contract.getOwnerId()).getUsername());
        contract.setUserName(userService.findUserById(contract.getUserId()).getUsername());

        Invoice invoice = new Invoice();
        invoice.setDatetime(ldt.toString());
        invoice.setItemDesc(contract.getItemDesc());
        invoice.setItemName(contract.getItemName());
        invoice.setPayeeId(contract.getOwnerId());
        invoice.setPayerId(contract.getUserId());
        invoice.setSum(contract.getPrice());
        invoice.setPayee(contract.getOwner());
        invoice.setPayer(contract.getUserName());
        invoice.setPayBy(ldt.toLocalDate().plusDays(3).toString());  // maksmistähtaeg 3 päeva peale arve loomist
        invoice.setPaid(false); // alguses on arve maksmata

        rentService.saveContract(contract);
        rentService.saveInvoice(invoice);
        rentService.removeContractOffer(coffer);

        return "redirect:/rentimine#sinu-lepingud";
    }

    @RequestMapping(value = "rentimine/rentOffer")
    public String rentOffer(@RequestParam int id, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LocalDateTime ldt = LocalDateTime.now();
        Offer offer = rentService.findOfferById(id);
        int userId = userService.findUserByEmail(auth.getName()).getId(); // current auth user id

        ContractOffer coffer = new ContractOffer();

        // arvutame tagastuskuupäeva lisades praegusele kuupäevale rendipäevade arvu
        String returnDate = ldt.toLocalDate().plusDays(Integer.parseInt(offer.getRentPeriod())).toString();

        coffer.setItemDesc(offer.getItemDesc());
        coffer.setItemName(offer.getItemName());
        coffer.setOwnerId(offer.getUserId());
        coffer.setUserId(userId);
        coffer.setPictureName(offer.getPictureName());
        coffer.setLocation(offer.getLocation());
        coffer.setOfferDateTime(ldt.toString());
        coffer.setReturnDate(returnDate);
        coffer.setUserName(userService.findUserById(userId).getUsername());
        coffer.setOwner(userService.findUserById(coffer.getOwnerId()).getUsername());
        coffer.setPrice(offer.getPrice());
        coffer.setOfferId(id);
        coffer.setOfferUserId(userId);

        rentService.saveContractOffer(coffer);

        return "redirect:/rentimine#sinu-lepingu-pakkumised";
    }

    @RequestMapping(value = "pakusoov/offerWish")
    public String offerWish(@Valid Offer offer, @RequestParam("id") int id, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LocalDateTime ldt = LocalDateTime.now();
        Wish wish = rentService.findWishById(id);
        int userId = userService.findUserByEmail(auth.getName()).getId();
        ContractOffer coffer = new ContractOffer();

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("failError", "Vali üleslaadimiseks fail");
            return "redirect:/pakusoov";
        }

        String filetype = file.getOriginalFilename().substring(file.getOriginalFilename().length()-3).toLowerCase();
        try {

            if(filetype.equals("jpg") || filetype.equals("png")) {
                byte[] bytes = file.getBytes();
                String failinimi = userService.findUserByEmail(auth.getName()).getUsername()+"coffer"+ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE)+"."+filetype;
                Path path = Paths.get(UPLOADED_FOLDER+failinimi);
                Files.write(path, bytes);
                coffer.setPictureName(failinimi);  // lisame pakkumisele pildi
            } else {
                redirectAttributes.addFlashAttribute("failError", "Failiformaat peab olema JPG või PNG");
                return "redirect:/pakusoov";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        String rDate = ldt.toLocalDate().plusDays(Integer.parseInt(offer.getRentPeriod())).toString();  // lisame praegusele kuupäevale rendi päevade arvu ja saame tagastuskuupäeva

        coffer.setItemDesc(offer.getItemDesc());
        coffer.setItemName(wish.getItemName());
        coffer.setOwnerId(userId);
        coffer.setUserId(wish.getUserId());
        coffer.setLocation(wish.getLocation());
        coffer.setOfferDateTime(ldt.toString());
        coffer.setReturnDate(rDate);
        coffer.setPrice(offer.getPrice());
        coffer.setUserName(userService.findUserById(coffer.getUserId()).getUsername());
        coffer.setOwner(userService.findUserById(coffer.getOwnerId()).getUsername());
        coffer.setWishId(id);
        coffer.setOfferUserId(userId);

        rentService.saveContractOffer(coffer);

        return "redirect:/rentimine#sinu-lepingu-pakkumised";
    }

    @RequestMapping(value = "rentimine/removeContract")
    public String removeContract(@RequestParam int id, RedirectAttributes redirectAttributes) {
        rentService.removeContract(rentService.findContractById(id));

        return "redirect:/rentimine#sinu-lepingud";
    }

    @RequestMapping(value = "rentimine/removeContractOffer")
    public String removeContractOffer(@RequestParam int id, RedirectAttributes redirectAttributes) {
        rentService.removeContractOffer(rentService.findContractOfferById(id));

        return "redirect:/rentimine#sinu-lepingu-pakkumised";
    }

    @RequestMapping(value = "rentimine/addOffer")
    public String addOffer(@Valid Offer offer, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LocalDateTime ldt = LocalDateTime.now();

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("failError", "Vali üleslaadimiseks fail");
            return "redirect:/rentimine#lisa-pakkumine";
        }

        String filetype = file.getOriginalFilename().substring(file.getOriginalFilename().length()-3).toLowerCase();

        try {

            if(filetype.equals("jpg") || filetype.equals("png")) {
                byte[] bytes = file.getBytes();
                String failinimi = userService.findUserByEmail(auth.getName()).getUsername()+"offer"+ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE)+"."+filetype;
                Path path = Paths.get(UPLOADED_FOLDER+failinimi);
                Files.write(path, bytes);
                offer.setPictureName(failinimi);  // lisame pakkumisele pildi
            } else {
                redirectAttributes.addFlashAttribute("failError", "Failiformaat peab olema JPG või PNG");
                return "redirect:/rentimine#lisa-pakkumine";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        offer.setDatetime(ldt.toString()); // lisame pakkumisele postitamise aja
        offer.setUserId(userService.findUserByEmail(auth.getName()).getId()); // lisame pakkumisele kasutaja id

        rentService.saveOffer(offer); // salvestame pakkumise andmebaasi
        redirectAttributes.addFlashAttribute("postSuccess", "Pakkumine on edukalt postitatud!");

        return "redirect:/rentimine#sinu-pakkumised";
    }

    @RequestMapping(value = "rentimine/addWish")
    public String addWish(@Valid Wish wish, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LocalDateTime ldt = LocalDateTime.now();

        wish.setDatetime(ldt.toString()); // lisame soovile postitamise aja
        wish.setUserId(userService.findUserByEmail(auth.getName()).getId()); // lisame soovile kasutaja id

        rentService.saveWish(wish); // salvestame pakkumise andmebaasi
        redirectAttributes.addFlashAttribute("postSuccess", "Soov on edukalt postitatud!");

        return "redirect:/rentimine#sinu-soovid";
    }

    @RequestMapping(value ="pakusoov", method = RequestMethod.GET)
    public ModelAndView getOfferWish(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Offer wishOffer = new Offer();

        modelAndView.addObject(user);
        modelAndView.addObject("wishId", id);
        modelAndView.addObject("wishOffer", wishOffer);
        modelAndView.setViewName("pakusoov");

        return modelAndView;
    }

    @RequestMapping(value ="rentimine/removeOffer")
    public String removeOffer(@RequestParam int id) {

        rentService.removeOffer(rentService.findOfferById(id));

        return "redirect:/rentimine#sinu-pakkumised";
    }

    @RequestMapping(value ="rentimine/removeWish")
    public String removeWish(@RequestParam int id) {

        rentService.removeWish(rentService.findWishById(id));

        return "redirect:/rentimine#sinu-soovid";
    }

    @RequestMapping(value ="rentimine/payInvoice")
    public String payInvoice(@RequestParam int id) {

        Invoice paidInvoice = rentService.findInvoiceById(id);
        paidInvoice.setPaid(true);
        paidInvoice.setPaidAt(LocalDateTime.now().toLocalDate().toString());
        rentService.saveInvoice(paidInvoice);

        return "redirect:/rentimine#sinu-arved";
    }
}
