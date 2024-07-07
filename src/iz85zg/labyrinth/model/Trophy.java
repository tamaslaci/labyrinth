/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iz85zg.labyrinth.model;

import javax.swing.ImageIcon;

/**
 *
 * @author iz85zg
 */
public class Trophy extends Field {
    private Position position;
    
    public Trophy(int x, int y){
        this.type = FieldType.TROPHY;
        this.icon = new ImageIcon("icon/trophy.jpg");
        this.position = new Position(x, y);
    }
    
    public Position getPosition(){
        return this.position;
    }
}
