package pl.epsi.shape;

import pl.epsi.selection.Selectable;
import pl.epsi.selection.Selection;

import java.util.ArrayList;

public abstract class Shape <T extends Selectable> {

    protected final ArrayList<Cutoff> cutoffs = new ArrayList<>();
    protected final boolean[] shouldContinue = { false };

    public Shape<T> addCutoff(Cutoff c) {
        cutoffs.add(c);
        return this;
    }

    // Step is usually between 0-1 as a progress, but in circle it is between 0-90 and arc 0-360
    public abstract Selection<T> select(boolean filled, Selectable.Factory factory, double step);

    protected void checkShouldSkip(double step, boolean[] shouldContinue) {
        for (Cutoff c : cutoffs) {
            if (step >= c.start && step <= c.end) {
                shouldContinue[0] = true;
                break;
            }
        }
    }

}
