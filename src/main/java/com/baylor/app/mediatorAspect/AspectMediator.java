package com.baylor.app.mediatorAspect;

import com.baylor.app.model.Location;
import com.baylor.app.service.ItemService;
import com.baylor.app.service.LocationService;
import com.baylor.app.service.VendorService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
public class AspectMediator {

    @Autowired
    private ItemService itemService;
    @Autowired
    private VendorService vendorService;
    @Autowired
    private LocationService locationService;

    @Bean
    public void notifyLocation(){
        String event = "";
        if(event.equals("getLocations")) {
            List<Location> locations = locationService.getAllLocations();
        }
    }
}
