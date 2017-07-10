package com.ruleshop.service;

import com.ruleshop.model.*;

import java.util.List;

/**
 * Created by milosandric on 05.07.17.
 */
public interface RuleService {
    public User findUser(String email);

    public List<BuyerCategory> getAllBCategories();

    void addCategory(BuyerCategory category_name);

    void addLimit(CategoryLimit limit);

    BuyerCategory findCategory(int category_id);

    CategoryLimit findLimit(int limit_id);

    void updateLimit(CategoryLimit cl);

    void updateCategory(BuyerCategory bc);

    void addItemCategory(ItemCategory ic);

    List<ItemCategory> getAllICategories();

    ItemCategory findItemCategory(int category_id);

    void updateItemCategory(ItemCategory ic);

    Sale addNewSale(Sale sale);

    List<Sale> getAllSles();

    Sale findSale(int category_id);

    Sale updateSale(Sale sale);

    List<Item> getItemsForOrder();

    Item findItem(int item_id);

    void updateItem(Item item);

    List<Bill> getAllBills();

    List<Bill> getOrderedBills();

    List<Bill> getSuccessBills();

    List<Bill> getRejectedBills();

    Bill findBill(int bill_id);

    Item getItemByName(String item_name);

    void updateBill(Bill bill);

    List<Item> getAllSICategories(String searchCode, String searchName, String category, Double price_from, Double price_to);

    List<Item> getAllItems();

    void addCartItem(Cart cart);

    ItemTest getClassifiedItem(ItemTest newItem);

    List<Cart> getUserCartItems(int id);
}
