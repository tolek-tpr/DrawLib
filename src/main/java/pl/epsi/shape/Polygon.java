package pl.epsi.shape;

import pl.epsi.selection.Selectable;
import pl.epsi.selection.Selection;
import pl.epsi.math.Vec3d;

import java.util.ArrayList;

// This class is only for equilateral shapes
public class Polygon<T extends Selectable> extends Shape<T> {

    private final Vec3d center;
    private final int numVertices;

    private final double radius;

    public Polygon(Vec3d center, int numVertices, double sideLength) {
        this.center = center;
        this.numVertices = numVertices;

        this.radius = (sideLength) / (2 * Math.sin(Math.PI / numVertices));
    }

    @Override
    public Selection<T> select(boolean filled, Selectable.Factory factory, double step) {
        Selection<T> selection = new Selection<>();
        ArrayList<Vec3d> vertices = new ArrayList<>();

        new Arc<T>(center, radius, 0, 360).select(false, factory, (double) 360 / numVertices)
                .consume(vertex -> vertices.add(vertex.getLocation()));

        for (int i = 0; i < vertices.size(); i++) {
            if (i != vertices.size() - 1) {
                selection.add(new Line<T>(vertices.get(i), vertices.get(i + 1)).select(false, factory, step));
            } else {
                selection.add(new Line<T>(vertices.get(i), vertices.getFirst()).select(false, factory, step));
            }
        }

        return selection;
    }

}
