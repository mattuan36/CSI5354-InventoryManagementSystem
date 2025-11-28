package com.baylor.app.repository;

import com.baylor.app.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, String> {
    public List<Item> findByName(String name);
    public List<Item> findByVendorId(Long vendorId);

    public void deleteByName(String name);
}
