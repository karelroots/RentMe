package com.project.rent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MeistController { //login lehe kuvamine

    @RequestMapping(value={"/meist"}, method = RequestMethod.GET)
    public ModelAndView meist(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("meist");
        return modelAndView;
    }

}