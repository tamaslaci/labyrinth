/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iz85zg.labyrinth.view;

import iz85zg.labyrinth.persistence.HighScore;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

/**
 *
 * @author iz85zg
 */
public class HighScoreDialog extends JDialog{
    private JTable table;
    
    public HighScoreDialog(ArrayList<HighScore> highscores, JFrame frame){
        super(frame, "Highscore table", true);
        this.table = new JTable(new HighScoreTableModel(highscores));
        table.setFillsViewportHeight(true);
        
        this.add(new JScrollPane(table));
        this.setSize(450, 300);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
