package pl.epsi.selection;

import pl.epsi.math.Mat4d;
import pl.epsi.math.Vec3d;
import pl.epsi.transform.Transformation;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class Selection <T extends Selectable> implements Cloneable {

    private final ArrayList<T> locations = new ArrayList<>();
    private Vec3d center;

    public Selection<T> add(T location) {
        double sumX = 0, sumY = 0, sumZ = 0;
        for (var t : locations) {
            sumX += t.getLocation().x;
            sumY += t.getLocation().y;
            sumZ += t.getLocation().z;
        }
        int n = locations.size();
        this.center = new Vec3d(sumX / n, sumY / n, sumZ / n);
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
        Selection<T> target = new Selection<>();
        locations.forEach(l -> target.add((T) operator.apply(l)));
        this.clear().add(target);
        return this;
    }

    public Selection<T> transform(Transformation transformation) {
        return transform(transformation.getTransformer());
    }

    @SuppressWarnings("unchecked")
    public Selection<T> transform(Mat4d matrix) {
        locations.replaceAll(t -> (T) t.setLocation(matrix.multiply(t.getLocation())));
        return this;
    }

    public Selection<T> clear() {
        this.locations.clear();
        return this;
    }

    public Vec3d getCenter() {
        return this.center;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        locations.forEach(l -> sb.append(l).append("; "));
        return sb.toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Selection<T> clone() {
        Selection<T> sel = new Selection<>();
        this.locations.forEach(loc -> sel.add((T) loc.cloneSelectable()));
        return sel;
    }

}
