package pl.epsi.transform;

import pl.epsi.selection.Selectable;
import pl.epsi.util.Vec3d;

import java.util.function.UnaryOperator;

public class MoveTransformation implements Transformation {

    private final Vec3d offset;

    public MoveTransformation(Vec3d offset) {
        this.offset = offset;
    }

    @Override
    public UnaryOperator<Selectable> getTransformer() {
        return selectable -> selectable.setLocation(selectable.getLocation().add(offset));
    }

}
