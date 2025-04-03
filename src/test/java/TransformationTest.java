import org.junit.jupiter.api.Test;
import pl.epsi.selection.DefaultSelectable;
import pl.epsi.selection.Selection;
import pl.epsi.transform.CompositeTransformation;
import pl.epsi.transform.MoveTransformation;
import pl.epsi.transform.RotateTransformation;
import pl.epsi.util.Vec3d;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransformationTest {

    @Test
    public void rotateTest() {
        Selection<DefaultSelectable> s = new Selection<DefaultSelectable>().add(new DefaultSelectable().setLocation(new Vec3d(1d, 1d, 2d)));
        RotateTransformation t = new RotateTransformation(new Vec3d(2d, 2d, 2d), new Vec3d(0d, 90d, 0d));
        s.transform(t);

        assertEquals(2, s.get(0).getLocation().x);
        assertEquals(1, s.get(0).getLocation().y);
        assertEquals(3, s.get(0).getLocation().z);
    }

    @Test
    public void moveTest() {
        Selection<DefaultSelectable> s = new Selection<DefaultSelectable>().add(new DefaultSelectable().setLocation(new Vec3d(1d, 1d, 1d)));
        MoveTransformation t = new MoveTransformation(new Vec3d(10d, 10d, 10d));
        s.transform(t);

        assertEquals(11, s.get(0).getLocation().x);
        assertEquals(11, s.get(0).getLocation().y);
        assertEquals(11, s.get(0).getLocation().z);
    }

    @Test
    public void compositeTest() {
        Selection<DefaultSelectable> s = new Selection<DefaultSelectable>().add(new DefaultSelectable().setLocation(new Vec3d(1d, 1d, 1d)));
        RotateTransformation t = new RotateTransformation(new Vec3d(2d, 2d, 2d), new Vec3d(0d, 90d, 0d));
        MoveTransformation t1 = new MoveTransformation(new Vec3d(10d, 10d, 10d));
        CompositeTransformation t2 = new CompositeTransformation();
        t2.add(t).add(t1);
        s.transform(t2);

        assertEquals(11, s.get(0).getLocation().x);
        assertEquals(11, s.get(0).getLocation().y);
        assertEquals(13, s.get(0).getLocation().z);
    }

}
