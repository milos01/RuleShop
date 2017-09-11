package com.ruleshop.controllers;

import com.ruleshop.model.Buyer;
import com.ruleshop.model.BuyerCategory;
import com.ruleshop.model.Role;
import com.ruleshop.model.User;
import com.ruleshop.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by milosandric on 05.07.17.
 */
@Controller
public class UserController {

    @Autowired
    private RuleService ruleService;

    /**
     * This method will redirect user on index.ftl, otherwise will respond with 404 error response.
     *
     * @return index.ftl
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String home(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password, HttpSession session) {
        User user = this.ruleService.findUser(email);
//        System.out.print(user.toString());
        if (user == null) {
            return "redirect:/";
        }
        if (!user.getPassword().equals(password)) {
            return "redirect:/";
        }
        session.setAttribute("user", user);
        if (user.getRole().getRole_name().equals("buyer")) {
            return "redirect:/home?searchCode=all&searchName=all&category=all&price_from=0&price_to=0";
        } else if (user.getRole().getRole_name().equals("manager")) {
            return "redirect:/manage";
        } else if (user.getRole().getRole_name().equals("seller")) {
            return "redirect:/sellsettings";
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/buyerRegister", method = RequestMethod.POST)
    public String buyerRegister(@RequestParam(value = "email") String email,
                                @RequestParam(value = "password") String password,
                                @RequestParam(value = "f_name") String f_name,
                                @RequestParam(value = "l_name") String l_name,
                                @RequestParam(value = "address") String address,
                                @RequestParam(value = "address_no") int address_no) {
        Role role = ruleService.findRole(1);
        BuyerCategory buyerCategory = ruleService.findCategory(1);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirst_name(f_name);
        user.setLast_name(l_name);
        user.setRole(role);
        user.setCreatedAt(new Date());

//        ruleService.addUser(user);

        Buyer buyer = new Buyer();
        buyer.setAddress(address);
        buyer.setAddress_no(address_no);
        buyer.setPoints(0.0);
        buyer.setBuyerCategory(buyerCategory);
        buyer.setUser(user);
        ruleService.addBuyer(buyer);


        return "redirect:/";

    }
}
