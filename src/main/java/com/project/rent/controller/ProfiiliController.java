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

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProfiiliController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/profiil", method = RequestMethod.GET)
    public ModelAndView profiil(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName()); //leiame kasutaja objekt

        modelAndView.addObject(user);
        modelAndView.setViewName("profiil");
        return modelAndView;
    }

    @RequestMapping(value="/profiil", method = RequestMethod.POST)
    public ModelAndView muudaProfiili(@Valid User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User originalUser = userService.findUserByEmail(auth.getName());
        int originalId = originalUser.getId();


        if (bindingResult.hasErrors()) {
            List<ObjectError> errorid = bindingResult.getAllErrors();
            for(ObjectError error:errorid) {
                System.out.println(error.toString()); //debug
            }
            modelAndView.setViewName("profiil");
        } else {
            modelAndView.setViewName("profiil");
            this.userService.updateUser(originalId, user);
            System.out.println("save success");
            modelAndView.addObject("successMessage", "Kasutaja andmed on muudetud!");
        }

        modelAndView.addObject("user", originalUser);
        return modelAndView;
    }

}
