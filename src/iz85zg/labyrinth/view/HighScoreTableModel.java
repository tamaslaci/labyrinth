/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iz85zg.labyrinth.view;

import iz85zg.labyrinth.persistence.HighScore;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author iz85zg
 */
public class HighScoreTableModel extends AbstractTableModel{
    private ArrayList<HighScore> highscores;
    private final String[] columns = {"name", "score", "datetime"};
    
    public HighScoreTableModel(ArrayList<HighScore> highscores){
        this.highscores = highscores;
    }
    
    @Override public int getRowCount(){
        return highscores.size();
    }
    
    @Override public int getColumnCount(){
        return 3;
    }
    
    @Override public Object getValueAt(int row, int column){
        HighScore hs = highscores.get(row);
        if(column == 0) return hs.getName();
        if(column == 1) return hs.getScore();
        return hs.getTimestamp();
    }
    
    @Override public String getColumnName(int i){
        return columns[i];
    }
}
