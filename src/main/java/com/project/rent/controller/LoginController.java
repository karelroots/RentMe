package com.project.rent.controller;

import com.project.rent.model.User;
import com.project.rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

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

    @RequestMapping(value = "/rentimine")
    public ModelAndView rentimine() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("rentimine");
        return modelAndView;
    }

    @RequestMapping(value = "/registreeri", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Selle e-mailiga on juba kasutaja registreeritud!");
            System.out.println("user exists");
        }
        if (bindingResult.hasErrors()) {
            List<ObjectError> errorid = bindingResult.getAllErrors();
            for(ObjectError error:errorid) {
                System.out.println(error.toString());
            }

            modelAndView.setViewName("registreeri");
            System.out.println("salvestuse error");
        } else {
            userService.saveUser(user);
            System.out.println("kasutaja salvestatud");
            modelAndView.addObject("successMessage", "Kasutaja on registreeritud!");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registreeri");

        }
        return modelAndView;
    }

    @RequestMapping(value="/profiil", method = RequestMethod.GET)
    public ModelAndView profiil(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        /*modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Ligipääs ainult administraatori õigustega");*/
        modelAndView.setViewName("profiil");
        return modelAndView;
    }

    @RequestMapping(value="/profiil", method = RequestMethod.POST)
    public ModelAndView muudaProfiili(@Valid User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profiil");

        return modelAndView;
    }


}