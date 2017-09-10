package com.ruleshop.controllers;

import com.ruleshop.model.*;
import com.ruleshop.service.RuleService;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by milosandric on 06.07.17.
 */
@Controller
public class CategoryController {
    @Autowired
    private RuleService ruleService;

    /**
     * This method will redirect user on index.ftl, otherwise will respond with 404 error response.
     *
     * @return index.ftl
     */
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public String addCategory(@RequestParam(value = "category_name") String category_name,
                              @RequestParam(value = "limit_from") int limit_from,
                              @RequestParam(value = "limit_to") int limit_to,
                              @RequestParam(value = "point_percent") int point_percent) {

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
    public String addLimit(@RequestParam(value = "category_id") int category_id,
                           @RequestParam(value = "limit_from") int limit_from,
                           @RequestParam(value = "limit_to") int limit_to,
                           @RequestParam(value = "point_percent") int point_percent) {

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
    public String updateLimit(@RequestParam(value = "limit_id") int limit_id,
                              @RequestParam(value = "limit_from") int limit_from,
                              @RequestParam(value = "limit_to") int limit_to,
                              @RequestParam(value = "point_percent") int point_percent) {

        CategoryLimit cl = (CategoryLimit) this.ruleService.findLimit(limit_id);

        cl.setLimit_from(limit_from);
        cl.setLimit_to(limit_to);
        cl.setPoint_percent(point_percent);

        this.ruleService.updateLimit(cl);
        return "redirect:/manage";

    }

    @RequestMapping(value = "/renameCategory", method = RequestMethod.POST)
    public String renameCategory(@RequestParam(value = "category_id") int category_id,
                                 @RequestParam(value = "category_name") String category_name) {

        BuyerCategory bc = (BuyerCategory) this.ruleService.findCategory(category_id);

        bc.setCategory_name(category_name);

        this.ruleService.updateCategory(bc);
        return "redirect:/manage";

    }

    @RequestMapping(value = "/addItemCategory", method = RequestMethod.POST)
    public String addItemCategory(@RequestParam(value = "code") String code,
                                  @RequestParam(value = "name") String name,
                                  @RequestParam(value = "point_percent") int percent,
                                  @RequestParam(value = "consumer_goods", required = false) String consumer_goods) {

        ItemCategory ic = new ItemCategory();
        ic.setCode(code);
        ic.setName(name);
        if (consumer_goods == null) {
            ic.setHasGlobaItemCat(false);
        } else {
            ic.setHasGlobaItemCat(true);
        }
        ic.setMax_discount_percent(percent);

        this.ruleService.addItemCategory(ic);
        return "redirect:/manage";

    }

    @RequestMapping(value = "/updateItemCategory", method = RequestMethod.POST)
    public String updateItemCategory(@RequestParam(value = "category_id") int category_id,
                                     @RequestParam(value = "name") String name,
                                     @RequestParam(value = "point_percent") int percent,
                                     @RequestParam(value = "consumer_goods", required = false) String consumer_goods) {

        ItemCategory ic = (ItemCategory) this.ruleService.findItemCategory(category_id);

        ic.setName(name);
        ic.setMax_discount_percent(percent);
        if (consumer_goods == null) {
            ic.setHasGlobaItemCat(false);
        } else {
            ic.setHasGlobaItemCat(true);
        }
        this.ruleService.updateItemCategory(ic);
        return "redirect:/manage";

    }

    @RequestMapping(value = "/addNewSale", method = RequestMethod.POST)
    public String addNewSale(@RequestParam(value = "code") String code,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "point_percent") int percent,
                             @RequestParam(value = "sale_from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date sale_from,
                             @RequestParam(value = "sale_to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date sale_to,
                             @RequestParam(value = "categories") List<Integer> categories) {

        Sale sale = new Sale();
        sale.setName(name);
        sale.setCode(code);
        sale.setDiscount_percent(percent);
        sale.setSale_from(sale_from);
        sale.setSale_to(sale_to);

        Sale s = (Sale) this.ruleService.addNewSale(sale);

        for (Integer cat : categories) {
            ItemCategory category = (ItemCategory) this.ruleService.findItemCategory(cat);
            category.setSale(s);
            this.ruleService.updateItemCategory(category);
        }
        return "redirect:/manage";

    }

    @RequestMapping(value = "/updateSale", method = RequestMethod.POST)
    public String updateSale(@RequestParam(value = "category_id") int category_id
            ,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "point_percent") int percent,
                             @RequestParam(value = "sale_from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date sale_from,
                             @RequestParam(value = "sale_to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date sale_to,
                             @RequestParam(value = "categories") List<Integer> categories) {

        Sale sale = this.ruleService.findSale(category_id);
        sale.setName(name);
        sale.setDiscount_percent(percent);
        sale.setSale_from(sale_from);
        sale.setSale_to(sale_to);

        this.ruleService.updateSale(sale);

        for (ItemCategory item : sale.getItems()) {
            item.setSale(null);
            this.ruleService.updateItemCategory(item);
        }
        for (Integer cat : categories) {
            ItemCategory category = (ItemCategory) this.ruleService.findItemCategory(cat);
            category.setSale(sale);
            this.ruleService.updateItemCategory(category);
        }
        return "redirect:/manage";

    }

    @RequestMapping(value = "/orderItem", method = RequestMethod.POST)
    public String orderItem(@RequestParam(value = "item_id") int item_id,
                            @RequestParam(value = "item_quantity") Double item_quantity) {

        Item item = (Item) this.ruleService.findItem(item_id);

        Double diff = item.getLager_min_state() - item.getNumber_left();
        if (diff > item_quantity) {
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
    public String submitOrder(@RequestParam(value = "bill_id") int bill_id, Model model) {

        Bill bill = (Bill) this.ruleService.findBill(bill_id);
        for (BillItem item : bill.getBill_items()) {
            Item realItem = (Item) this.ruleService.getItemByName(item.getItem().getName());
            if (item.getItem_quantity() > realItem.getNumber_left()) {
                return "redirect:/billingsettings?filter=all";
            } else {
                Double newQuantity = realItem.getNumber_left() - item.getItem_quantity();
                realItem.setNumber_left(newQuantity);
                this.ruleService.updateItem(realItem);
            }

        }
        bill.setState("uspesno_realizovan");
        this.ruleService.updateBill(bill);
        return "redirect:/billingsettings?filter=all";

    }

    @RequestMapping(value = "/rejectOrder", method = RequestMethod.POST)
    public String rejectOrder(@RequestParam(value = "bill_id") int bill_id, Model model) {

        Bill bill = (Bill) this.ruleService.findBill(bill_id);
        bill.setState("odbijen");

        this.ruleService.updateBill(bill);
        return "redirect:/billingsettings?filter=all";

    }

    @RequestMapping(value = "/searchItems", method = RequestMethod.POST)
    public String searchItems(@RequestParam(value = "searchCode") String searchCode,
                              @RequestParam(value = "searchName") String searchName,
                              @RequestParam(value = "category") String category,
                              @RequestParam(value = "price_from") Double price_from,
                              @RequestParam(value = "price_to") Double price_to, Model model) {
        System.err.print(price_from + "aa");
        return "redirect:/home?searchCode=" + searchCode + "&searchName=" + searchName + "&category=" + category + "&price_from=" + price_from + "&price_to=" + price_to;

    }

    @RequestMapping(value = "/addItemToCart", method = RequestMethod.POST)
    public String addItemToCart(@RequestParam(value = "item_id") int item_id,
                                @RequestParam(value = "cartNnum") int cartNnum, HttpSession session) {

        System.err.print(cartNnum);
        Item item = (Item) this.ruleService.findItem(item_id);
        if (cartNnum < item.getNumber_left()) {
            User user = (User) session.getAttribute("user");

            Cart cart = new Cart();
            cart.setItem(item);
            cart.setUser(user);
            cart.setQuantity(cartNnum);

            this.ruleService.addCartItem(cart);
            return "redirect:/cart";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/orderCheckout", method = RequestMethod.POST)
    public String orderCheckout(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        User user = (User) session.getAttribute("user");
        List<Cart> cart_items = this.ruleService.getUserCartItems(user.getId());
        Double brutoPrice = 0.0;


        for (Cart item : cart_items) {
            brutoPrice += (item.getQuantity() * item.getItem().getPrice());
        }

        Bill bill = new Bill();
        bill.setOrigina_price(brutoPrice);
        bill.setCeated_at(new Date());
        bill.setState("porucen");
        bill.setBuyer(user.getBuyer());
        bill.setFinal_price(0.0);


//        this.ruleService.addBill(bill);
        Set<BillItem> bi = new HashSet<BillItem>();
        for (Cart item : cart_items) {
            BillItem bill_item = new BillItem();
            Set<ItemDiscount> discountss = new HashSet<>();
            bill_item.setBill(bill);
            bill_item.setItem_quantity(item.getQuantity());
            bill_item.setItem(item.getItem());
            bill_item.setFinal_price(0.0);
            bill_item.setDiscounts(discountss);
//            //drools for bill item discounts
//            this.ruleService.getBillItemDiscounts(bill_item);
//            this.ruleService.addBillItem(bill_item);
//            Set<ItemDiscount> itemDiscounts = bill_item.getDiscounts();
////
//            if (itemDiscounts != null){
//                for (ItemDiscount id: itemDiscounts) {
//                    this.ruleService.addItemDiscount(id);
//                }
//            }
//
//            this.ruleService.getItemsFinalDiscounts(bill_item);
//            this.ruleService.updateBillItem(bill_item);
//
//
            bi.add(bill_item);

//
        }
        bill.setBill_items(bi);


//        this.ruleService.getBillDiscounts(bill);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -15);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_MONTH, -30);

        basicItemDiscount(bill);
        extraItemDiscount(bill, calendar.getTime());
        extraItemDiscount2(bill, calendar2.getTime());
        extraItemDiscountEvents(bill);
        finalItemDiscount(bill);
        ruleService.addBill(bill);


//
//////        System.err.print(newBill.getBill_items());
//        for (BillItem bitem: newBill.getBill_items()) {
//            this.ruleService.updateBillItem(bitem);
//
//        }
//        for (BillItem bitem : bill.getBill_items()) {
//            ruleService.updateBillItem(bitem);
//            for (ItemDiscount disc: bitem.getDiscounts()) {
//                this.ruleService.addItemDiscount(disc);
//            }
//
//        }
//        this.ruleService.updateBill(bill);
//
//        this.ruleService.getBillDiscounts(bill);
//
//
//        Set<BillDiscount> itemDiscounts = bill.getDiscounts();
//
//        if (itemDiscounts != null){
//            for (BillDiscount billDiscount: itemDiscounts) {
//                this.ruleService.addBillDiscount(billDiscount);
//            }
//        }

        //final price and all for bill
//        this.ruleService.getFinalDiscounts(bill);
//        this.ruleService.updateBill(bill);


//        //Bill item rules
//        for (Cart item: cart_items) {
//            BillItem bill_item = new BillItem();
//            bill_item.setBill(this.ruleService.findBill(1));
//            bill_item.setItem_quantity(item.getQuantity());
//            bill_item.setItem(item.getItem());
////            bill_item.setDiscounts();
//            //drools for bill item discounts
//            this.ruleService.getBillItemDiscounts(bill_item);
//            this.ruleService.addBillItem(bill_item);
//
//
//            Set<ItemDiscount> itemDiscounts = bill_item.getDiscounts();
//
//            if (itemDiscounts != null){
////                Double tmp = 0.0;
//                Map<ItemDiscount, Double> itemMap = new HashMap<ItemDiscount, Double>();
////                int tmpId = 1;
//                for (ItemDiscount id: itemDiscounts) {
//                    //best discount form all discounts
//                    int it_q = id.getBillItem().getItem_quantity();
//                    Double it_p = id.getBillItem().getItem().getPrice();
//                    Double disc = ((it_q * it_p) * id.getDiscount_percent())/100;
//                    System.err.print(disc);
//                    itemMap.put(id, disc);
////                    this.ruleService.addItemDiscount(id);
////                    tmp = 0.0;
////                    tmpId++;
//                }
//                Double i = 0.0;
//                for(ItemDiscount key: itemMap.keySet()){
//                    if (itemMap.get(key) > i){
//                        i = itemMap.get(key);
//                    }
//
//                }
//                for(ItemDiscount key: itemMap.keySet()){
//                    if (itemMap.get(key) == i){
//                        this.ruleService.addItemDiscount(key);
//                    }
//
//                }
//
////                System.out.println(key + " - " + vehicles.get(key));
//
//            }
//            System.err.print("<-"+itemDiscounts);
////            this.ruleService.addBillItem(bill_item);
//            while (itemDiscounts.iterator().hasNext()){
//                System.err.print(itemDiscounts.iterator().next());
//            }


//


//        }

        return "redirect:/cart";

    }
    public Bill basicItemDiscount(Bill item){

        this.ruleService.itemDiscount(item);

        // call rules for best basic discount
        for (BillItem bitem : item.getBill_items()) {
            this.ruleService.filterForBestDiscount(bitem);
        }
        return item;

    }

    private Bill extraItemDiscount(Bill invoice, Date date) {
        List<Bill> invoices = this.ruleService.findByDateAfterAndCustomer_Id(date, invoice.getBuyer().getId());
        for (BillItem item : invoice.getBill_items()) {
            int itemDiscounts = item.getDiscounts().size();
            for (Bill histInvoice : invoices) {
                this.ruleService.extraItemDiscount(item, histInvoice);
                if (itemDiscounts != item.getDiscounts().size()) {
                    break;
                }
            }
        }
        return invoice;
    }

    private Bill extraItemDiscount2(Bill invoice, Date date) {
        List<Bill> invoices = this.ruleService.findByDateAfterAndCustomer_Id(date, invoice.getBuyer().getId());
        for (BillItem item : invoice.getBill_items()) {
            int itemDiscounts = item.getDiscounts().size();
            for (Bill histInvoice : invoices) {
                this.ruleService.extraItemDiscount30(item, histInvoice);
                if (itemDiscounts != item.getDiscounts().size()) {
                    break;
                }
            }
        }
        return invoice;
    }

    private Bill extraItemDiscountEvents(Bill invoice) {
        List<Sale> actionEvents = this.ruleService.findByDateEndingAfter(new Date());
        for (BillItem item : invoice.getBill_items()) {
            for (Sale actionEvent : actionEvents) {
                this.ruleService.extraItemDiscountEvents(item, actionEvent);
            }
        }
        return invoice;
    }

    private Bill finalItemDiscount(Bill invoice){
        for (BillItem item : invoice.getBill_items()) {
            this.ruleService.setItemDiscount(item);
        }
        return invoice;
    }

}
