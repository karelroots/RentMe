package com.project.rent.controller;

import com.project.rent.model.User;
import com.project.rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RentController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/rentimine")
    public ModelAndView rentimine() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName()); //leiame kasutaja objekt
        modelAndView.addObject(user);
        modelAndView.setViewName("rentimine");
        return modelAndView;
    }
}
