package com.ruleshop.controllers;

import com.ruleshop.model.*;
import com.ruleshop.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by milosandric on 06.07.17.
 */
@Controller
public class CategoryController {
    @Autowired
    private RuleService ruleService;
    /**
     * This method will redirect user on index.ftl, otherwise will respond with 404 error response.
     * @return index.ftl
     */
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public String addCategory(@RequestParam(value="category_name") String category_name,
                        @RequestParam(value="limit_from") int limit_from,
                        @RequestParam(value="limit_to") int limit_to,
                        @RequestParam(value="point_percent") int point_percent) {

        BuyerCategory bc = new BuyerCategory();
        bc.setCategory_name(category_name);

        CategoryLimit cl = new CategoryLimit();
        cl.setLimit_from(limit_from);
        cl.setLimit_to(limit_to);
        cl.setPoint_percent(point_percent);
        cl.setBuyerCategory(bc);
        this.ruleService.addCategory(bc);
        this.ruleService.addLimit(cl);
        return "redirect:/manage";

    }

    @RequestMapping(value = "/addLimit", method = RequestMethod.POST)
    public String addLimit(@RequestParam(value="category_id") int category_id,
                        @RequestParam(value="limit_from") int limit_from,
                        @RequestParam(value="limit_to") int limit_to,
                        @RequestParam(value="point_percent") int point_percent) {

        BuyerCategory bc = (BuyerCategory) this.ruleService.findCategory(category_id);

        CategoryLimit cl = new CategoryLimit();
        cl.setLimit_from(limit_from);
        cl.setLimit_to(limit_to);
        cl.setPoint_percent(point_percent);
        cl.setBuyerCategory(bc);

        this.ruleService.addLimit(cl);
        return "redirect:/manage";

    }

    @RequestMapping(value = "/updateLimit", method = RequestMethod.POST)
    public String updateLimit(@RequestParam(value="limit_id") int limit_id,
                           @RequestParam(value="limit_from") int limit_from,
                           @RequestParam(value="limit_to") int limit_to,
                           @RequestParam(value="point_percent") int point_percent) {

        CategoryLimit cl = (CategoryLimit) this.ruleService.findLimit(limit_id);

        cl.setLimit_from(limit_from);
        cl.setLimit_to(limit_to);
        cl.setPoint_percent(point_percent);

        this.ruleService.updateLimit(cl);
        return "redirect:/manage";

    }

    @RequestMapping(value = "/renameCategory", method = RequestMethod.POST)
    public String renameCategory(@RequestParam(value="category_id") int category_id,
                              @RequestParam(value="category_name") String category_name) {

        BuyerCategory bc = (BuyerCategory) this.ruleService.findCategory(category_id);

        bc.setCategory_name(category_name);

        this.ruleService.updateCategory(bc);
        return "redirect:/manage";

    }

    @RequestMapping(value = "/addItemCategory", method = RequestMethod.POST)
    public String addItemCategory(@RequestParam(value="code") String code,
                                 @RequestParam(value="name") String name,
                                  @RequestParam(value="point_percent") int percent) {

        ItemCategory ic = new ItemCategory();
        ic.setCode(code);
        ic.setName(name);
        ic.setMax_discount_percent(percent);

        this.ruleService.addItemCategory(ic);
        return "redirect:/manage";

    }

    @RequestMapping(value = "/updateItemCategory", method = RequestMethod.POST)
    public String updateItemCategory( @RequestParam(value="category_id") int category_id,
                                        @RequestParam(value="name") String name,
                                      @RequestParam(value="point_percent") int percent) {

        ItemCategory ic = (ItemCategory) this.ruleService.findItemCategory(category_id);

        ic.setName(name);
        ic.setMax_discount_percent(percent);

        this.ruleService.updateItemCategory(ic);
        return "redirect:/manage";

    }

    @RequestMapping(value = "/addNewSale", method = RequestMethod.POST)
    public String addNewSale( @RequestParam(value="code") String code,
                              @RequestParam(value="name") String name,
                              @RequestParam(value="point_percent") int percent,
                              @RequestParam(value="sale_from") @DateTimeFormat(pattern="yyyy-MM-dd") Date sale_from,
                              @RequestParam(value="sale_to") @DateTimeFormat(pattern="yyyy-MM-dd") Date sale_to,
                              @RequestParam(value="categories") List<Integer> categories) {

        Sale sale = new Sale();
        sale.setName(name);
        sale.setCode(code);
        sale.setDiscount_percent(percent);
        sale.setSale_from(sale_from);
        sale.setSale_to(sale_to);

        Sale s = (Sale)this.ruleService.addNewSale(sale);

        for (Integer cat: categories) {
            ItemCategory category = (ItemCategory)this.ruleService.findItemCategory(cat);
            category.setSale(s);
            this.ruleService.updateItemCategory(category);
        }
        return "redirect:/manage";

    }

