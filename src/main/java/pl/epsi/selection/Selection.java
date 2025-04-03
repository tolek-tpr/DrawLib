package pl.epsi.selection;

import pl.epsi.transform.Transformation;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class Selection <T extends Selectable> {

    private final ArrayList<T> locations = new ArrayList<>();

    public Selection<T> add(T location) {
        this.locations.add(location);
        return this;
    }

    public Selection<T> union(Selection<T> s1, Selection<T> s2) {
        return new Selection<T>().add(s1).add(s2);
    }

    public Selection<T> add(Selection<T> s1) {
        s1.locations.forEach(this::add);
        return this;
    }

    public T get(int idx) {
        if (idx < this.locations.size())
            return this.locations.get(idx);
        return null;
    }

    public ArrayList<T> getLocations() {
        return this.locations;
    }

    public Selection<T> consume(Consumer<T> consumer) {
        locations.forEach(consumer);
        return this;
    }

    @SuppressWarnings("unchecked")
    public Selection<T> transform(UnaryOperator<Selectable> operator) {
        Selection<T> target = new Selection<T>();
        locations.forEach(l -> target.add((T) operator.apply(l)));
        this.clear().add(target);
        return this;
    }

    public Selection<T> transform(Transformation transformation) {
        return transform(transformation.getTransformer());
    }

    public Selection<T> clear() {
        this.locations.clear();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        locations.forEach(l -> sb.append(l).append("; "));
        return sb.toString();
    }

}
