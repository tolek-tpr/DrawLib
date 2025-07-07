package pl.epsi.math;

public class Vec4d implements Cloneable {

    public double x, y, z, w;

    public Vec4d(double x, double y, double z, double w) {
        if (w > 1) throw new IllegalStateException("Cannot instance a Vector" +
                " with a bigger W component than 1");
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vec4d(Vec4d source) {
        this.x = source.x;
        this.y = source.y;
        this.z = source.z;
        this.w = source.w;
    }

    public Vec4d(Vec3d source) {
        this.x = source.x;
        this.y = source.y;
        this.z = source.z;
        this.w = 1;
    }

    public Vec4d(Vec3d source, double w) {
        this.x = source.x;
        this.y = source.y;
        this.z = source.z;
        this.w = w;
    }

    public Vec4d() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 1;
    }

    public Vec4d add(Vec4d source) {
        this.x += source.x;
        this.y += source.y;
        this.z += source.z;
        this.w += this.x == 1 && source.w == 1 ? 1 : source.w;
        return this;
    }

    public Vec4d add(double x, double y, double z, double w) {
        this.x += x;
        this.y += y;
        this.z += z;
        this.w += this.x == 1 && w == 1 ? 1 : w;
        return this;
    }

    public Vec4d add(Vec4d source, Vec4d dest) {
        dest.add(source);
        return dest;
    }

    public Vec4d add(double x, double y, double z, double w, Vec4d dest) {
        dest.add(x, y, z, w);
        return dest;
    }

    public double dot(Vec4d other) {
        return this.x * other.x + this.y * other.y + this.z * other.z + this.w * other.w;
    }

    public double dot(Vec4d other, Vec4d dest) {
        return dest.x * other.x + dest.y * other.y + dest.z * other.z + dest.w * other.w;
    }

    public Vec3d getBlockPos() {
        return new Vec3d(Math.floor(x), Math.floor(y), Math.floor(z));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("X: ").append(x).append(" Y: ").append(y).append(" Z: ")
                .append(z).append(" W: ").append(w);
        return sb.toString();
    }

    @Override
    public Vec4d clone() {
        return new Vec4d(x, y, z, w);
    }

}
