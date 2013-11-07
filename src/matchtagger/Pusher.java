/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matchtagger;

import java.awt.Color;

/**
 *
 * @author ericr
 */
public class Pusher implements Comparable<Pusher> {

    private int index;
    private String label = null;
    private String value = null;
    private String category = null;
    private Color color;
    private int x;
    private int y;
    private boolean visible;

    /**
     *
     */
    public Pusher() {
        this.index = 0;
        this.x = 0;
        this.y = 0;        
        this.color = Color.GREEN;
        this.visible = false;
    }

    /**
     *
     * @return
     */
    public int getIndex() {
        return index;
    }

    /**
     *
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    public String getIndexString() {
        return Integer.toString(index);
    }

    /**
     *
     * @return
     */
    public String getLabel() {
        return label;
    }

    /**
     *
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     *
     * @param colorString
     */
    public void setColor(String colorString) {
        try {
            this.color = Color.decode("0x" +  colorString);
        } catch (NumberFormatException e) {
            this.color = Color.GREEN;
        }     
    }

    /**
     *
     * @return
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @return
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     *
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    
    
    @Override
    public String toString() {
        return "Pusher{" + "index=" + index + ", label=" + label + ", value=" + value + ", category=" + category + ", color=" + color + ", x=" + x + ", y=" + y + '}';
    }      

    @Override
    public int compareTo(Pusher o) {
        int thisVal = this.index;
        int anotherVal = o.getIndex();
        return (thisVal<anotherVal ? -1 : (thisVal == anotherVal ? 0 : 1));
    }
    

}

