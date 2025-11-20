package com.baylor.app.model;

import java.util.List;

public interface Component {

    // return list of items inside the calling location object
    public List<Item> getItems();
}