package pl.epsi.math;

public class Mat4d implements Cloneable {

    private double m00;
    private double m01;
    private double m02;
    private double m03;
    private double m10;
    private double m11;
    private double m12;
    private double m13;
    private double m20;
    private double m21;
    private double m22;
    private double m23;
    private double m30;
    private double m31;
    private double m32;
    private double m33;

    public Mat4d(Mat4d source) {
        this.set(source, this);
    }

    public Mat4d() {
        this.identity();
    }

    public Mat4d identity() {
        this.m00 = 1;
        this.m11 = 1;
        this.m22 = 1;
        this.m33 = 1;
        return this;
    }

    // These functions will clear the current matrix. For composing matrices, use mul(Mat4d) where the Mat4d provided, is your second
    // transform.
    public Mat4d rotate(Vec3d rotation) {
        return this.rotate(rotation, this);
    }

    public Mat4d scale(Vec3d scalingFactors) {
        return this.scale(new Vec4d(scalingFactors, 1), this);
    }
    public Mat4d scale(Vec4d scalingFactors) {
        return this.scale(scalingFactors, this);
    }

    public Mat4d translate(Vec3d translation) {
        return this.translate(translation, this);
    }

    public Mat4d multiply(Mat4d source) {
        return this.multiply(source, this, this);
    }
    public Mat4d multiply(Mat4d source, Mat4d destination) {
        return this.multiply(source, this, destination);
    }

    public Mat4d rotate(Vec3d rotation, Mat4d destination) {
        destination.identity();

        double xRad = Math.toRadians(rotation.x);
        double yRad = Math.toRadians(rotation.y);
        double zRad = Math.toRadians(rotation.z);

        Mat4d xRot = new Mat4d();
        xRot.m11(Math.cos(xRad));
        xRot.m21(-Math.sin(xRad));
        xRot.m12(Math.sin(xRad));
        xRot.m22(Math.cos(xRad));

        Mat4d yRot = new Mat4d();
        yRot.m00(Math.cos(yRad));
        yRot.m20(Math.sin(yRad));
        yRot.m02(-Math.sin(yRad));
        yRot.m22(Math.cos(yRad));

        Mat4d zRot = new Mat4d();
        zRot.m00(Math.cos(zRad));
        zRot.m10(-Math.sin(zRad));
        zRot.m01(Math.sin(zRad));
        zRot.m11(Math.cos(zRad));

        destination.multiply(zRot, yRot, destination).multiply(destination, xRot, destination);
        return destination;
    }
    public Mat4d scale(Vec4d scaleFactors, Mat4d destination) {
        destination.identity();

        destination.m00(scaleFactors.x);
        destination.m11(scaleFactors.y);
        destination.m22(scaleFactors.z);
        destination.m33(1);

        return destination;
    }
    public Mat4d translate(Vec3d translation, Mat4d destination) {
        destination.identity();

        destination.m30(translation.x);
        destination.m31(translation.y);
        destination.m32(translation.z);

        return destination;
    }

    public Mat4d multiply(Mat4d source, Mat4d source2, Mat4d destination) {
        destination.m00 = source.m00 * source2.m00 + source.m01 * source2.m10 + source.m02 * source2.m20 + source.m03 * source2.m30;
        destination.m01 = source.m00 * source2.m01 + source.m01 * source2.m11 + source.m02 * source2.m21 + source.m03 * source2.m31;
        destination.m02 = source.m00 * source2.m02 + source.m01 * source2.m12 + source.m02 * source2.m22 + source.m03 * source2.m32;
        destination.m03 = source.m00 * source2.m03 + source.m01 * source2.m13 + source.m02 * source2.m23 + source.m03 * source2.m33;

        destination.m10 = source.m10 * source2.m00 + source.m11 * source2.m10 + source.m12 * source2.m20 + source.m13 * source2.m30;
        destination.m11 = source.m10 * source2.m01 + source.m11 * source2.m11 + source.m12 * source2.m21 + source.m13 * source2.m31;
        destination.m12 = source.m10 * source2.m02 + source.m11 * source2.m12 + source.m12 * source2.m22 + source.m13 * source2.m32;
        destination.m13 = source.m10 * source2.m03 + source.m11 * source2.m13 + source.m12 * source2.m23 + source.m13 * source2.m33;

        destination.m20 = source.m20 * source2.m00 + source.m21 * source2.m10 + source.m22 * source2.m20 + source.m23 * source2.m30;
        destination.m21 = source.m20 * source2.m01 + source.m21 * source2.m11 + source.m22 * source2.m21 + source.m23 * source2.m31;
        destination.m22 = source.m20 * source2.m02 + source.m21 * source2.m12 + source.m22 * source2.m22 + source.m23 * source2.m32;
        destination.m23 = source.m20 * source2.m03 + source.m21 * source2.m13 + source.m22 * source2.m23 + source.m23 * source2.m33;

        destination.m30 = source.m30 * source2.m00 + source.m31 * source2.m10 + source.m32 * source2.m20 + source.m33 * source2.m30;
        destination.m31 = source.m30 * source2.m01 + source.m31 * source2.m11 + source.m32 * source2.m21 + source.m33 * source2.m31;
        destination.m32 = source.m30 * source2.m02 + source.m31 * source2.m12 + source.m32 * source2.m22 + source.m33 * source2.m32;
        destination.m33 = source.m30 * source2.m03 + source.m31 * source2.m13 + source.m32 * source2.m23 + source.m33 * source2.m33;

        return destination;
    }
    public Mat4d set(Mat4d source, Mat4d destination) {
        destination.m00(source.m00());
        destination.m01(source.m01());
        destination.m02(source.m02());
        destination.m03(source.m03());

        destination.m10(source.m10());
        destination.m11(source.m11());
        destination.m12(source.m12());
        destination.m13(source.m13());

        destination.m20(source.m20());
        destination.m21(source.m21());
        destination.m22(source.m22());
        destination.m23(source.m23());

        destination.m30(source.m30());
        destination.m31(source.m31());
        destination.m32(source.m32());
        destination.m33(source.m33());
        return destination;
    }

