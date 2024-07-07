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
public class Dragon extends Field {
    private Position position;
    
    public Dragon(int x, int y){
        this.type = FieldType.DRAGON;
        this.icon = new ImageIcon("icon/dragon.jpg");
        this.position = new Position(x, y);
    }
    
    public void moveTo(Position p){
        this.position = new Position(p.getX(), p.getY());
    }
    
    public Position getPosition(){
        return this.position;
    }
}
