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
public class RegistrationController { // registreerimise protsessid

    @Autowired
    private UserService userService;

    @Autowired
    public JavaMailSender emailSender;

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
        User emailExists = userService.findUserByEmail(user.getEmail());
        User userExists = userService.findUserByUsername(user.getUsername());

        if (emailExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Selle e-mailiga on juba kasutaja registreeritud!");
            modelAndView.addObject("emailError", "Selle e-mailiga on juba kasutaja registreeritud!");
            System.out.println("email exists");
        }
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "Selle kasutajanimega on juba kasutaja registreeritud!");
            modelAndView.addObject("usernameError", "Selle kasutajanimega on juba kasutaja registreeritud!");
            System.out.println("user exists");
        }
        if (bindingResult.hasErrors()) {
            List<ObjectError> errorid = bindingResult.getAllErrors();
            for(ObjectError error:errorid) {
                System.out.println(error.toString()); //debug
            }

            modelAndView.setViewName("registreeri");
        } else {

            try{
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(user.getEmail());
                message.setSubject("Rent.me - Teie kasutaja on registreeritud");
                message.setText("Teie kasutaja on registreeritud kasutajanimega "+user.getUsername());
                emailSender.send(message);

            } catch(Exception ex) {
                ex.printStackTrace();
            }

            userService.saveUser(user);
            System.out.println("kasutaja salvestatud");
            modelAndView.addObject("successMessage", "Kasutaja on registreeritud!");
            modelAndView.addObject("user", new User()); //et saaks uue kasutaja registreerida
            modelAndView.setViewName("registreeri");

        }
        return modelAndView;
    }
}
