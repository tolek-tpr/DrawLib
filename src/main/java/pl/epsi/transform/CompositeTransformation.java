package pl.epsi.transform;

import pl.epsi.selection.Selectable;
import pl.epsi.util.Vec3d;

import java.util.LinkedList;
import java.util.function.UnaryOperator;

public class CompositeTransformation implements Transformation {

    private final LinkedList<Transformation> transformations = new LinkedList<>();

    public CompositeTransformation add(Transformation t) {
        transformations.add(t);
        return this;
    }

    @Override
    public UnaryOperator<Selectable> getTransformer() {
        return selectable -> {
            for (Transformation transformation : transformations) selectable = transformation.getTransformer().apply(selectable);
            return selectable;
        };
    }

}
