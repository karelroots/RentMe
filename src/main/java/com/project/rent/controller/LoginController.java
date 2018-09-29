package com.project.rent.controller;

import com.project.rent.model.User;
import com.project.rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController { //siin töötavad meetodid, mis kuvavad konkreetseid html lehti ja määrame ära, ka mida erinevate vormide POST-imisel tehakse

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value="/registreeri", method = RequestMethod.GET)
    public ModelAndView registreeri(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registreeri");
        return modelAndView;
    }

    @RequestMapping(value = "/registreeri", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        System.out.println(user.getEmail()); //debug
        System.out.println(user.getPassword());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Selle e-mailiga on juba kasutaja registreeritud!");
            modelAndView.addObject("emailError", "Selle e-mailiga on juba kasutaja registreeritud!");
            System.out.println("user exists");
        }
        if (bindingResult.hasErrors()) {
            List<ObjectError> errorid = bindingResult.getAllErrors();
            for(ObjectError error:errorid) {
                System.out.println(error.toString()); //debug
            }

            modelAndView.setViewName("registreeri");
        } else {
            userService.saveUser(user);
            String recipientAddress = user.getEmail();
            String subject = "Registreerimise kinnitus";
            String message = "Tere, "+user.getName()+"Olete registreerinud kasutaja keskonnas Rent.Me kasutajanimega "+user.getUsername();

            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(recipientAddress);
            email.setSubject(subject);
            email.setText(message);
            mailSender.send(email);
            System.out.println("kasutaja salvestatud");
            modelAndView.addObject("successMessage", "Kasutaja on registreeritud!");
            modelAndView.addObject("user", new User()); //et saaks uue kasutaja registreerida
            modelAndView.setViewName("registreeri");

        }
        return modelAndView;
    }

}