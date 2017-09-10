package com.ruleshop.service;

import com.ruleshop.DAO.BuyerCategoryDAO;
import com.ruleshop.DAO.UserDAO;
import com.ruleshop.model.*;
import com.ruleshop.util.AllItems;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by milosandric on 05.07.17.
 */
@Service
public class RuleServiceImplementation implements RuleService {

    private final KieContainer kieContainer;

    @Autowired
    public RuleServiceImplementation(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public void getFillingStock(AllItems items) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(items);
        kieSession.getAgenda().getAgendaGroup("order").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    public BillItem getBillItemDiscounts(BillItem item) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(item);
//        kieSession.getAgenda().getAgendaGroup("bill_item_discounts").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
        return item;
    }

    @Override
    public Bill getBillDiscounts(Bill item) {
        return null;
    }

    @Override
    public void extraItemDiscount(BillItem item, Bill invoice) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(invoice);
        kieSession.insert(item);
        kieSession.getAgenda().getAgendaGroup("extra-item-discount-15").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Override
    public void extraItemDiscount30(BillItem item, Bill invoice) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(invoice);
        kieSession.insert(item);
        kieSession.getAgenda().getAgendaGroup("extra-item-discount-30").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Override
    public void extraItemDiscountEvents(BillItem item, Sale actionEvent) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(actionEvent);
        kieSession.insert(item);
        kieSession.getAgenda().getAgendaGroup("extra-item-discount-events").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Override
    @Transactional
    public List<Sale> findByDateEndingAfter(Date date) {
        return this.buyerCategoryDAO.findByDateEndingAfter(date);
    }

    @Override
    public void setItemDiscount(BillItem item) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(item);
        kieSession.getAgenda().getAgendaGroup("final-item-discount").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
    }


    public void filterForBestDiscount(BillItem item) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(item);
        kieSession.getAgenda().getAgendaGroup("best-item-discount").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
    }


    public Bill itemDiscount(Bill invoice) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(invoice);
        kieSession.getAgenda().getAgendaGroup("item-discount").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
        return invoice;
    }

    public Bill getFinalDiscounts(Bill item) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("date2YearsAgo", getdate2YearsAgo());
        kieSession.insert(item);
