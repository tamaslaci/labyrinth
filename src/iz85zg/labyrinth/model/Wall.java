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
public class Wall extends Field {
    public Wall(){
        this.type = FieldType.WALL;
        this.icon = new ImageIcon("icon/wall.jpg");
    }
}
