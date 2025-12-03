package com.baylor.app;

import com.baylor.app.aspectj.CompositeProtocolAOP;
import com.baylor.app.model.Category;
import com.baylor.app.model.Item;
import com.baylor.app.model.Location;
import com.baylor.app.model.Vendor;
import com.baylor.app.repository.ItemRepository;
import com.baylor.app.repository.LocationRepository;
import com.baylor.app.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Initializer implements CommandLineRunner {

    private final ItemRepository items;
    private final LocationRepository locations;
    private final VendorRepository vendors;

    @Override
    public void run(String... args) {

        Vendor walmart = new Vendor();
        walmart.setName("Walmart");
        walmart.setAddress("11701 Metcalf Ave, Overland Park, KS 66210");
        vendors.save(walmart);

        Location closet = new Location();
        closet.setDescription("Closet");
        locations.save(closet);
        System.out.println("Closet id: " + closet.getId());

        Location top_shelf = new Location();
        top_shelf.setDescription("Top Shelf");
        locations.save(top_shelf);

        Item towel = new Item();
        towel.setName("towel");
        towel.setCategory(Category.CLEANING_SUPPLIES);
        towel.setDescription("plain white towel");
        towel.setQuantity(2L);
        towel.setPrice(10L);
        towel.setMinQuantity(1L);
        towel.setVendor(walmart);
        towel.setLocation(top_shelf);
        items.save(towel);

        walmart.setItems(List.of(towel));
        vendors.save(walmart);

        CompositeProtocolAOP.Component cCloset = (CompositeProtocolAOP.Component) closet;
        CompositeProtocolAOP.Component cShelf = (CompositeProtocolAOP.Component) top_shelf;
        CompositeProtocolAOP.Component lTowel = (CompositeProtocolAOP.Component) towel;

        cCloset.add(cShelf);
        cShelf.add(lTowel);
    }
}
