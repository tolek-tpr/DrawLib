package pl.epsi.selection;

import pl.epsi.math.Vec3d;
import pl.epsi.math.Vec4d;

public interface Selectable {

    Vec3d getLocation();
    Selectable setLocation(Vec3d loc);

    /**
     * This method should return a new instance of your class, with the
     * location set by default, using<br> {@link pl.epsi.math.Vec3d#clone() the clone method }
     * @return New object with the same properties, but a cloned vector
     */
    Selectable cloneSelectable();

    default Selectable setLocation(Vec4d loc) {
        this.setLocation(new Vec3d(loc));
        return this;
    }

    interface Factory {
        Selectable create();
    }

}
