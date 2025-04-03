package pl.epsi.util;

public class Vec3d extends Vec3<Double> {

    public Vec3d(Double x, Double y, Double z) {
        super(x, y, z);
    }

    public Vec3d(Vec3d source) {
        super(source);
    }

    public Vec3d() {
        this.x = 0d;
        this.y = 0d;
        this.z = 0d;
    }

    @Override
    public Vec3d add(Vec3<Double> source) {
        this.x += source.x;
        this.y += source.y;
        this.z += source.z;
        return this;
    }

    @Override
    public Vec3d add(Double x, Double y, Double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    @Override
    public Vec3d add(Vec3<Double> source, Vec3<Double> dest) {
        this.x += source.x;
        this.y += source.y;
        this.z += source.z;
        dest.add(source);
        return this;
    }

    @Override
    public Vec3d add(Double x, Double y, Double z, Vec3<Double> dest) {
        this.x += x;
        this.y += y;
        this.z += z;
        dest.add(x, y, z);
        return this;
    }

    @Override
    public Vec3d getBlockPos() {
        return new Vec3d(Math.floor(x), Math.floor(y), Math.floor(z));
    }
}
