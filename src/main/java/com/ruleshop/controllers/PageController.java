package com.ruleshop.controllers;

import com.ruleshop.model.*;
import com.ruleshop.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by milosandric on 04.07.17.
 */
@Controller
public class PageController {

    @Autowired
    private RuleService ruleService;
    /**
     * This method will redirect user on index.ftl, otherwise will respond with 404 error response.
     * @return index.ftl
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {
        if(session.getAttribute("user") != null){
            return "redirect:/home";
        }
        return "index";

    }

    /**
     * This method will redirect user on index.ftl, otherwise will respond with 404 error response.
     * @return index.ftl
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        if(session.getAttribute("user") == null){
            return "redirect:/";
        }
//        System.err.print(session.getAttribute("name"));
        return "home";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpSession session) {
        session.invalidate();
        return "redirect:/";

    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String manage(Model model, HttpSession session) {
        if(session.getAttribute("user") == null){
            return "redirect:/";
        }
        User u = (User)session.getAttribute("user");
        if(!u.getRole().getRole_name().equals("manager")){
            return "redirect:/home";
        }

        List<BuyerCategory> bc = this.ruleService.getAllBCategories();
        List<ItemCategory> ic = this.ruleService.getAllICategories();
        List<Sale> sales = this.ruleService.getAllSles();
        model.addAttribute("bCat", bc);
        model.addAttribute("iCat", ic);
        model.addAttribute("sCat", sales);
        return "manage";

    }

    @RequestMapping(value = "/sellsettings", method = RequestMethod.GET)
    public String sellerPage(Model model, HttpSession session) {
        if(session.getAttribute("user") == null){
            return "redirect:/";
        }
        List<Item> orderItems = this.ruleService.getItemsForOrder();
        model.addAttribute("orderItems", orderItems);
        return "seller";

    }
}
