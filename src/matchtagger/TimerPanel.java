/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matchtagger;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author ericr
 */
public class TimerPanel extends JPanel {

    private JLabel jTimerLabel;
    private Timer timer;
    private TimerManager tm;

    public TimerPanel(boolean internalContol) {
        this.setLayout(new GridLayout(1, 3));
        jTimerLabel = new JLabel();
        add(jTimerLabel);
        if (internalContol) {
            jTimerLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jTimerLabelMouseClicked(evt);
                }
            });
        }
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClock();
            }
        });
        tm = new TimerManager(timer);
        updateClock();
    }

    private void updateClock() {
        if (tm != null) {
            jTimerLabel.setText(tm.getTime() + " | " + tm.getTimerState().toString());
        }
    }

    private void jTimerLabelMouseClicked(java.awt.event.MouseEvent evt) {
        TimerState curState = tm.getTimerState();

        if (evt.getButton() == MouseEvent.BUTTON1) {
            switch (curState) {
                case NOT_STARTED:
                    tm.start();
                    break;
                case RUNNING:
                    tm.paused();
                    break;
                case PAUSED:
                    tm.restart();
                    break;
                default:
                    System.out.println(curState);
                    throw new AssertionError();
            }
        } else if ((evt.getButton() == MouseEvent.BUTTON3) && (curState == TimerState.PAUSED)) {
            tm.reset();
        }
        updateClock();
    }

    public void startTimer() {
        tm.start();
    }

    public void setPause(boolean pause) {
        if (pause) {
            tm.paused();
        } else {
            tm.restart();
        }
        updateClock();
    }
}
