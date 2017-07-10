package com.ruleshop.controllers;

import com.ruleshop.model.User;
import com.ruleshop.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;

/**
 * Created by milosandric on 05.07.17.
 */
@Controller
public class UserController {

    @Autowired
    private RuleService ruleService;

    /**
            * This method will redirect user on index.ftl, otherwise will respond with 404 error response.
            * @return index.ftl
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String home(@RequestParam(value="email") String email, @RequestParam(value="password") String password, HttpSession session) {
        User user = this.ruleService.findUser(email);
//        System.out.print(user.toString());
        if (user == null){
            return "redirect:/";
        }
        if (!user.getPassword().equals(password)){
            return "redirect:/";
        }
        session.setAttribute("user",user);
        if (user.getRole().getRole_name().equals("buyer")) {
            return "redirect:/home?searchCode=all&searchName=all&category=all&price_from=0&price_to=0";
        }
        return "redirect:/home";
    }
}
