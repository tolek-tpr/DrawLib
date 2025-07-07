package pl.epsi.shape;

import pl.epsi.selection.Selectable;
import pl.epsi.selection.Selection;
import pl.epsi.math.Vec3d;

public class Line<T extends Selectable> extends Shape<T> {

    private Vec3d pos1, pos2;

    public Line(Vec3d pos1, Vec3d pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    public Line<T> setPos1(Vec3d pos1) {
        this.pos1 = pos1;
        return this;
    }

    public Line<T> setPos2(Vec3d pos2) {
        this.pos2 = pos2;
        return this;
    }

    public Vec3d getLocationOnLine(double t) {
        return new Vec3d(pos1.x + t * (pos2.x - pos1.x), pos1.y + t * (pos2.y - pos1.y),
                pos1.z + t * (pos2.z - pos1.z));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Selection<T> select(boolean filled, Selectable.Factory factory, double step) {
        final Selection<T> selection = new Selection<>();
        final double dx = pos2.x - pos1.x;
        final double dy = pos2.y - pos1.y;
        final double dz = pos2.z - pos1.z;

        double distance = Math.sqrt(Math.abs(dx * dx + dy * dy + dz * dz));
        step = distance * step;

        for (double i = 0; i <= 1; i += step) {
            checkShouldSkip(i, shouldContinue);
            if (shouldContinue[0]) {
                shouldContinue[0] = false;
                continue;
            }

            selection.add((T) factory.create().setLocation(getLocationOnLine(i)));
        }

        return selection;
    }

}
