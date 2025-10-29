package com.baylor.app.repository;

import com.baylor.app.model.Vendor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends CrudRepository<Vendor, String> {
    public List<Vendor> findByName(String name);
}
