package pl.epsi.shape;

import pl.epsi.selection.Selectable;
import pl.epsi.selection.Selection;
import pl.epsi.util.Vec3d;

public class Arc<T extends Selectable> extends Shape<T> {

    protected Vec3d center;
    protected double radius;
    protected double startAngle;
    protected double endAngle;

    public Arc(final Vec3d center, final double radius, final double startAngle, final double endAngle) {
        this.center = center;
        this.radius = radius;
        this.startAngle = startAngle;
        this.endAngle = endAngle;
    }

    public Arc<T> setCenter(Vec3d vec) {
        this.center = vec;
        return this;
    }

    public Arc<T> setRadius(double radius) {
        this.radius = radius;
        return this;
    }

    public Arc<T> setStartAngle(double angle) {
        this.startAngle = angle;
        return this;
    }

    public Arc<T> setEndAngle(double angle) {
        this.endAngle = angle;
        return this;
    }

    public Arc<T> setAngles(double start, double end) {
        this.startAngle = start;
        this.endAngle = end;
        return  this;
    }

    public Vec3d getCenter() { return this.center; }

    @SuppressWarnings("unchecked")
    @Override
    public Selection<T> select(boolean filled, Selectable.Factory factory, double step) {
        final Selection<T> selection = new Selection<>();

        final double cx = center.x;
        final double cz = center.z;

        for (double alpha = startAngle; alpha < endAngle; alpha += step) {
            checkShouldSkip(alpha, shouldContinue);
            if (shouldContinue[0]) {
                shouldContinue[0] = false;
                continue;
            }

            final double x = radius * Math.sin(Math.toRadians(alpha));
            final double z = radius * Math.cos(Math.toRadians(alpha));
            selection.add((T) factory.create().setLocation(new Vec3d(x + cx, center.y, z + cz)));
        }

        return selection;
    }

}