/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iz85zg.labyrinth.model;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author iz85zg
 */
public abstract class Field {
    protected FieldType type;
    protected ImageIcon icon;
    
    public Field(){
        this.type = FieldType.EMPTY;
        this.icon = new ImageIcon("icon/empty.jpg");
    }
    
    public Image getImage(){
        return this.icon.getImage();
    }
    
    public FieldType getType(){
        return this.type;
    }
}
