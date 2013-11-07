/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videotagger;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import matchtagger.PanelConfig;
import matchtagger.PanelParser;
import matchtagger.Pusher;
import matchtagger.TagPanel;
import videoplayer.VideoPanel;

/**
 *
 * @author ericr
 */
public class VideoTagger {

    /**
     * @param args the command line arguments
     */
    private List<Pusher> pushers;
    private final TagPanel tagPanel;
    private final VideoPanel playerPanel;
    private final JPanel mainPane;
    private String mediaURL;

    //Specify the look and feel to use.  Valid values:
    //null (use the default), "Metal", "System", "Motif", "GTK+"
    private String getTimeString() {
        long millis = playerPanel.getTime();
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), // The change is in this line
                TimeUnit.MILLISECONDS.toSeconds(millis)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }

    private VideoTagger() {
        PanelParser read = new PanelParser();
        // mediaURL = "/home/ericr/tmp/test.mp4";
        mediaURL = "/home/ericr/Videos/Wartburgia/MAH00514.MP4";
        String homedir = System.getProperty("user.home");
        PanelConfig panelConfig = read.readConfig(homedir + "/mypanel5x8.xml");
        pushers = panelConfig.getPushers();

        playerPanel = new VideoPanel(mediaURL);
        tagPanel = new TagPanel(panelConfig);

        tagPanel.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals(TagPanel.BUTTON_PRESSED)) {
                    String timeString = getTimeString();
                    int index = Integer.parseInt(evt.getNewValue().toString());
                    Pusher p = pushers.get(index);
                    // String s = String.format("%d;%s;%s;%s;%s", index, tm.getTime(), duration, p.getCategory(), p.getValue());
                    String s = String.format("%d;%s;%s;%s", index, timeString, p.getCategory(), p.getValue());
                    System.out.println(s);

                }
            }
        });

        mainPane = new JPanel();

        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.LINE_AXIS));
        mainPane.add(playerPanel);
        mainPane.add(tagPanel);

        //playerPanel.startPlayer();
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Set the look and feel.
        // initLookAndFeel();

        //Create and set up the window.
        JFrame frame = new JFrame("VideoTagger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(100, 100);

        //Create and set up the content pane.
        VideoTagger videoTagger = new VideoTagger();
        videoTagger.mainPane.setOpaque(true); // TODO (what does this mean): content panes must be opaque
        frame.setContentPane(videoTagger.mainPane);
        frame.setSize(1600, 800);

        //Display the window.
        //frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException ex) {
            System.err.println(ex.getStackTrace());
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
