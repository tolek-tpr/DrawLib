import org.junit.jupiter.api.Test;
import pl.epsi.animation.Animation;
import pl.epsi.math.Vec3d;
import pl.epsi.selection.DefaultSelectable;
import pl.epsi.selection.Selection;
import pl.epsi.shape.Square;
import pl.epsi.transform.MoveTransformation;
import pl.epsi.transform.RotateTransformation;
import pl.epsi.transform.ScaleTransformation;
import pl.epsi.util.Scheduler;
import pl.epsi.util.Task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimationTest {

    @Test
    public void animation() {
        Selection<DefaultSelectable> selection = new Selection<>();
        RotateTransformation transform = new RotateTransformation(new Vec3d(2, 2, 2), new Vec3d(0, -90, 0));
        MoveTransformation move = new MoveTransformation(new Vec3d(10, 10, 10));

        selection.add(new DefaultSelectable().setLocation(new Vec3d(1, 1, 2)));

        Animation<DefaultSelectable> a = new Animation<>(new TestScheduler(), 2, selection);
        a.add(transform);
        a.add(move);
        a.setAfterAnimation((b) -> selection.clear().add(b));
        a.run();

        assertEquals(12, selection.get(0).getLocation().x);
        assertEquals(11, selection.get(0).getLocation().y);
        assertEquals(11, selection.get(0).getLocation().z);
    }

    @Test
    public void scalingAnimation() {
        Selection<DefaultSelectable> selection = new Selection<>();
        ScaleTransformation scale = new ScaleTransformation(new Vec3d(2, 2, 2));

        selection.add(new Square<DefaultSelectable>(new Vec3d(2, 2, 2), 2)
                .select(false, new DefaultSelectable.DefaultSelectableFactory(), 1));

        Animation<DefaultSelectable> a = new Animation<>(new TestScheduler(), 2, selection);
        a.add(scale);
        a.setAfterAnimation((b) -> selection.clear().add(b));
        a.run();

        System.out.println(selection);

        // Top Left
        assertEquals(2, selection.get(0).getLocation().x);
        assertEquals(1, selection.get(0).getLocation().y);
        assertEquals(6, selection.get(0).getLocation().z);

        // Bottom Left
        assertEquals(2, selection.get(1).getLocation().x);
        assertEquals(1, selection.get(1).getLocation().y);
        assertEquals(2, selection.get(1).getLocation().z);

        // Bottom Right
        assertEquals(6, selection.get(2).getLocation().x);
        assertEquals(1, selection.get(2).getLocation().y);
        assertEquals(2, selection.get(2).getLocation().z);

        // Top Right
        assertEquals(6, selection.get(3).getLocation().x);
        assertEquals(1, selection.get(3).getLocation().y);
        assertEquals(6, selection.get(3).getLocation().z);
    }

    public class TestScheduler implements Scheduler {

        private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        @Override
        public Task drawLibScheduleRepeatingTask(Runnable runnable, int delay, int repeatAmount) {
            try {
                scheduler.scheduleAtFixedRate(runnable, 0, 50, TimeUnit.MILLISECONDS);
                Thread.sleep(2010);
                scheduler.shutdown();
            } catch (Exception e) {}
            return null;
        }

        @Override
        public Task drawLibScheduleDelayedTask(Runnable runnable, int delay) {
            return null;
        }

        @Override
        public void drawLibCancelTask(Task t) {

        }
    }

}
