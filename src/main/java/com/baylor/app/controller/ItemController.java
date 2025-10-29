package com.baylor.app.controller;

import com.baylor.app.model.Item;
import com.baylor.app.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    public ResponseEntity<Item> getItem(@PathVariable("itemId") String itemId) {
        Item item = itemService.getItem(itemId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Item>> getItemByName(@PathVariable("name") String name) {
        List<Item> item = itemService.getItemByName(name);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping(value="/{itemId}")
    public ResponseEntity<Item> updateItem(@PathVariable("itemId") String itemId, @RequestBody Item item) {
        return new ResponseEntity<>(itemService.updateItem(itemId, item), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        return new ResponseEntity<>(itemService.createItem(item), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable("itemId") String itemId) {
        return new ResponseEntity<>(itemService.deleteItem(itemId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/name/{name}")
    public ResponseEntity<String> deleteItemByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(itemService.deleteItemByName(name), HttpStatus.OK);
    }
}
