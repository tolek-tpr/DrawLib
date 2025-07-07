package pl.epsi.transform;

import org.jetbrains.annotations.Nullable;
import pl.epsi.animation.EaseFunctions;
import pl.epsi.math.Mat4d;
import pl.epsi.selection.Selectable;
import pl.epsi.math.Vec3d;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class RotateTransformation implements Transformation {

    private final Vec3d center;
    private Mat4d rotationMatrix;
    private final UnaryOperator<Double> easeFunction;
    private Vec3d angle;

    public RotateTransformation(Vec3d angle) {
        this.center = null;
        this.angle = angle;
        this.rotationMatrix = new Mat4d().rotate(angle);
        this.easeFunction = EaseFunctions.linear();
    }

    public RotateTransformation(Vec3d angle, Vec3d center) {
        this.center = center;
        this.angle = angle;
        this.rotationMatrix = new Mat4d().rotate(angle);
        this.easeFunction = EaseFunctions.linear();
    }

    public RotateTransformation(Vec3d angle, @Nullable Vec3d center, UnaryOperator<Double> easeFunction) {
        this.center = center;
        this.angle = angle;
        this.rotationMatrix = new Mat4d().rotate(angle);
        this.easeFunction = easeFunction;
    }

    public RotateTransformation setAngle(Vec3d angle) {
        this.angle = angle;
        this.rotationMatrix = new Mat4d().rotate(angle);
        return this;
    }

    public Vec3d getAngle() { return this.angle; }

    @Override
    public Transformation animationScaleDown(Vec3d scalar) {
        return new RotateTransformation(new Vec3d(angle.x * scalar.x, angle.y * scalar.y, angle.z * scalar.z),
                this.center, this.easeFunction);
    }

    @Override
    public UnaryOperator<Selectable> getTransformer() {
        return selectable ->
            selectable.setLocation(new Vec3d(this.rotationMatrix.multiply(selectable.getLocation().subtract(center))).add(center));
    }

    @Override
    public Mat4d getMatrix() { return this.rotationMatrix; }

    @Override
    public Vec3d getCenter() { return this.center; }

    @Override
    public UnaryOperator<Double> getEaseFunction() { return this.easeFunction; }

}
