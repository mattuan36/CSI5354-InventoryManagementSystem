package com.baylor.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "item")
public class Item implements Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;
    private String description;
    private Long quantity;
    private Long price;
    private Long minQuantity;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Override
    public List<Item> getItems() {
        return List.of(this);
    }
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;
}
