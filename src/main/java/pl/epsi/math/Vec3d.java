package pl.epsi.math;

public class Vec3d implements Cloneable {

    public double x, y, z;
    
    public Vec3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3d(Vec3d source) {
        this.x = source.x;
        this.y = source.y;
        this.z = source.z;
    }

    public Vec3d(Vec4d source) {
        this.x = source.x;
        this.y = source.y;
        this.z = source.z;
    }

    public Vec3d() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vec3d add(Vec3d source) {
        this.x += source.x;
        this.y += source.y;
        this.z += source.z;
        return this;
    }

    public Vec3d add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }
    
    public Vec3d add(Vec3d source, Vec3d dest) {
        dest.add(source);
        return dest;
    }

    public Vec3d add(double x, double y, double z, Vec3d dest) {
        dest.add(x, y, z);
        return dest;
    }

    public Vec3d subtract(Vec3d source) {
        this.x -= source.x;
        this.y -= source.y;
        this.z -= source.z;
        return this;
    }

    public Vec3d subtract(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vec3d subtract(Vec3d source, Vec3d dest) {
        dest.subtract(source);
        return dest;
    }

    public Vec3d subtract(double x, double y, double z, Vec3d dest) {
        dest.subtract(x, y, z);
        return dest;
    }

    public double dot(Vec3d other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public Vec3d cross(Vec3d other) {
        return new Vec3d(
                this.y * other.z - this.z * other.y,
                this.z * other.x - this.x * other.z,
                this.x * other.y - this.y * other.x
        );
    }

    public Vec3d cross(Vec3d other, Vec3d dest) {
        double cx = this.y * other.z - this.z * other.y;
        double cy = this.z * other.x - this.x * other.z;
        double cz = this.x * other.y - this.y * other.x;

        dest.x = cx;
        dest.y = cy;
        dest.z = cz;

        return dest;
    }

    public double dot(Vec3d other, Vec3d dest) {
        return dest.x * other.x + dest.y * other.y + dest.z * other.z;
    }
    
    public Vec3d getBlockPos() {
        return new Vec3d(Math.floor(x), Math.floor(y), Math.floor(z));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("X: ").append(x).append(" Y: ").append(y).append(" Z: ").append(z);
        return sb.toString();
    }

    @Override
    public Vec3d clone() {
        return new Vec3d(x, y, z);
    }
    
}
