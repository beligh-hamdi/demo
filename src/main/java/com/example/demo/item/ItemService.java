package com.example.demo.item;

import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

@Service
public class ItemService implements CommandLineRunner {

    private final ItemRepository itemRepo;

    public ItemService(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("-------------CREATE GROCERY ITEMS-------------------------------\n");

        createGroceryItems();

        System.out.println("\n----------------SHOW ALL GROCERY ITEMS---------------------------\n");

        showAllGroceryItems();

        System.out.println("\n--------------GET ITEM BY NAME-----------------------------------\n");

        getGroceryItemByName("Whole Wheat Biscuit");

        System.out.println("\n-----------GET ITEMS BY CATEGORY---------------------------------\n");

        getItemsByCategory("millets");

        System.out.println("\n-----------UPDATE CATEGORY NAME OF SNACKS CATEGORY----------------\n");

        updateCategoryName("snacks");

        System.out.println("\n----------DELETE A GROCERY ITEM----------------------------------\n");

        deleteGroceryItem("Kodo Millet");

        System.out.println("\n------------FINAL COUNT OF GROCERY ITEMS-------------------------\n");

        findCountOfGroceryItems();

        System.out.println("\n-------------------THANK YOU---------------------------");
    }


    void createGroceryItems() {
        System.out.println("Data creation started...");
        itemRepo.save(new Item("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks"));
        itemRepo.save(new Item("Kodo Millet", "XYZ Kodo Millet healthy", 2, "millets"));
        itemRepo.save(new Item("Dried Red Chilli", "Dried Whole Red Chilli", 2, "spices"));
        itemRepo.save(new Item("Pearl Millet", "Healthy Pearl Millet", 1, "millets"));
        itemRepo.save(new Item("Cheese Crackers", "Bonny Cheese Crackers Plain", 6, "snacks"));
        System.out.println("Data creation complete...");
    }

    public List<Item> showAllItems() {
        return StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(itemRepo.findAll().iterator(), Spliterator.ORDERED), false)
                .toList();
    }

    public void showAllGroceryItems() {
        itemRepo.findAll().forEach(item -> System.out.println(getItemDetails(item)));
    }

    public void getGroceryItemByName(String name) {
        System.out.println("Getting item by name: " + name);
        Item item = itemRepo.findItemByName(name);
        System.out.println(getItemDetails(item));
    }

    public void getItemsByCategory(String category) {
        System.out.println("Getting items for the category " + category);
        List<Item> list = itemRepo.findAll(category);
        list.forEach(item -> System.out.println("Name: " + item.getName() + ", Quantity: " + item.getQuantity()));
    }

    public void findCountOfGroceryItems() {
        long count = itemRepo.count();
        System.out.println("Number of documents in the collection: " + count);
    }

    public String getItemDetails(Item item) {
        System.out.println(
                "Item Name: " + item.getName() +
                        ", \nQuantity: " + item.getQuantity() +
                        ", \nItem Category: " + item.getCategory()
        );
        return "";
    }


    public void updateCategoryName(String category) {
        String newCategory = "munchies";
        List<Item> list = itemRepo.findAll(category);

        list.forEach(item -> {
            item.setCategory(newCategory);
        });

        List<Item> itemsUpdated = itemRepo.saveAll(list);

        if(itemsUpdated != null)
            System.out.println("Successfully updated " + itemsUpdated.size() + " items.");
    }

    public void deleteGroceryItem(String id) {
        itemRepo.deleteById(id);
        System.out.println("Item with id " + id + " deleted...");
    }
}
