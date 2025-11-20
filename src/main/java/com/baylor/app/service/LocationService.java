package com.baylor.app.service;

import com.baylor.app.mediator.Comp;
import com.baylor.app.mediator.Mediator;
import com.baylor.app.model.Item;
import com.baylor.app.model.Location;
import com.baylor.app.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService implements Comp {

    @Autowired
    private LocationRepository locationRepository;

    private Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

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

}
