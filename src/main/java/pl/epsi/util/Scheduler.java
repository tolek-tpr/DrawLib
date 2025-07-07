package pl.epsi.util;

public interface Scheduler {

    // For indefinite repetition, set repeat amount to -1
    Task drawLibScheduleRepeatingTask(Runnable runnable, int timeBetweenFrames, int repeatAmount); // Delay = how often
    Task drawLibScheduleDelayedTask(Runnable runnable, int delay);

    void drawLibCancelTask(Task t);

}
