package pl.epsi.transform;

import pl.epsi.selection.Selectable;
import pl.epsi.util.Vec3d;

import java.util.function.UnaryOperator;

public class RotateTransformation implements Transformation {

    private final Vec3d center;
    private double angleX, angleY, angleZ;

    public RotateTransformation(Vec3d center, Vec3d angle) {
        this.center = center;
        this.angleX = Math.toRadians(angle.x);
        this.angleY = Math.toRadians(angle.y);
        this.angleZ = Math.toRadians(angle.z);
    }

    public RotateTransformation setAngle(Vec3d angle) {
        this.angleX = Math.toRadians(angle.x);
        this.angleY = Math.toRadians(angle.y);
        this.angleZ = Math.toRadians(angle.z);
        return this;
    }

    public UnaryOperator<Selectable> getTransformer() {
        return selectable -> {
            Vec3d loc = selectable.getLocation();
            double x = loc.x - center.x;
            double y = loc.y - center.y;
            double z = loc.z - center.z;

            // Rotate around X-axis
            double tempY = y * Math.cos(angleX) - z * Math.sin(angleX);
            double tempZ = y * Math.sin(angleX) + z * Math.cos(angleX);
            y = tempY;
            z = tempZ;

            // Rotate around Y-axis
            double tempX = x * Math.cos(angleY) + z * Math.sin(angleY);
            tempZ = -x * Math.sin(angleY) + z * Math.cos(angleY);
            x = tempX;
            z = tempZ;

            // Rotate around Z-axis
            tempX = x * Math.cos(angleZ) - y * Math.sin(angleZ);
            tempY = x * Math.sin(angleZ) + y * Math.cos(angleZ);
            x = tempX;
            y = tempY;

            return selectable.setLocation(new Vec3d(center.x + x, center.y + y, center.z + z));
        };
    }

}
