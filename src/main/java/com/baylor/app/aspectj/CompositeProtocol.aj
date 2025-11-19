package com.baylor.app.aspectj;

import java.util.ArrayList;
import java.util.List;

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

    // necessary Composite methods
    public void Composite.add(Component c) { children.add(c); }
    public void Composite.remove(Component c) { children.remove(c); }
    public List<Component> Composite.getChildren() { return children; }

    //
    public abstract List<Item> Component.get_items_by_location();

    public List<Item> Leaf.get_items_by_location() {
        return List.of((Item)(Object)this);
    }

    public List<Item> Composite.get_items_by_location() {
        List<Item> items = new ArrayList<>();
        for (Component c: this.getChildren()) {
            items.addAll(c.get_items_by_location());
        }
        return items;
    }
}
