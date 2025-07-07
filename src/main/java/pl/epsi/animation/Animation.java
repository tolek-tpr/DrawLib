package pl.epsi.animation;

import pl.epsi.math.Mat4d;
import pl.epsi.math.Vec3d;
import pl.epsi.math.Vec4d;
import pl.epsi.selection.Selectable;
import pl.epsi.selection.Selection;
import pl.epsi.transform.CompositeTransformation;
import pl.epsi.transform.Transformation;
import pl.epsi.util.Loggers;
import pl.epsi.util.Scheduler;
import pl.epsi.util.Task;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Animation<T extends Selectable> {

    private final int duration;
    private final Scheduler scheduler;
    private final Selection<T> selection;

    private Consumer<Selection<T>> beforeFrame = (a) -> {};
    private Consumer<Selection<T>> afterFrame = (a) -> {};
    private Consumer<Selection<T>> afterAnimation = (a) -> {};

    private final ArrayList<Transformation> transformations = new ArrayList<>();

    // Duration is in seconds.
    public Animation(Scheduler scheduler, int duration, Selection<T> selection) {
        this.scheduler = scheduler;
        this.duration = duration;
        this.selection = selection;
    }

    public Animation<T> add(Transformation t) {
        if (t instanceof CompositeTransformation) {
            Loggers.ANIMATION_LOGGER.error("Error in creating animation, adding a CompositeTransformation in a animation is not allowed!");
            throw new IllegalStateException("Cannot add a CompositeTransformation to a animation!");
        }
        transformations.add(t);
        return this;
    }

    public Animation<T> setAfterFrame(Consumer<Selection<T>> f) {
        this.afterFrame = f;
        return this;
    }
    public Animation<T> setBeforeFrame(Consumer<Selection<T>> f) {
        this.beforeFrame = f;
        return this;
    }
    public Animation<T> setAfterAnimation(Consumer<Selection<T>> f) {
        this.afterAnimation = f;
        return this;
    }

    public void run() {
        int[] elapsedTimeMillis = { 0 };

        Selection<T> currentFrameSelection = selection.clone();

        scheduler.drawLibScheduleRepeatingTask(() -> {
            double frameElapsedPercentage = (double) elapsedTimeMillis[0] / (duration * 1000);

            this.beforeFrame.accept(currentFrameSelection);
            currentFrameSelection.clear().add(selection.clone());

            transformations.forEach(transformation -> {
                double scaleFactor = transformation.getEaseFunction().apply(frameElapsedPercentage);
                Mat4d scaledMatrix = transformation.animationScaleDown(new Vec3d(scaleFactor, scaleFactor, scaleFactor)).getMatrix();
                Vec3d center = transformation.getCenter() == null ?
                        selection.getCenter() : transformation.getCenter();

                currentFrameSelection.getLocations().forEach(loc -> loc.getLocation().subtract(center));
                currentFrameSelection.transform(scaledMatrix);
                currentFrameSelection.getLocations().forEach(loc -> loc.getLocation().add(center));
            });

            this.afterFrame.accept(currentFrameSelection);
            if (frameElapsedPercentage == 1) {
                this.afterAnimation.accept(currentFrameSelection);
            }

            elapsedTimeMillis[0] += 50;
        }, 1, 20 * duration);
    }

}
