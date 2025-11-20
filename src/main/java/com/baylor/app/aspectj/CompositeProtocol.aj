package com.baylor.app.aspectj;

import java.util.ArrayList;
import java.util.List;
import com.baylor.app.model.Item;
import com.baylor.app.model.Location;
import com.baylor.app.service.LocationService

public aspect CompositeProtocol {

    // create the roles
	protected interface Component {}
	protected interface Composite extends Component {}
	protected interface Leaf extends Component {}

    // connect roles to OOD objects in model
	declare parents: Location implements Composite;
	declare parents: Item implements Leaf;

    // store children hierarchy
    private List<Component> Composite.children = new ArrayList<>();

    // Composite base methods
    public void Composite.add(Component c) { children.add(c); }
    public void Composite.remove(Component c) { children.remove(c); }
    public List<Component> Composite.getChildren() { return children; }

    // shared composite logic method and implementations
    public abstract List<Item> Component.getItems();

    public List<Item> Leaf.getItems() {
        return List.of((Item)(Object)this);
    }

    public List<Item> Composite.getItems() {
        List<Item> items = new ArrayList<>();
        for (Component c: this.getChildren()) {
            items.addAll(c.getItems());
        }
        return items;
    }

    // the pointcut to be replaced and the logic to be used
    pointcut execute(Location location) :
        execution(List LocationService.getItemsByLocation(Location))
        && args(location);

    List<Item> around(Location location) : execute(location) {
        Component root = (Component) location;
        return root.getItems();
    }
}
