package pl.epsi.shape;

public class Cutoff {

    public final double start;
    public final double end;

    // start and end are the start and end in terms of the t or step variable, normally 0-1, for circles 0-90
    public Cutoff(double start, double end) {
        this.start = start;
        this.end = end;
    }

}
