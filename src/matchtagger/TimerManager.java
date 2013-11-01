/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matchtagger;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import javax.swing.Timer;

/**
 *
 * @author ericr
 */
public class TimerManager {

    Timer timer;
    TimerState ts = TimerState.NOT_STARTED;
   // public static final DateFormat sdf = new SimpleDateFormat("k:mm:ss");
    Calendar refTime = Calendar.getInstance();
    Calendar startTime = Calendar.getInstance();
    Calendar pausedTime = Calendar.getInstance();

    public TimerManager(Timer timer) {
        this.timer = timer;
    }

    public void start() {
        refTime = Calendar.getInstance();
        startTime = Calendar.getInstance();
        timer.start();
        ts = TimerState.RUNNING;
    }

    public void stop() {
//        refTime = Calendar.getInstance();
        timer.stop();
        ts = TimerState.STOPPED;
    }

    public void reset() {
        refTime = Calendar.getInstance();
        timer.stop();
        ts = TimerState.NOT_STARTED;
    }

    public void restart() {
        Calendar now = Calendar.getInstance();
        long mseconds = refTime.getTimeInMillis() + now.getTimeInMillis() - pausedTime.getTimeInMillis();
        refTime.setTimeInMillis(mseconds);
        timer.start();
        ts = TimerState.RUNNING;
    }

    public void paused() {
        timer.stop();
        pausedTime = Calendar.getInstance();
        ts = TimerState.PAUSED;
    }

    public String getTime() {
        Calendar now = Calendar.getInstance();
        long millis = now.getTimeInMillis() - refTime.getTimeInMillis();
        // return sdf.format(mseconds);

        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), // The change is in this line
                TimeUnit.MILLISECONDS.toSeconds(millis)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }

    public TimerState getTimerState() {
        return ts;
    }
}
