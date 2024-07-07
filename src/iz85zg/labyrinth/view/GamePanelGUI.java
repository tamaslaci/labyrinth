/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iz85zg.labyrinth.view;

import iz85zg.labyrinth.model.Maze;
import iz85zg.labyrinth.persistence.HighScore;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 *
 * @author iz85zg
 */
public class GamePanelGUI extends JPanel{
    private LabyrinthGUI frame;
    private Maze maze;
    private Timer timer;
    private int time;
    private JLabel timeLabel;
    private boolean gameOn;
    
    private enum Direction{
        UP, RIGHT, DOWN, LEFT
    }
    private Direction dragonMove;
    
    public GamePanelGUI(LabyrinthGUI frame, JLabel timeLabel){
        super();
        this.frame = frame;
        setPreferredSize(new Dimension(576, 576));
        this.setBackground(Color.white);
        this.maze = new Maze();
        this.time = 0;
        this.timeLabel = timeLabel;
        this.timer = new Timer(1000, new TimerActionListener());
        this.timer.start();
        this.dragonMove = Direction.LEFT;
        this.gameOn = true;
        
        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "pressed up");
        this.getInputMap().put(KeyStroke.getKeyStroke("W"), "pressed up");
        this.getActionMap().put("pressed up", new AbstractAction(){
            @Override public void actionPerformed(ActionEvent e){
                if(gameOn){
                    maze.movePlayerUp();
                    if(maze.checkVictory()){
                        timer.stop();
                        gameOn = false;
                        if(frame.getCurrentPlayer().equals("")){
                            timeLabel.setText("You've won in " + time + "s! Congratulation!");
                        }else{
                            timeLabel.setText("You've won in " + time + "s! Congratulation! " +
                                                "Player: " + frame.getCurrentPlayer() + " points: " + (frame.getScore()+1));
                        }
                        repaint();
                        
                        checkCurrentPlayer();
                        startNewGameOrExit();
                    }
                    if(maze.checkDefeat()){
                        timer.stop();
                        gameOn = false;
                        if(frame.getCurrentPlayer().equals("")){
                            timeLabel.setText("You've lost in " + time + "s! Try again!");
                        }else{
                            timeLabel.setText("You've lost in " + time + "s! Try again! " +
                                                "Player: " + frame.getCurrentPlayer() + " points: " + frame.getScore());
                        }
                        maze.setPlayerKilled();
                        repaint();
                        
                        startNewGameOrExit();
                    }
                    repaint();
                }
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "pressed down");
        this.getInputMap().put(KeyStroke.getKeyStroke("S"), "pressed down");
        this.getActionMap().put("pressed down", new AbstractAction(){
            @Override public void actionPerformed(ActionEvent e){
                if(gameOn){
                    maze.movePlayerDown();
                    if(maze.checkVictory()){
                        timer.stop();
                        gameOn = false;
                        if(frame.getCurrentPlayer().equals("")){
                            timeLabel.setText("You've won in " + time + "s! Congratulation!");
                        }else{
                            timeLabel.setText("You've won in " + time + "s! Congratulation! " +
                                                "Player: " + frame.getCurrentPlayer() + " points: " + (frame.getScore()+1));
                        }
                        repaint();
                        
                        checkCurrentPlayer();
                        startNewGameOrExit();
                    }
                    if(maze.checkDefeat()){
                        timer.stop();
                        gameOn = false;
                        if(frame.getCurrentPlayer().equals("")){
                            timeLabel.setText("You've lost in " + time + "s! Try again!");
                        }else{
                            timeLabel.setText("You've lost in " + time + "s! Try again! " +
                                                "Player: " + frame.getCurrentPlayer() + " points: " + frame.getScore());
                        }
                        maze.setPlayerKilled();
                        repaint();
                        
                        startNewGameOrExit();
                    }
                    repaint();
                }
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "pressed left");
        this.getInputMap().put(KeyStroke.getKeyStroke("A"), "pressed left");
        this.getActionMap().put("pressed left", new AbstractAction(){
            @Override public void actionPerformed(ActionEvent e){
                if(gameOn){
                    maze.movePlayerLeft();
                    if(maze.checkVictory()){
                        timer.stop();
                        gameOn = false;
                        if(frame.getCurrentPlayer().equals("")){
                            timeLabel.setText("You've won in " + time + "s! Congratulation!");
                        }else{
                            timeLabel.setText("You've won in " + time + "s! Congratulation! " +
                                                "Player: " + frame.getCurrentPlayer() + " points: " + (frame.getScore()+1));
                        }
                        repaint();
                        
                        checkCurrentPlayer();
                        startNewGameOrExit();
                    }
                    if(maze.checkDefeat()){
                        timer.stop();
                        gameOn = false;
                        if(frame.getCurrentPlayer().equals("")){
                            timeLabel.setText("You've lost in " + time + "s! Try again!");
                        }else{
                            timeLabel.setText("You've lost in " + time + "s! Try again! " +
                                                "Player: " + frame.getCurrentPlayer() + " points: " + frame.getScore());
                        }
                        maze.setPlayerKilled();
                        repaint();
                        
                        startNewGameOrExit();
                    }
                    repaint();
                }
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "pressed right");
        this.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed right");
        this.getActionMap().put("pressed right", new AbstractAction(){
            @Override public void actionPerformed(ActionEvent e){
                if(gameOn){
                    maze.movePlayerRight();
                    if(maze.checkVictory()){
                        timer.stop();
                        gameOn = false;
                        if(frame.getCurrentPlayer().equals("")){
                            timeLabel.setText("You've won in " + time + "s! Congratulation!");
                        }else{
                            timeLabel.setText("You've won in " + time + "s! Congratulation! " +
                                                "Player: " + frame.getCurrentPlayer() + " points: " + (frame.getScore()+1));
                        }
                        repaint();
                        
                        checkCurrentPlayer();
                        startNewGameOrExit();
                    }
                    if(maze.checkDefeat()){
                        timer.stop();
                        gameOn = false;
                        if(frame.getCurrentPlayer().equals("")){
                            timeLabel.setText("You've lost in " + time + "s! Try again!");
                        }else{
                            timeLabel.setText("You've lost in " + time + "s! Try again! " +
                                                "Player: " + frame.getCurrentPlayer() + " points: " + frame.getScore());
                        }
                        maze.setPlayerKilled();
                        repaint();
                        
                        startNewGameOrExit();
                    }
                    repaint();
                }
            }
        });
    }
    
    /**
     * Main method for visuality
     * Painting the images for the maze's every field
     * @param g 
     */
    @Override protected void paintComponent(Graphics g){
        Graphics2D graphics = (Graphics2D) g;
        for(int y=0; y<this.maze.getMaze().length; ++y){
            for(int x=0; x<this.maze.getMaze()[y].length; ++x){
                this.maze.hideUnseen();
                graphics.drawImage(this.maze.getMaze()[y][x].getImage(), x * 48, y * 48, 48, 48, Color.WHITE, null);
            }
        }
    }
    
    public void makeNewMaze(){
        this.maze = new Maze();
    }
    
    public int getTime(){
        return this.time;
    }
    public void setTime(int time){
        this.time = time;
    }
    private void incrementTime(){
        ++this.time;
    }
    public void startTimer(){
        if(this.timer.isRunning()){
            this.timer.restart();
        }else{
            this.timer.start();
        }
    }
    public void stopTimer(){
        if(this.timer.isRunning()){
            this.timer.stop();
        }
    }
    public boolean getGameOn(){
        return this.gameOn;
    }
    public void setGameOn(boolean b){
        this.gameOn = b;
    }
    
    /**
     * Random movement implemetation for the maze's dragon figure
     * It can change direction randomly to avoid movement stuck
     */
    private void moveDragon(){
        Random r = new Random();
        boolean p = r.nextBoolean();
        int direction = this.dragonMove.ordinal();
        if(!p) direction = r.nextInt(4);
        Direction[] directions = {Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT};
        boolean moved = false;
        while(!moved){
            Direction d = directions[direction];
            switch(d){
                case UP -> moved = this.maze.moveDragonUp();
                case RIGHT -> moved = this.maze.moveDragonRight();
                case DOWN -> moved = this.maze.moveDragonDown();
                case LEFT -> moved = this.maze.moveDragonLeft();
            }
            if(!moved){
                ++direction;
                direction %= 4;
            }
        }
        this.dragonMove = directions[direction];
    }
    
    class TimerActionListener implements ActionListener {
        /**
         * Timed method for moving the dragon and changeing the elapsed time
         * @param e 
         */
        @Override public void actionPerformed(ActionEvent e){
            incrementTime();
            timeLabel.setText("Time: " + time + "s");
            moveDragon();
            if(maze.checkDefeat()){
                timer.stop();
                gameOn = false;
                if(frame.getCurrentPlayer().equals("")){
                    timeLabel.setText("You've lost in " + time + "s! Try again!");
                }else{
                    timeLabel.setText("You've lost in " + time + "s! Try again! " +
                                        "Player: " + frame.getCurrentPlayer() + " points: " + frame.getScore());
                }
                maze.setPlayerKilled();
                repaint();
                
                startNewGameOrExit();
            }
            repaint();
        }
    }
    
    /**
     * Monitor player status during the game
     * After the first game win a dialog query the player's name
     * After first game player's points increment automatically
     */
    private void checkCurrentPlayer(){
        if(this.frame.getCurrentPlayer().equals("")){
            String name = "";
            while(name.equals("") || name.trim().equals("")){
                name = (String)JOptionPane.showInputDialog(
                                this.frame,
                                "Congratulation! Save your progress!\n What's your name?",
                                "Save current Player's name",
                                JOptionPane.PLAIN_MESSAGE,
                                null, null, null);
                if(name == null){
                    this.frame.exitGame();
                    name = "";
                    break;
                }
                if(name.equals("") || name.trim().equals("")){
                    JOptionPane.showMessageDialog(
                                frame, 
                                "Error with the given name! Try again!", 
                                "ERROR", 
                                JOptionPane.ERROR_MESSAGE);
                }
            }
            if(name != null){
                this.frame.setCurrentPlayer(name);
                if(!name.equals("")){
                    this.frame.setScore(this.frame.getScore() + 1);
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    HighScore hs = new HighScore(this.frame.getCurrentPlayer(), this.frame.getScore(), timestamp);
                    this.frame.setId(this.frame.getDatabase().insertHighScore(hs));
                }
            }
        }else{
            this.frame.setScore(this.frame.getScore() + 1);
            this.frame.getDatabase().updateHighScore(this.frame.getId(), this.frame.getScore());
        }
    }
    
    private void startNewGameOrExit(){
        int a = JOptionPane.showConfirmDialog(
                            this.frame, 
                            "Would you like to play again?",
                            "New game or exit",
                            JOptionPane.YES_NO_OPTION);
        if(a == JOptionPane.YES_OPTION){
            this.frame.startNewGame();
        }else{
            this.frame.exitGame();
        }
    }
    
    /**
     * Checking game state for the game's frame
     * @return 
     */
    public boolean finishedGame(){
        return this.maze.checkDefeat() || this.maze.checkVictory();
    }
}
