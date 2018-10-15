package com.project.rent.controller;

import com.project.rent.model.*;
import com.project.rent.service.StatsService;
import com.project.rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StatsController {

    @Autowired
    private UserService userService;

    @Autowired
    private StatsService statsService;

    @RequestMapping(value={"/statistika"}, method = RequestMethod.GET)
    public ModelAndView statistika(){ // statistika lehe kuvamine

        Summa summa = new Summa(userService.getSum()); // leiame kasutajate koguarvu

        List<User> userArrayList = userService.getUserList();

        List<UserLog> userLogArrayList = statsService.getLastTenList(); // võtame viimased 10 logikirjet

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName()); //leiame kasutaja objekti

        List<Browser> topBrowsers = statsService.getTopBrowsers();
        List<OpSys> topOpSystems = statsService.getTopOs();
        List<Landing> topLandingPages = statsService.getTopLanding();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(user);
        modelAndView.addObject("users", userArrayList); // lisame kõikide kasutajate listi statistika lehele kasutamiseks
        modelAndView.addObject(summa); // lisame kasutajate arvu lehele objektina
        modelAndView.addObject("logs", userLogArrayList); // lisame logide listi
        modelAndView.addObject("browsers", topBrowsers);
        modelAndView.addObject("opsys", topOpSystems);
        modelAndView.addObject("pages", topLandingPages);
        modelAndView.setViewName("statistika");
        return modelAndView;
    }

    @RequestMapping(value={"/statistika/toggleActive"}, method = RequestMethod.POST)
    public String statistika(int userId, int active){ // kasutajate aktiveerimine/deaktiveerimine

        userService.saveActive(active, userService.findUserById(userId));

        return "redirect:/statistika";
    }

    @RequestMapping("/statistika/")
    public void handleRequest() {
        try {
            System.out.println("ei ole exceptionit");
        } catch(RuntimeException ex) {
            throw ex;
        }
    }
}