    @RequestMapping(value = "/updateSale", method = RequestMethod.POST)
    public String updateSale(@RequestParam(value="category_id") int category_id
            ,
                              @RequestParam(value="name") String name,
                              @RequestParam(value="point_percent") int percent,
                              @RequestParam(value="sale_from") @DateTimeFormat(pattern="yyyy-MM-dd") Date sale_from,
                              @RequestParam(value="sale_to") @DateTimeFormat(pattern="yyyy-MM-dd") Date sale_to,
                              @RequestParam(value="categories") List<Integer> categories) {

        Sale sale = this.ruleService.findSale(category_id);
        sale.setName(name);
        sale.setDiscount_percent(percent);
        sale.setSale_from(sale_from);
        sale.setSale_to(sale_to);

        this.ruleService.updateSale(sale);

        for (ItemCategory item: sale.getItems()) {
            item.setSale(null);
            this.ruleService.updateItemCategory(item);
        }
        for (Integer cat: categories) {
            ItemCategory category = (ItemCategory)this.ruleService.findItemCategory(cat);
            category.setSale(sale);
            this.ruleService.updateItemCategory(category);
        }
        return "redirect:/manage";

    }

    @RequestMapping(value = "/orderItem", method = RequestMethod.POST)
    public String orderItem( @RequestParam(value="item_id") int item_id,
                                      @RequestParam(value="item_quantity") Double item_quantity) {

        Item item  = (Item) this.ruleService.findItem(item_id);

        Double diff = item.getLager_min_state() - item.getNumber_left();
        if (diff > item_quantity){
            return "redirect:/sellsettings";
        }

        item.setNeedOrder(false);
        item.setNumber_left(item.getNumber_left() + item_quantity);
        this.ruleService.updateItem(item);

        return "redirect:/sellsettings";

    }

    @RequestMapping(value = "/filterBills", method = RequestMethod.POST)
    public String filterBills(Model model) {

        model.addAttribute("bills", "new bills");
        return "bill";

    }

    @RequestMapping(value = "/submitOrder", method = RequestMethod.POST)
    public String submitOrder(@RequestParam(value="bill_id") int bill_id, Model model) {

        Bill bill = (Bill)this.ruleService.findBill(bill_id);
        for (BillItem item: bill.getBill_items()) {
            Item realItem = (Item)this.ruleService.getItemByName(item.getItem_name());
            if (item.getItem_quantity() > realItem.getNumber_left()){
                return "redirect:/billsettings";
            }else{
                Double newQuantity = realItem.getNumber_left() - item.getItem_quantity();
                realItem.setNumber_left(newQuantity);
                this.ruleService.updateItem(realItem);
            }

        }
        bill.setState("uspesno_realizovan");
        this.ruleService.updateBill(bill);
        return "redirect:/billsettings";

    }

    @RequestMapping(value = "/searchItems", method = RequestMethod.POST)
    public String searchItems(@RequestParam(value="searchCode") String searchCode,
                              @RequestParam(value="searchName") String searchName,
                              @RequestParam(value="category") String category,
                              @RequestParam(value="price_from") Double price_from,
                              @RequestParam(value="price_to") Double price_to, Model model) {
        System.err.print(price_from+ "aa");
        return "redirect:/home?searchCode="+searchCode+"&searchName="+searchName+"&category="+category+"&price_from="+price_from+"&price_to="+price_to;

    }

    @RequestMapping(value = "/addItemToCart", method = RequestMethod.POST)
    public String addItemToCart(@RequestParam(value="item_id") int item_id,
                              @RequestParam(value="cartNnum") int cartNnum, Model model, HttpSession session) {

        Item item = (Item)this.ruleService.findItem(item_id);
        User user = (User)session.getAttribute("user");

        Cart cart = new Cart();
        cart.setItem(item);
        cart.setUser(user);
        cart.setQuantity(cartNnum);

        this.ruleService.addCartItem(cart);
        return "redirect:/cart";

    }

}
