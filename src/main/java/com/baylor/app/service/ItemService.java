package com.baylor.app.service;

import com.baylor.app.mediator.Mediator;
import com.baylor.app.mediator.VendorLocationMediator;
import com.baylor.app.model.Item;
import com.baylor.app.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService{

    @Autowired
    private ItemRepository itemRepository;

    public Item getItem(String itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        return item.orElseThrow(() -> new IllegalArgumentException("Item Not Found"));
    }

    public List<Item> getItemByName(String name) {
        return itemRepository.findByName(name);
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        itemRepository.findAll().forEach(items::add);
        return items;
    }

    public List<Item> getItemByVendor(Long vendorId) {
        return itemRepository.findByVendorId(vendorId);
    }

    public Item updateItem(String itemId, Item item) {
        Item itemToUpdate = getItem(itemId);

        itemToUpdate.setName(item.getName());
        itemToUpdate.setDescription(item.getDescription());
        itemToUpdate.setCategory(item.getCategory());
        itemToUpdate.setQuantity(item.getQuantity());
        itemToUpdate.setMinQuantity(item.getMinQuantity());
        itemToUpdate.setLocation(item.getLocation());
        itemRepository.save(itemToUpdate);

        return itemToUpdate;
    }

    public Item createItem(Item item) {
        itemRepository.save(item);

        return item;
    }

    public String deleteItem(String itemId) {
        String responseMessage = null;
        itemRepository.deleteById(itemId);
        responseMessage = String.format("Item item: %s Deleted Successfully", itemId);
        return responseMessage;
    }

    public String deleteItemByName(String name) {
        String responseMessage = null;
        itemRepository.deleteByName(name);
        responseMessage = String.format("Item item: %s Deleted Successfully", name);
        return responseMessage;
    }
}
