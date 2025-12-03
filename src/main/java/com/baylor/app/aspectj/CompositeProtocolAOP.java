package com.baylor.app.aspectj;

import com.baylor.app.model.Item;
import com.baylor.app.model.Location;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;

import java.util.ArrayList;
import java.util.List;

@Aspect
public class CompositeProtocolAOP {

    public interface Component {
        public List<Item> getItems();
        default void add(Component c) {}
        default void remove(Component c) {}
    }

    public static class Composite implements Component {
        private List<Component> children = new ArrayList<>();

        @Override public void add(Component c) { children.add(c); }
        @Override public void remove(Component c) { children.remove(c); }
        public List<Component> getChildren() { return children; }

        @Override public List<Item> getItems() {
            List<Item> items = new ArrayList<>();
            for (Component c: this.getChildren()) {
                items.addAll(c.getItems());
            }
            return items;
        }
    }

    public static class Leaf implements Component {
        @Override public List<Item> getItems() {
            return List.of((Item)(Object)this);
        }
    }

    @DeclareParents(value = "com.baylor.app.model.Location", defaultImpl = Composite.class)
    public Component composite;

    @DeclareParents(value = "com.baylor.app.model.Item", defaultImpl = Leaf.class)
    public Component leaf;

    @Pointcut("execution(* *..LocationService.getItemsByLocation(..)) && args(location)")
    private void getItems(Location location) {}

    @Around("getItems(location)")
    public List<Item> around(Location location) {
        Component root = (Component) location;
        return root.getItems();
    }

}
