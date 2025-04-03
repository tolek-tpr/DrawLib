package pl.epsi.shape;

import pl.epsi.selection.Selectable;
import pl.epsi.selection.Selection;
import pl.epsi.util.Vec3d;

public class Circle<T extends Selectable> extends Shape<T> {

    private Vec3d center;
    private double radius;

    public Circle(Vec3d center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Circle<T> setCenter(Vec3d vec) {
        this.center = vec;
        return this;
    }

    public Circle<T> setRadius(double radius) {
        this.radius = radius;
        return this;
    }

    public Vec3d getCenter() { return this.center; }

    public Vec3d getLocationOnCircle(float alpha) {
        return this.getLocationOnCircle(alpha, 0);
    }

    public Vec3d getLocationOnCircle(float alpha, float pitch) {
        double a = Math.abs(Math.cos(Math.toRadians(pitch)));
        double x = center.x - radius * Math.sin(Math.toRadians(alpha)) * a;
        double z = center.z - radius * Math.cos(Math.toRadians(alpha)) * a;
        return new Vec3d(x, center.y, z);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Selection<T> select(boolean filled, Selectable.Factory factory, double step) {
        final Selection<T> selection = new Selection<T>();
        final double cx = center.x;
        final double cy = center.y;
        final double cz = center.z;

        for (double alpha = 0; alpha <= 90; alpha += step) {
            for (double r = filled ? 0 : radius; r <= radius; r += 0.1) {
                checkShouldSkip(alpha, shouldContinue);
                if (shouldContinue[0]) {
                    shouldContinue[0] = false;
                    continue;
                }

                final double dx = r * Math.sin(Math.toRadians(alpha));
                final double dz = r * Math.cos(Math.toRadians(alpha));

                selection.add((T) factory.create().setLocation(new Vec3d(cx + dx, cy, cz - dz)))
                        .add((T) factory.create().setLocation(new Vec3d(cx - dx, cy, cz - dz)))
                        .add((T) factory.create().setLocation(new Vec3d(cx + dx, cy, cz + dz)))
                        .add((T) factory.create().setLocation(new Vec3d(cx - dx, cy, cz + dz)));
            }
        }
        return selection;
    }

}
