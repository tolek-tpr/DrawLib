package pl.epsi.util;

public abstract class Vec3<T extends Number> {

    public T x, y, z;

    public Vec3(T x, T y, T z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3(Vec3<T> source) {
        this.x = source.x;
        this.y = source.y;
        this.z = source.z;
    }

    public Vec3() {}

    public abstract Vec3<T> add(Vec3<T> source);
    public abstract Vec3<T> add(T x, T y, T z);
    public abstract Vec3<T> add(Vec3<T> source, Vec3<T> dest);
    public abstract Vec3<T> add(T x, T y, T z, Vec3<T> dest);
    public abstract Vec3<T> getBlockPos();

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("X: ").append(x).append(" Y: ").append(y).append(" Z: ").append(z);
        return sb.toString();
    }

}
