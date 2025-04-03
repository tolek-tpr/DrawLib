package pl.epsi.transform;

import pl.epsi.selection.Selectable;

import java.util.function.UnaryOperator;

public interface Transformation {

    UnaryOperator<Selectable> getTransformer();

}
