package com.ruleshop.service;

import com.ruleshop.DAO.BuyerCategoryDAO;
import com.ruleshop.DAO.UserDAO;
import com.ruleshop.model.*;
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


}
