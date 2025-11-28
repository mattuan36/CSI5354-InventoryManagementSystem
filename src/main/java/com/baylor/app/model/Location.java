package com.baylor.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long room;
    private Long shelf;
    private Long container;
    @Column(columnDefinition = "integer default 10")
    private Long availableSpace;
    private String reserved = "null";

    @OneToMany(mappedBy = "location")
    private List<Item> items;
}