//        kieSession.getAgenda().getAgendaGroup("bill_final_discount").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
        return item;
    }

    public BillItem getItemsFinalDiscounts(BillItem item) {
        KieSession kieSession = kieContainer.newKieSession();
//        kieSession.setGlobal("date2YearsAgo", getdate2YearsAgo());
        kieSession.insert(item);
//        kieSession.getAgenda().getAgendaGroup("bill_item_final_discount").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
        return item;
    }

    @Override
    @Transactional
    public List<Bill> findByDateAfterAndCustomer_Id(Date date, int id) {
        return this.buyerCategoryDAO.findByDateAfterAndCustomer(date, id);
    }

    @Override
    @Transactional
    public void updateBillItem(BillItem bill_item) {
        this.buyerCategoryDAO.updateBillItem(bill_item);
    }

    @Override
    @Transactional
    public Role findRole(int i) {
        return this.userDAO.findRole(i);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        this.userDAO.addUser(user);
    }

    @Override
    @Transactional
    public void addBuyer(Buyer buyer) {
        this.userDAO.addBuyer(buyer);
    }


    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BuyerCategoryDAO buyerCategoryDAO;

    @Override
    @Transactional
    public User findUser(String email) {
        return this.userDAO.findUser(email);
    }

    @Override
    @Transactional
    public List<BuyerCategory> getAllBCategories() {
        return this.buyerCategoryDAO.getAllBCategories();
    }

    @Override
    @Transactional
    public void addCategory(BuyerCategory category_name) {
        this.buyerCategoryDAO.addCategory(category_name);
    }

    @Override
    @Transactional
    public void addLimit(CategoryLimit limit) {
        this.buyerCategoryDAO.addLimit(limit);
    }

    @Override
    @Transactional
    public BuyerCategory findCategory(int category_id) {
        return this.buyerCategoryDAO.findCactegory(category_id);
    }

    @Override
    @Transactional
    public CategoryLimit findLimit(int limit_id) {
        return this.buyerCategoryDAO.findLimit(limit_id);
    }

    @Override
    @Transactional
    public void updateLimit(CategoryLimit cl) {
        this.buyerCategoryDAO.updateLimit(cl);
    }

    @Override
    @Transactional
    public void updateCategory(BuyerCategory bc) {
        this.buyerCategoryDAO.updateCategory(bc);
    }

    @Override
    @Transactional
    public void addItemCategory(ItemCategory ic) {
        this.buyerCategoryDAO.addItemCategory(ic);
    }

    @Override
    @Transactional
    public List<ItemCategory> getAllICategories() {
        return this.buyerCategoryDAO.getAllICategories();
    }

    @Override
    @Transactional
    public ItemCategory findItemCategory(int category_id) {
        return this.buyerCategoryDAO.findItemCategory(category_id);
    }

    @Override
    @Transactional
    public void updateItemCategory(ItemCategory ic) {
        this.buyerCategoryDAO.updateItemCategory(ic);
    }

    @Override
    @Transactional
    public Sale addNewSale(Sale sale) {
        return this.buyerCategoryDAO.addNewSale(sale);
    }

    @Override
    @Transactional
    public List<Sale> getAllSles() {
        return this.buyerCategoryDAO.getAllSales();
    }

    @Override
    @Transactional
    public Sale findSale(int category_id) {
        return this.buyerCategoryDAO.findSale(category_id);
    }

    @Override
    @Transactional
    public Sale updateSale(Sale sale) {
        return this.buyerCategoryDAO.updateSale(sale);
    }

    @Override
    @Transactional
    public List<Item> getItemsForOrder() {
        return this.buyerCategoryDAO.getItemsForOrder();
    }

    @Override
    @Transactional
    public Item findItem(int item_id) {
        return this.buyerCategoryDAO.findItem(item_id);
    }

    @Override
    @Transactional
    public void updateItem(Item item) {
        this.buyerCategoryDAO.updateItem(item);
    }

    @Override
    @Transactional
    public List<Bill> getAllBills() {
        return this.buyerCategoryDAO.getAllBills();
    }

    @Override
    @Transactional
    public List<Bill> getOrderedBills() {
        return this.buyerCategoryDAO.getOrderedBills();
    }

    @Override
    @Transactional
    public List<Bill> getSuccessBills() {
        return this.buyerCategoryDAO.getSuccessBills();
    }

    @Override
    @Transactional
    public List<Bill> getRejectedBills() {
        return this.buyerCategoryDAO.getRejectedBills();
    }

    @Override
    @Transactional
    public Bill findBill(int bill_id) {
        return this.buyerCategoryDAO.findBill(bill_id);
    }

    @Override
    @Transactional
    public Item getItemByName(String item_name) {
        return this.buyerCategoryDAO.findBillByName(item_name);
    }

    @Override
    @Transactional
    public void updateBill(Bill bill) {
        this.buyerCategoryDAO.updateBill(bill);
    }

    @Override
    @Transactional
    public List<Item> getAllSICategories(String searchCode, String searchName, String category, Double price_from, Double price_to) {
        return this.buyerCategoryDAO.getAllSICategories(searchCode, searchName, category, price_from, price_to);
    }

    @Override
    @Transactional
    public List<Item> getAllItems() {
        return this.buyerCategoryDAO.getAllItems();
    }

    @Override
    @Transactional
    public void addCartItem(Cart cart) {
        this.buyerCategoryDAO.addCartItem(cart);
    }

//    @Override
//    public Item getFillingStock(Item item) {
//        return null;
//    }


    @Override
    @Transactional
    public List<Cart> getUserCartItems(int id) {
        return this.buyerCategoryDAO.getUserCartItems(id);
    }

    @Override
    @Transactional
    public void addItemDiscount(ItemDiscount id) {
        this.buyerCategoryDAO.addItemDiscount(id);
    }

    @Override
    @Transactional
    public void addBillItem(BillItem bitem) {
        this.buyerCategoryDAO.addBillItem(bitem);
    }

    @Override
    @Transactional
    public void addBillDiscount(BillDiscount billDiscount) {
        this.buyerCategoryDAO.addBillDiscount(billDiscount);
    }

    @Override
    @Transactional
    public Bill addBill(Bill bill) {
        return this.buyerCategoryDAO.addBill(bill);

    }


    public Date getdate2YearsAgo() {
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.YEAR, -2); // to get previous year add -1
        Date past2yearsYears = cal.getTime();
        return past2yearsYears;
    }
}
