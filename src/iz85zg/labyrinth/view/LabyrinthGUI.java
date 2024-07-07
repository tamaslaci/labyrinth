/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iz85zg.labyrinth.view;

import iz85zg.labyrinth.persistence.HighScoreDB;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import static javax.swing.SwingConstants.CENTER;

/**
 *
 * @author iz85zg
 */
public class LabyrinthGUI extends JFrame{
    private GamePanelGUI gameSpace;
    private JLabel label;
    private JLabel timeLabel;
    
    private int currentId;
    private String currentPlayer;
    private int currentScore;
    
    private HighScoreDB database;
    
    public LabyrinthGUI(){
        super();
        this.currentId = -1;
        this.currentPlayer = "";
        this.currentScore = 0;
        this.timeLabel = new JLabel("Time: 0s", CENTER);
        this.gameSpace = new GamePanelGUI(this, timeLabel);
        this.database = new HighScoreDB();
        setTitle("Labyrinth Game");
        setSize(576, 576);
        addWindowListener(new WindowAdapter(){
            @Override public void windowClosing(WindowEvent e){
                database.closeConnection();
                System.exit(0);
            }
        });
        
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenuItem newGameMenu = new JMenuItem(new AbstractAction("New Game"){
            @Override public void actionPerformed(ActionEvent e){
                if(currentPlayer.equals("")){
                    gameSpace.makeNewMaze();
                    gameSpace.setTime(0);
                    timeLabel.setText("Time: 0s");
                    gameSpace.setGameOn(true);
                    gameSpace.startTimer();
                    gameSpace.repaint();
                }else{
                    gameSpace.setGameOn(false);
                    gameSpace.stopTimer();
                    int a = JOptionPane.showConfirmDialog(
                                null,
                                "Are you sure to start new game?",
                                "NEW GAME",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                    if(a == JOptionPane.YES_OPTION){
                        gameSpace.makeNewMaze();
                        gameSpace.setTime(0);
                        timeLabel.setText("Time: 0s");
                        gameSpace.setGameOn(true);
                        gameSpace.startTimer();
                        gameSpace.repaint();
                    }else{
                        gameSpace.setGameOn(true);
                        gameSpace.startTimer();
                    }
                }
            }
        });
        JMenuItem highScores = new JMenuItem(new AbstractAction("Scores"){
            @Override public void actionPerformed(ActionEvent e){
                gameSpace.setGameOn(false);
                gameSpace.stopTimer();
                HighScoreDialog dialog = new HighScoreDialog(database.getSortedHighScores(), LabyrinthGUI.this);
                dialog.setVisible(true);
                if(!gameSpace.finishedGame()){
                    gameSpace.setGameOn(true);
                    gameSpace.startTimer();
                }
            }
        });
        JMenuItem exitMenu = new JMenuItem(new AbstractAction("Exit"){
            @Override public void actionPerformed(ActionEvent e){
                gameSpace.setGameOn(false);
                gameSpace.stopTimer();
                exitGame();
                if(!gameSpace.finishedGame()){
                    gameSpace.setGameOn(true);
                    gameSpace.startTimer();
                }
            }
        });
        gameMenu.add(newGameMenu);
        gameMenu.add(highScores);
        gameMenu.addSeparator();
        gameMenu.add(exitMenu);
        menuBar.add(gameMenu);
        setJMenuBar(menuBar);
        
        setLayout(new BorderLayout());
        this.add(this.timeLabel, BorderLayout.NORTH);
        this.add(gameSpace, BorderLayout.CENTER);
        
        this.label = new JLabel("Run away from the Dragon of the Maze and reach the Trophy! " +
                "Use the Arrow or WASD keys!", CENTER);
        this.add(this.label, BorderLayout.SOUTH);
        
        setResizable(false);
        setLocationRelativeTo(null);
        
        ImageIcon img = new ImageIcon("icon/icon.jpg");
        setIconImage(img.getImage());
        pack();
        setVisible(true);
    }
    
    public int getId(){
        return this.currentId;
    }
    public void setId(int id){
        this.currentId = id;
    }
    public int getScore(){
        return this.currentScore;
    }
    public void setScore(int score){
        this.currentScore = score;
    }
    public String getCurrentPlayer(){
        return this.currentPlayer;
    }
    public void setCurrentPlayer(String name){
        this.currentPlayer = name;
    }
    public HighScoreDB getDatabase(){
        return this.database;
    }
    
    /**
     * Assistant method for clearing the game state
     */
    public void startNewGame(){
        gameSpace.makeNewMaze();
        gameSpace.setTime(0);
        timeLabel.setText("Time: 0s");
        gameSpace.setGameOn(true);
        gameSpace.startTimer();
        gameSpace.repaint();
    }
    /**
     * Assistant method for ending gameplay
     */
    public void exitGame(){
        int a = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure to exit?",
                    "EXIT",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
        if(a == JOptionPane.YES_OPTION){
            this.database.closeConnection();
            System.exit(0);
        }
    }
    
}
