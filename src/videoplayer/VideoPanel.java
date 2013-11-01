/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoplayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

/**
 *
 * @author ericr
 */
public class VideoPanel extends JPanel {

    private final String mediaURL;
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private EmbeddedMediaPlayer mediaPlayer;
    private boolean pause = false;
    private boolean start = false;
    private float speed = (float) 1.0;
    private TimePanel tp;

    public VideoPanel(String mediaURL) {
        this.mediaURL = mediaURL;
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        mediaPlayer = mediaPlayerComponent.getMediaPlayer();

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause = !pause;
                mediaPlayer.setPause(pause);

                JButton b = (JButton) e.getSource();
                if (pause) {
                    b.setText("Continue");
                } else {
                    b.setText("Pause");
                }
            }
        });
        JButton incSpeedButton = new JButton("Increase speed");
        incSpeedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speed = speed * 2;
                mediaPlayer.setRate(speed);
                tp.updateSpeedLabel();
            }
        });
        JButton decSpeedButton = new JButton("Decrease speed");
        decSpeedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speed = speed / 2;
                mediaPlayer.setRate(speed);
                tp.updateSpeedLabel();
            }
        });
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start = !start;
                JButton b = (JButton) e.getSource();
                if (start) {
                    b.setText("Stop");
                    startPlayer();
                } else {
                    b.setText("Start");
                    stopPlayer();
                }
            }
        });
        JPanel controlPanel = new JPanel();
        controlPanel.add(startButton);
        controlPanel.add(pauseButton);
        controlPanel.add(incSpeedButton);
        controlPanel.add(decSpeedButton);

        tp = new TimePanel(mediaPlayer);
        this.add(mediaPlayerComponent);
        this.add(tp);
        this.add(controlPanel);

    }

    public void startPlayer() {
        mediaPlayer.playMedia(mediaURL);
        tp.startTimer();
        //tp.startTimer();
    }

    public void stopPlayer() {
        mediaPlayer.stop();
        tp.stopTimer();
    }

    public long getTime() {
        return mediaPlayer.getTime();
    }

}
