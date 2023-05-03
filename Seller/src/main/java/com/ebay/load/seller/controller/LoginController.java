package com.ebay.load.seller.controller;



import com.ebay.load.seller.model.Users;
import com.ebay.load.seller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

   @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @RequestMapping(value = "/logoutMe", method = RequestMethod.GET)
    public ModelAndView logout() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message","You have been logged out successfully");
        modelAndView.addObject("isLogout",true);

        modelAndView.setViewName("login");
        return modelAndView;
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login2(@Valid Users users) {
        ModelAndView mav = new ModelAndView();
        //  Users users1= userService.checkExistingEmail(users.getEmail());

        return mav;
    }
}






