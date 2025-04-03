package pl.epsi.selection;

import pl.epsi.util.Vec3d;

public interface Selectable {

    Vec3d getLocation();
    Selectable setLocation(Vec3d loc);

    interface Factory {
        Selectable create();
    }

}
