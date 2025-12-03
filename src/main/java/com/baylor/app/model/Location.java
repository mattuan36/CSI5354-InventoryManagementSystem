package com.baylor.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "location")
public class Location implements Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @Transient
    private List<Component> children = new ArrayList<>();

    public void add(Component c) { children.add(c); }
    public void remove(Component c) { children.remove(c); }

    @Override
    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        for (Component component : children) {
            items.addAll(component.getItems());
        }
        return items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
