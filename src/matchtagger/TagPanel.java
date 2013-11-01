/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matchtagger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author ericr
 */
public class TagPanel extends JPanel
        implements ActionListener {

    public static final String BUTTON_PRESSED = "Button Pressed";
    private final Dimension buttonDimension;
    private final Font buttonFont;

    private final GridLayout layout = new GridLayout();
    String oldValue = "";

    public TagPanel(PanelConfig panelConfig) {
        layout.setColumns(panelConfig.getColumns());
        layout.setRows(panelConfig.getRows());
        setLayout(layout);
        setPreferredSize(new Dimension(700, 200));
        setBorder(BorderFactory.createLineBorder(Color.red));
        buttonDimension = new java.awt.Dimension(80, 40);
        buttonFont = new Font("Arial", Font.BOLD, 12);

        List<Pusher> pushers = panelConfig.getPushers();

        for (Pusher pm : pushers) {
            String label = pm.getLabel();
            JButton b = new JButton("<html><center>" + label.replaceAll("] ", "]<br>") + "</center></html>");
            b.setMinimumSize(buttonDimension);
            b.setBackground(pm.getColor());
            b.setFont(buttonFont);
            b.setActionCommand(pm.getName());
            b.setEnabled(pm.isVisible());
            b.addActionListener(this);
            add(b);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();

        String text = b.getActionCommand();

        firePropertyChange(BUTTON_PRESSED, oldValue, text);
        oldValue = "";
    }
}
