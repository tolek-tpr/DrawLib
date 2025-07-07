package pl.epsi.transform;

import pl.epsi.animation.EaseFunctions;
import pl.epsi.math.Mat4d;
import pl.epsi.selection.Selectable;
import pl.epsi.math.Vec3d;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class MoveTransformation implements Transformation {

    private final Mat4d transformMatrix;
    private final UnaryOperator<Double> easeFunction;
    private final Vec3d center = new Vec3d(0, 0, 0);
    private final Vec3d offset;

    public MoveTransformation(Vec3d offset) {
        this.offset = offset;
        this.transformMatrix = new Mat4d().translate(offset);
        this.easeFunction = EaseFunctions.linear();
    }

    public MoveTransformation(Vec3d offset, UnaryOperator<Double> easeFunction) {
        this.offset = offset;
        this.transformMatrix = new Mat4d().translate(offset);
        this.easeFunction = easeFunction;
    }

    public Vec3d getOffset() { return this.offset; }

    @Override
    public Transformation animationScaleDown(Vec3d scalar) {
        return new MoveTransformation(new Vec3d(offset.x * scalar.x, offset.y * scalar.y, offset.z * scalar.z), this.easeFunction);
    }

    @Override
    public UnaryOperator<Selectable> getTransformer() {
        return selectable -> selectable.setLocation(transformMatrix.multiply(selectable.getLocation()));
    }

    @Override
    public Mat4d getMatrix() { return this.transformMatrix; }

    @Override
    public Vec3d getCenter() { return this.center; }

    @Override
    public UnaryOperator<Double> getEaseFunction() { return this.easeFunction; }

}
