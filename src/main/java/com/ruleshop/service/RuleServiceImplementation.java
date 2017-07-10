package com.ruleshop.service;

import com.ruleshop.DAO.BuyerCategoryDAO;
import com.ruleshop.DAO.UserDAO;
import com.ruleshop.model.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    public ItemTest getClassifiedItem(ItemTest i) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(i);
        kieSession.fireAllRules();
        kieSession.dispose();
        return i;
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

    @Override
    @Transactional
    public List<Cart> getUserCartItems(int id) {
        return this.buyerCategoryDAO.getUserCartItems(id);
    }
}
