package pl.epsi.shape;

import pl.epsi.selection.Selectable;
import pl.epsi.selection.Selection;
import pl.epsi.math.Vec3d;

public class Square<T extends Selectable> extends Shape<T> {

    private final double halfSideLength;
    private Vec3d origin;

    public Square(Vec3d origin, double sideLength) {
        this.halfSideLength = sideLength / 2;
        this.origin = origin;
    }

    public Square<T> setOrigin(Vec3d origin) {
        this.origin = origin;
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Selection<T> select(boolean filled, Selectable.Factory factory, double step) {
        step = step * halfSideLength * 2;
        Selection<T> selection = new Selection<>();

        for (double i = -halfSideLength; i < halfSideLength; i += step) {
            checkShouldSkip(i, shouldContinue);
            if (shouldContinue[0]) {
                shouldContinue[0] = false;
                continue;
            }

            selection.add((T) factory.create().setLocation(new Vec3d(origin.x + i, origin.y, origin.z + halfSideLength)));
            selection.add((T) factory.create().setLocation(new Vec3d(origin.x + i, origin.y, origin.z - halfSideLength)));
            selection.add((T) factory.create().setLocation(new Vec3d(origin.x + halfSideLength, origin.y, origin.z + i)));
            selection.add((T) factory.create().setLocation(new Vec3d(origin.x - halfSideLength, origin.y, origin.z + i)));
        }

        return selection;
    }

}
