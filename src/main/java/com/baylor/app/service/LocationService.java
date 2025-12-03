package com.baylor.app.service;

import com.baylor.app.model.Item;
import com.baylor.app.model.Location;
import com.baylor.app.repository.LocationRepository;
import com.baylor.app.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location getLocation(String locationId) {
        Optional<Location> location = locationRepository.findById(locationId);
        return location.orElseThrow(() -> new IllegalArgumentException("Location Not Found"));
    }

    // method that uses the composite pattern to return all items in a given location.
    public List<Item> getItemsByLocation(Location location) {
        return null; // will be replaced by AspectJ behavior
    }
  
    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        locationRepository.findAll().forEach(locations::add);
        return locations;
    }

    public Location updateLocation(String locationId, Location location) {
        Location locationToUpdate = getLocation(locationId);

        locationToUpdate.setDescription(location.getDescription());
        locationRepository.save(locationToUpdate);

        return locationToUpdate;
    }

    public Location createLocation(Location location) {
        locationRepository.save(location);

        return location;
    }

    public String deleteLocation(String locationId) {
        String responseMessage = null;
        locationRepository.deleteById(locationId);
        responseMessage = String.format("Vendor: %s Deleted Successfully", locationId);
        return responseMessage;
    }

    public Boolean isFull(String locationId) {
        Location location = getLocation(locationId);
        return location.getAvailableSpace() > 0;
    }

    public Long getAvailableSpace(String locationId) {
        Location location = getLocation(locationId);
        return location.getAvailableSpace();
    }

    public Boolean isReserved(String vendorId, String locationId) {
        Location location = getLocation(locationId);
        return location.getReserved().equals(vendorId);
    }

    public String reserveLocation(String vendorId, String locationId) {
        if (isReserved(vendorId, locationId)) {
            return "Already Reserved";
        }else{
            Location location = getLocation(locationId);
            location.setReserved(locationId);
            locationRepository.save(location);
            return "Reserved";
        }
    }

}
