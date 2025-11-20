package com.baylor.app.controller;

import com.baylor.app.model.Item;
import com.baylor.app.model.Location;
import com.baylor.app.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @RequestMapping(value = "/{locationId}", method = RequestMethod.GET)
    public ResponseEntity<Location> getLocation(@PathVariable("locationId") String locationId) {
        Location location = locationService.getLocation(locationId);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    // method for demonstrating the composite pattern.
    @RequestMapping(value = "/{locationId}/items", method = RequestMethod.GET)
    public ResponseEntity<List<Item>> getItemsByLocation(@PathVariable("locationId") String locationId) {
        Location location = locationService.getLocation(locationId);
        return new ResponseEntity<>(locationService.getItemsByLocation(location), HttpStatus.OK);
    }

    @PutMapping(value="/{locationId}")
    public ResponseEntity<Location> updateLocation(@PathVariable("locationId") String locationId, @RequestBody Location location) {
        return new ResponseEntity<>(locationService.updateLocation(locationId, location), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        return new ResponseEntity<>(locationService.createLocation(location), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{locationId}")
    public ResponseEntity<String> deleteLocation(@PathVariable("locationId") String locationId) {
        return new ResponseEntity<>(locationService.deleteLocation(locationId), HttpStatus.OK);
    }
}