    public Mat4d multiply(double num) {
        return this.multiply(num, this);
    }
    public Mat4d multiply(double num, Mat4d destination) {
        return this.multiply(this, new Mat4d().m00(num).m11(num).m22(num).m33(1), destination);
    }
    public Vec4d multiply(Vec4d source, Vec4d destination) {
        return this.multiply(this, source, destination);
    }
    public Vec4d multiply(Vec4d source) {
        return this.multiply(this, source, new Vec4d());
    }
    public Vec4d multiply(Vec3d source, Vec4d destination) {
        return this.multiply(this, new Vec4d(source, 1), destination);
    }
    public Vec4d multiply(Vec3d source) {
        return this.multiply(this, new Vec4d(source, 1), new Vec4d());
    }
    public Vec4d multiply(Mat4d source, Vec4d source2, Vec4d destination) {
        destination.x = source.m00 * source2.x + source.m10 * source2.y + source.m20 * source2.z + source.m30 * source2.w;
        destination.y = source.m01 * source2.x + source.m11 * source2.y + source.m21 * source2.z + source.m31 * source2.w;
        destination.z = source.m02 * source2.x + source.m12 * source2.y + source.m22 * source2.z + source.m32 * source2.w;
        destination.w = source.m03 * source2.x + source.m13 * source2.y + source.m23 * source2.z + source.m33 * source2.w;

        return destination;
    }

    // region Getters
    public double m00() {
        return m00;
    }

    public double m01() {
        return m01;
    }

    public double m02() {
        return m02;
    }

    public double m03() {
        return m03;
    }

    public double m10() {
        return m10;
    }

    public double m11() {
        return m11;
    }

    public double m12() {
        return m12;
    }

    public double m13() {
        return m13;
    }

    public double m20() {
        return m20;
    }

    public double m21() {
        return m21;
    }

    public double m22() {
        return m22;
    }

    public double m23() {
        return m23;
    }

    public double m30() {
        return m30;
    }

    public double m31() {
        return m31;
    }

    public double m32() {
        return m32;
    }

    public double m33() {
        return m33;
    }
    // endregion
    // region Setters
    public Mat4d m33(double m33) {
        this.m33 = m33;
        return this;
    }

    public Mat4d m32(double m32) {
        this.m32 = m32;
        return this;
    }

    public Mat4d m31(double m31) {
        this.m31 = m31;
        return this;
    }

    public Mat4d m30(double m30) {
        this.m30 = m30;
        return this;
    }

    public Mat4d m23(double m23) {
        this.m23 = m23;
        return this;
    }

    public Mat4d m22(double m22) {
        this.m22 = m22;
        return this;
    }

    public Mat4d m21(double m21) {
        this.m21 = m21;
        return this;
    }

    public Mat4d m20(double m20) {
        this.m20 = m20;
        return this;
    }

    public Mat4d m13(double m13) {
        this.m13 = m13;
        return this;
    }

    public Mat4d m12(double m12) {
        this.m12 = m12;
        return this;
    }

    public Mat4d m11(double m11) {
        this.m11 = m11;
        return this;
    }

    public Mat4d m10(double m10) {
        this.m10 = m10;
        return this;
    }

    public Mat4d m03(double m03) {
        this.m03 = m03;
        return this;
    }

    public Mat4d m02(double m02) {
        this.m02 = m02;
        return this;
    }

    public Mat4d m01(double m01) {
        this.m01 = m01;
        return this;
    }

    public Mat4d m00(double m00) {
        this.m00 = m00;
        return this;
    }
    // endregion

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[").append(m00).append("] [").append(m10).append("] [").append(m20).append("] [").append(m30).append("]\n");
        s.append("[").append(m01).append("] [").append(m11).append("] [").append(m21).append("] [").append(m31).append("]\n");
        s.append("[").append(m02).append("] [").append(m12).append("] [").append(m22).append("] [").append(m32).append("]\n");
        s.append("[").append(m03).append("] [").append(m13).append("] [").append(m23).append("] [").append(m33).append("]\n");
        return s.toString();
    }

    @Override
    public Mat4d clone() {
        return new Mat4d(this);
    }

}
