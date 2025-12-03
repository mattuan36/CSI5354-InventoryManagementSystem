package com.baylor.app.controller;

import com.baylor.app.model.Vendor;
import com.baylor.app.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/vendor")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @RequestMapping(value = "/{vendorId}", method = RequestMethod.GET)
    public ResponseEntity<Vendor> getVendor(@PathVariable("vendorId") String vendorId) {
        Vendor vendor = vendorService.getVendor(vendorId);
        return new ResponseEntity<>(vendor, HttpStatus.OK);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Vendor>> getVendorByName(@PathVariable("name") String name) {
        List<Vendor> vendor = vendorService.getVendorByName(name);
        return new ResponseEntity<>(vendor, HttpStatus.OK);
    }

    @PutMapping(value="/{vendorId}")
    public ResponseEntity<Vendor> updateVendor(@PathVariable("vendorId") String vendorId, @RequestBody Vendor vendor) {
        return new ResponseEntity<>(vendorService.updateVendor(vendorId, vendor), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Vendor> createVendor(@RequestBody Vendor vendor) {
        return new ResponseEntity<>(vendorService.createVendor(vendor), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{vendorId}")
    public ResponseEntity<String> deleteVendor(@PathVariable("vendorId") String vendorId) {
        return new ResponseEntity<>(vendorService.deleteVendor(vendorId), HttpStatus.OK);
    }
}
