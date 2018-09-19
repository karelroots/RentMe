package com.project.rent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StatsController {
    @RequestMapping(value={"/statistika"}, method = RequestMethod.GET)
    public ModelAndView statistika(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("statistika");
        return modelAndView;
    }
}
