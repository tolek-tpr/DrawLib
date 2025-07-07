package pl.epsi.animation;

import java.util.function.UnaryOperator;

public class EaseFunctions {

    public static UnaryOperator<Double> linear() {
        return (a) -> a;
    }

    public static UnaryOperator<Double> easeInQuadratic() { return (a) -> a * a; }
    public static UnaryOperator<Double> easeOutQuadratic() { return (a) -> a * (2 - a); }

}
