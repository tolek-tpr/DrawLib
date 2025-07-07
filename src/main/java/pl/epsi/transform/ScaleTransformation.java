package pl.epsi.transform;

import org.jetbrains.annotations.Nullable;
import pl.epsi.animation.EaseFunctions;
import pl.epsi.math.Mat4d;
import pl.epsi.math.Vec3d;
import pl.epsi.math.Vec4d;
import pl.epsi.selection.Selectable;

import java.util.function.UnaryOperator;

public class ScaleTransformation implements Transformation {

    private final Vec3d size;
    private final Mat4d scaleMatrix;
    private final UnaryOperator<Double> easeFunction;
    private final Vec3d center;

    public ScaleTransformation(Vec3d size) {
        this.size = size;
        this.scaleMatrix = new Mat4d().scale(size);
        this.easeFunction = EaseFunctions.linear();
        this.center = null;
    }

    public ScaleTransformation(Vec3d size, Vec3d center) {
        this.size = size;
        this.scaleMatrix = new Mat4d().scale(size);
        this.easeFunction = EaseFunctions.linear();
        this.center = center;
    }

    // For the selections center set `center` to null
    public ScaleTransformation(Vec3d size, @Nullable Vec3d center, UnaryOperator<Double> easeFunction) {
        this.size = size;
        this.scaleMatrix = new Mat4d().scale(size);
        this.easeFunction = easeFunction;
        this.center = center;
    }

    public Vec3d getSize() { return this.size; }

    @Override
    public Transformation animationScaleDown(Vec3d scalar) {
        return new ScaleTransformation(new Vec3d(size.x * scalar.x, size.y * scalar.y, size.z * scalar.z),
                this.center, this.easeFunction);
    }

    @Override
    public UnaryOperator<Selectable> getTransformer() {
        return (selectable) ->
                selectable.setLocation(this.scaleMatrix.multiply(new Vec4d(selectable.getLocation(), 1)));
    }

    @Override
    public Mat4d getMatrix() {
        return this.scaleMatrix;
    }

    @Override
    public Vec3d getCenter() { return this.center; }

    @Override
    public UnaryOperator<Double> getEaseFunction() {
        return this.easeFunction;
    }

}
