package pl.epsi.selection;

import pl.epsi.math.Vec3d;

public class DefaultSelectable implements Selectable {

    private Vec3d location;

    public DefaultSelectable() {
        this.location = new Vec3d();
    }

    @Override
    public Vec3d getLocation() {
        return location;
    }

    @Override
    public DefaultSelectable setLocation(Vec3d loc) {
        this.location = loc;
        return this;
    }

    @Override
    public Selectable cloneSelectable() {
        return new DefaultSelectable().setLocation(this.location.clone());
    }

    @Override
    public String toString() {
        return "Loc: " + this.location;
    }

    public static class DefaultSelectableFactory implements Selectable.Factory {

        @Override
        public Selectable create() {
            return new DefaultSelectable();
        }

    }

}
