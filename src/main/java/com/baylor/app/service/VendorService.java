package com.baylor.app.service;

import com.baylor.app.mediator.Mediator;
import com.baylor.app.mediator.VendorLocationMediator;
import com.baylor.app.model.Item;
import com.baylor.app.model.Vendor;
import com.baylor.app.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendorService{

    Mediator mediator;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public Vendor getVendor(String vendorId) {
        Optional<Vendor> vendor = vendorRepository.findById(vendorId);
        return vendor.orElseThrow(() -> new IllegalArgumentException("Vendor Not Found"));
    }

    public List<Vendor> getVendorByName(String name) {
        return vendorRepository.findByName(name);

    }

    public List<Vendor> getAllVendors() {
        List<Vendor> vendors = new ArrayList<>();
        vendorRepository.findAll().forEach(vendors::add);
        return vendors;
    }

    public Vendor updateVendor(String vendorId, Vendor vendor) {
        Vendor vendorToUpdate = getVendor(vendorId);

        vendorToUpdate.setName(vendor.getName());
        vendorToUpdate.setAddress(vendor.getAddress());
        vendorRepository.save(vendorToUpdate);

        return vendorToUpdate;
    }

    public Vendor createVendor(Vendor vendor) {
        vendorRepository.save(vendor);

        return vendor;
    }

    public String deleteVendor(String vendorId) {
        String responseMessage = null;
        vendorRepository.deleteById(vendorId);
        responseMessage = String.format("Vendor: %s Deleted Successfully", vendorId);
        return responseMessage;
    }

    public Long getAvailableSpace(String locationId){
        return mediator.getAvailableSpace(locationId);
    }

    public String reserveLocation(String vendorId, String locationId) {
        String responseMessage = null;
        return mediator.reserveLocation(locationId, vendorId);
    }

}
