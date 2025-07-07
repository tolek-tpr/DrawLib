package pl.epsi.transform;

import pl.epsi.math.Mat4d;
import pl.epsi.math.Vec3d;
import pl.epsi.selection.Selectable;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public interface Transformation {

    Vec3d getCenter();
    Transformation animationScaleDown(Vec3d scalar);
    UnaryOperator<Selectable> getTransformer();
    Mat4d getMatrix();
    UnaryOperator<Double> getEaseFunction();

}
