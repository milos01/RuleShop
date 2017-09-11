package com.ruleshop.controllers;

import com.ruleshop.model.*;
import com.ruleshop.service.RuleService;
import com.ruleshop.util.AllItems;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by milosandric on 04.07.17.
 */
@Controller
public class PageController {

    @Autowired
    private RuleService ruleService;

    /**
     * This method will redirect user on index.ftl, otherwise will respond with 404 error response.
     *
     * @return index.ftl
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {

        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (user.getRole().getRole_name().equals("buyer")) {
                return "redirect:/home?searchCode=all&searchName=all&category=all&price_from=0&price_to=0";
            } else if (user.getRole().getRole_name().equals("manager")) {
                return "redirect:/manage";
            } else if (user.getRole().getRole_name().equals("seller")) {
                return "redirect:/sellsettings";
            }
            return "redirect:/home";
        }

        return "index";

    }

    /**
     * This method will redirect user on index.ftl, otherwise will respond with 404 error response.
     *
     * @return index.ftl
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(@RequestParam(value = "searchCode") String searchCode,
                       @RequestParam(value = "searchName") String searchName,
                       @RequestParam(value = "category") String category,
                       @RequestParam(value = "price_from") Double price_from,
                       @RequestParam(value = "price_to") Double price_to, Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        User user = (User) session.getAttribute("user");
        if (user.getRole().getRole_name().equals("buyer")) {
            if (!searchCode.equals("all") && !searchName.equals("all") && !category.equals("") && price_from != 0 && price_to != 0) {
                List<Item> categories = this.ruleService.getAllSICategories(searchCode, searchName, category, price_from, price_to);
                model.addAttribute("items", categories);
            } else {
                List<Item> items = this.ruleService.getAllItems();
                model.addAttribute("items", items);
            }


        }

        return "home";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpSession session) {
        session.invalidate();
        return "redirect:/";

    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String manage(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        User u = (User) session.getAttribute("user");
        if (!u.getRole().getRole_name().equals("manager")) {
            return "redirect:/";
        }

        List<BuyerCategory> bc = this.ruleService.getAllBCategories();
        List<ItemCategory> ic = this.ruleService.getAllICategories();
        List<Sale> sales = this.ruleService.getAllSles();
        model.addAttribute("bCat", bc);
        model.addAttribute("iCat", ic);
        model.addAttribute("sCat", sales);
        return "manage";

    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage() {
        return "register";
    }

    @RequestMapping(value = "/sellsettings", method = RequestMethod.GET)
    public String sellerPage(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        User u = (User) session.getAttribute("user");
        if (!u.getRole().getRole_name().equals("seller")) {
            return "redirect:/";
        }

        AllItems allItems = new AllItems();
        allItems.setProducts(this.ruleService.getAllItems());
        this.ruleService.getFillingStock(allItems);
        for (Item item : allItems.getProducts()) {
            this.ruleService.updateItem(item);
        }


        List<Item> orderItems = this.ruleService.getItemsForOrder();
        model.addAttribute("orderItems", orderItems);
        return "seller";

    }

    @RequestMapping(value = "/billingsettings", method = RequestMethod.GET)
    public String billingPage(@RequestParam(value = "filter") String filter, Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        List<Bill> bills = null;
        if (filter.equals("all")) {
            bills = this.ruleService.getAllBills();
            model.addAttribute("bills", bills);
            return "bill";
        } else if (filter.equals("ordered")) {
            bills = this.ruleService.getOrderedBills();
            model.addAttribute("bills", bills);
            return "bill";
        } else if (filter.equals("successfully_receved")) {
            bills = this.ruleService.getSuccessBills();
            model.addAttribute("bills", bills);
            return "bill";
        } else if (filter.equals("rejected")) {
            bills = this.ruleService.getRejectedBills();
            model.addAttribute("bills", bills);
            return "bill";
        }
        return "redirect:/home";

    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String userProfile(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        return "profile";

    }

    @RequestMapping(value = "/mybills", method = RequestMethod.GET)
    public String myBils(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        List<Bill> bills = this.ruleService.getSuccessBills();
        model.addAttribute("bills", bills);
        return "mybills";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cartPage(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }


        User user = (User) session.getAttribute("user");
        List<Cart> cart_items = this.ruleService.getUserCartItems(user.getId());
        List<Bill> bills = this.ruleService.getOrderedBills();
        model.addAttribute("cart_items", cart_items);
        model.addAttribute("billss", bills);
        return "cart";
    }
}
