/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoplayer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

/**
 *
 * @author ericr
 */
public class TimePanel extends JPanel {

    private final static String START_TIME = "00:00:00";
    private final static String COMPLETE_PERC = "0 %";
    private final JLabel jTimerLabel;
    private final JLabel jSpeedLabel;
    private final JLabel jPositionLabel;
    private final Timer timer;
    private final EmbeddedMediaPlayer mediaPlayer;

    public TimePanel(EmbeddedMediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        this.setLayout(new GridLayout(1, 3));
        jTimerLabel = new JLabel(START_TIME);       
        add(jTimerLabel);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClock();
            }
        });
        jSpeedLabel = new JLabel(getSpeedString());
        add(jSpeedLabel);
        jPositionLabel = new JLabel(COMPLETE_PERC);
        add(jPositionLabel);
    }

    private void updateClock() {        
        jTimerLabel.setText(getTimeString());
        jPositionLabel.setText(getPositionString());
    }

    private String getTimeString() {
        long millis = mediaPlayer.getTime();
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), // The change is in this line
                TimeUnit.MILLISECONDS.toSeconds(millis)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }

    public void startTimer() {
        timer.start();
    }
    
    public void stopTimer() {
        timer.stop();
        jTimerLabel.setText(START_TIME);
    }
    
    private String getSpeedString() {
        float speed = mediaPlayer.getRate();
        return String.format("%1.1f", speed);
    }
    
    private String getPositionString() {
        float pos = mediaPlayer.getPosition();
        return String.format("%2.0f %%", pos*100);
    }
    
    public void updateSpeedLabel() {
        jSpeedLabel.setText(getSpeedString());
    }
}
