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

    public PanelConfig(String name, int columns, int rows) {
        this.name = name;
        this.columns = columns;
        this.rows = rows;       
    }

    
    
    public void setPushers(List<Pusher> pushers) {
        Collections.sort(pushers);
//        for (Pusher pm : pushers) {
//            int i = pm.getPos();
//            this.pushers.set(i, pm);
//        }
        this.pushers = pushers;
    }

    public String getName() {
        return name;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public List<Pusher> getPushers() {
        return pushers;
    }
}

