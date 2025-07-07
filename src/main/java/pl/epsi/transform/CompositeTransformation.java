package pl.epsi.transform;

import pl.epsi.math.Mat4d;
import pl.epsi.math.Vec3d;
import pl.epsi.selection.Selectable;

import java.util.LinkedList;
import java.util.function.UnaryOperator;

public class CompositeTransformation implements Transformation {

    private final LinkedList<Transformation> transformations = new LinkedList<>();

    public CompositeTransformation add(Transformation t) {
        transformations.add(t);
        return this;
    }

    @Override
    public UnaryOperator<Selectable> getTransformer() {
        return selectable -> {
            for (Transformation transformation : transformations) selectable = transformation.getTransformer().apply(selectable);
            return selectable;
        };
    }

    @Override
    public Mat4d getMatrix() {
        Mat4d mat = new Mat4d();
        transformations.forEach(t -> mat.multiply(t.getMatrix()));
        return mat;
    }

    @Override
    public Vec3d getCenter() {
        return null;
    }

    @Override
    public Transformation animationScaleDown(Vec3d scalar) {
        throw new IllegalStateException("Cannot call animationScaleDown on a CompositeTransformation!");
    }

    @Override
    public UnaryOperator<Double> getEaseFunction() {
        throw new IllegalStateException("Cannot call getEaseFunction on a CompositeTransformation!");
    }

}
