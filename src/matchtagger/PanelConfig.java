/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matchtagger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Eric
 */
public class PanelConfig {

    private final String name;
    private final int columns;
    private final int rows;
    private List<Pusher> pushers = new ArrayList<>();

    /**
     *
     * @param name
     * @param columns
     * @param rows
     */
    public PanelConfig(String name, int columns, int rows) {
        this.name = name;
        this.columns = columns;
        this.rows = rows;       
    }

    /**
     *
     * @param pushers
     */
    public void setPushers(List<Pusher> pushers) {
        Collections.sort(pushers);
        this.pushers = pushers;
    }

    /**
     * Returns the name of the panel to be used in header
     * 
     * @return      the name of the panel
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public int getColumns() {
        return columns;
    }

    /**
     *
     * @return      the number of rows of this panel
     */
    public int getRows() {
        return rows;
    }

    /**
     *
     * @return
     */
    public List<Pusher> getPushers() {
        return pushers;
    }
}

