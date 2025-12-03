package com.baylor.app.mediator;

import com.baylor.app.model.Item;
import com.baylor.app.model.Location;
import com.baylor.app.model.Vendor;
import com.baylor.app.service.ItemService;
import com.baylor.app.service.LocationService;
import com.baylor.app.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorLocationMediator implements Mediator {

    @Autowired
    private LocationService locationService;

    public Long getAvailableSpace(String locationId){
        return locationService.getAvailableSpace(locationId);
    }

    public Boolean isReserved(String vendorId, String locationId){
        return locationService.isReserved(vendorId, locationId);
    }

    public String reserveLocation(String vendorId, String locationId){
        return locationService.reserveLocation(locationId, vendorId);
    }
}
