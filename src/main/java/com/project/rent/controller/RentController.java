package com.project.rent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RentController {

    @RequestMapping(value = "/rentimine")
    public ModelAndView rentimine() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("rentimine");
        return modelAndView;
    }
}
