/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iz85zg.labyrinth.model;

import java.util.Random;

/**
 *
 * @author iz85zg
 */
public class Maze {
    private final int MAX_SIZE = 12;
    private Field[][] maze;
    
    private Player player;
    private Dragon dragon;
    private Trophy trophy;
    
    public Maze(){
        this.maze = new Field[MAX_SIZE][MAX_SIZE];
        Random random = new Random();
        generateMaze(random);
    }
    
    /**
     * Main method for generating the model for the game
     * Random value based algorithm
     * @param random 
     */
    private void generateMaze(Random random){
        int r = random.nextInt(2);
        if(r == 0){
            this.generateHorizontalMaze();
            for(int i=0; i<MAX_SIZE; ++i){
                if(i%2 == 0){
                    int emptyPerRow = random.nextInt(Math.round(MAX_SIZE / 5)) + 1;
                    for(int k=0; k<emptyPerRow; ++k){
                        int n = random.nextInt(MAX_SIZE);
                        if(this.maze[i][n].getType() != FieldType.TROPHY)
                            this.maze[i][n] = new Empty();
                    } 
                }
            }
            int x = random.nextInt(Math.round(MAX_SIZE / 3)) + Math.round(MAX_SIZE / 3);
            int y = random.nextInt(Math.round(MAX_SIZE / 3)) + Math.round(MAX_SIZE / 3);
            if(y%2 == 0) ++y;
            Position dragonP = new Position(x, y);
            this.dragon = new Dragon(dragonP.getX(), dragonP.getY());
            this.maze[dragonP.getY()][dragonP.getX()] = this.dragon;
        }else{
            this.generateVerticalMaze();
            for(int j=0; j<MAX_SIZE; ++j){
                if(j%2 == 1){
                    int emptyPerColumn = random.nextInt(Math.round(MAX_SIZE / 5)) + 1;
                    for(int k=0; k<emptyPerColumn; ++k){
                        int n = random.nextInt(MAX_SIZE);
                        if(this.maze[n][j].getType() != FieldType.TROPHY)
                            this.maze[n][j] = new Empty();
                    } 
                }
            }
            int x = random.nextInt(Math.round(MAX_SIZE / 3)) + Math.round(MAX_SIZE / 3);
            if(x%2 == 1) ++x;
            int y = random.nextInt(Math.round(MAX_SIZE / 3)) + Math.round(MAX_SIZE / 3);
            Position dragonP = new Position(x, y);
            this.dragon = new Dragon(dragonP.getX(), dragonP.getY());
            this.maze[dragonP.getY()][dragonP.getX()] = this.dragon;
        }
    }
    
    /**
     * Assistant method for generating maze with horizontal walls
     */
    private void generateHorizontalMaze(){
        for(int i=0; i<MAX_SIZE; ++i){
            for(int j=0; j<MAX_SIZE; ++j){
                if(i%2 == 0){
                    this.maze[i][j] = new Wall();
                }else{
                    this.maze[i][j] = new Empty();
                }
            }
        }
        this.trophy = new Trophy(MAX_SIZE-1, 0);
        this.maze[0][MAX_SIZE - 1] = this.trophy;
        this.player = new Player(0, MAX_SIZE-1);
        this.maze[MAX_SIZE - 1][0] = this.player;
    }
    
    /**
     * Assistant method for generating maze with vertical walls
     */
    private void generateVerticalMaze(){
        for(int i=0; i<MAX_SIZE; ++i){
            for(int j=0; j<MAX_SIZE; ++j){
                if(j%2 == 1){
                    this.maze[i][j] = new Wall();
                }else{
                    this.maze[i][j] = new Empty();
                }
            }
        }
        this.trophy = new Trophy(MAX_SIZE-1, 0);
        this.maze[0][MAX_SIZE - 1] = this.trophy;
        this.player = new Player(0, MAX_SIZE-1);
        this.maze[MAX_SIZE - 1][0] = this.player;
    }
    
    public final Field[][] getMaze(){
        return this.maze;
    }
    public final Player getPlayer(){
        return this.player;
    }
    public final Dragon getDragon(){
        return this.dragon;
    }
    public final Trophy getTrophy(){
        return this.trophy;
    }
    
    /**
     * Checking if a position is in the maze
     * @param p
     * @return 
     */
    private boolean validPosition(Position p){
        if(p.getX() < 0 || p.getX() >= MAX_SIZE ||
                p.getY() < 0 || p.getY() >= MAX_SIZE){
            return false;
        }
        if(this.maze[p.getY()][p.getX()].getType() == FieldType.WALL ||
                this.maze[p.getY()][p.getX()].getType() == FieldType.UNSEEN_WALL){
            return false;
        }
        return true;
    }
    /**
     * Checking if a move of a figure is valid
     * @param from
     * @param to
     * @return 
     */
    private boolean validMove(Position from, Position to){
        if(!validPosition(from) || !validPosition(to)) return false;
        if(!from.adjacentTo(to)) return false;
        return true;
    }
    
    /**
     * Implementing figure moves
     */
    public void movePlayerUp(){
        Position from = this.player.getPosition();
        Position to = new Position(this.player.getPosition().getX(), this.player.getPosition().getY()-1);
        if(this.validMove(from, to)){
            this.player.moveTo(new Position(to.getX(), to.getY()));
            this.maze[to.getY()][to.getX()] = this.player;
            this.maze[from.getY()][from.getX()] = new Empty();
        }
    }
    public boolean moveDragonUp(){
        Position from = this.dragon.getPosition();
        Position to = new Position(this.dragon.getPosition().getX(), this.dragon.getPosition().getY()-1);
        if(this.validMove(from, to)){
            this.dragon.moveTo(new Position(to.getX(), to.getY()));
            this.maze[to.getY()][to.getX()] = this.dragon;
            this.maze[from.getY()][from.getX()] = new Empty();
            return true;
        }
        return false;
    }
    public void movePlayerDown(){
        Position from = this.player.getPosition();
        Position to = new Position(this.player.getPosition().getX(), this.player.getPosition().getY()+1);
        if(this.validMove(from, to)){
            this.player.moveTo(new Position(to.getX(), to.getY()));
            this.maze[to.getY()][to.getX()] = this.player;
            this.maze[from.getY()][from.getX()] = new Empty();
        }
    }
    public boolean moveDragonDown(){
        Position from = this.dragon.getPosition();
        Position to = new Position(this.dragon.getPosition().getX(), this.dragon.getPosition().getY()+1);
        if(this.validMove(from, to)){
            this.dragon.moveTo(new Position(to.getX(), to.getY()));
            this.maze[to.getY()][to.getX()] = this.dragon;
            this.maze[from.getY()][from.getX()] = new Empty();
            return true;
        }
        return false;
    }
    public void movePlayerLeft(){
        Position from = this.player.getPosition();
        Position to = new Position(this.player.getPosition().getX()-1, this.player.getPosition().getY());
        if(this.validMove(from, to)){
            this.player.moveTo(new Position(to.getX(), to.getY()));
            this.maze[to.getY()][to.getX()] = this.player;
            this.maze[from.getY()][from.getX()] = new Empty();
        }
    }
    public boolean moveDragonLeft(){
        Position from = this.dragon.getPosition();
        Position to = new Position(this.dragon.getPosition().getX()-1, this.dragon.getPosition().getY());
        if(this.validMove(from, to)){
            this.dragon.moveTo(new Position(to.getX(), to.getY()));
            this.maze[to.getY()][to.getX()] = this.dragon;
            this.maze[from.getY()][from.getX()] = new Empty();
            return true;
        }
        return false;
    }
    public void movePlayerRight(){
        Position from = this.player.getPosition();
        Position to = new Position(this.player.getPosition().getX()+1, this.player.getPosition().getY());
        if(this.validMove(from, to)){
            this.player.moveTo(new Position(to.getX(), to.getY()));
            this.maze[to.getY()][to.getX()] = this.player;
            this.maze[from.getY()][from.getX()] = new Empty();
        }
    }
    public boolean moveDragonRight(){
        Position from = this.dragon.getPosition();
        Position to = new Position(this.dragon.getPosition().getX()+1, this.dragon.getPosition().getY());
        if(this.validMove(from, to)){
            this.dragon.moveTo(new Position(to.getX(), to.getY()));
            this.maze[to.getY()][to.getX()] = this.dragon;
            this.maze[from.getY()][from.getX()] = new Empty();
            return true;
        }
        return false;
    }
    
    /**
     * Masking the unseen part of the maze from the player's view
     */
    public void hideUnseen(){
        int origoJ = this.player.getPosition().getX();
        int origoI = this.player.getPosition().getY();
        for(int i=0; i<this.maze.length; ++i){
            for(int j=0; j<this.maze[i].length; ++j){
                int deltaI = Math.abs(origoI - i);
                int deltaJ = Math.abs(origoJ - j);
                if((deltaI > 3 || deltaJ > 3) || 
                    (deltaI == 3 && deltaJ == 3) ||
                    (deltaI == 2 && deltaJ == 3) ||
                    (deltaI == 3 && deltaJ == 2)){
                    if(this.maze[i][j].type == FieldType.WALL){
                        this.maze[i][j] = new UnseenWall();
                    }
                    if(this.maze[i][j].type == FieldType.EMPTY){
                        this.maze[i][j] = new UnseenEmpty();
                    }
                }else{
                    if(this.maze[i][j].type == FieldType.UNSEEN_WALL){
                        this.maze[i][j] = new Wall();
                    }
                    if(this.maze[i][j].type == FieldType.UNSEEN_EMPTY){
                        this.maze[i][j] = new Empty();
                    }
                }
            }
        }
    }
    
    /**
     * Checking game state with the positions of the main figures
     * @return 
     */
    public boolean checkVictory(){
        if(this.player.getPosition().adjacentTo(this.trophy.getPosition())) return true;
        return false;
    }
    
    public boolean checkDefeat(){
        if(this.player.getPosition().adjacentTo(this.dragon.getPosition())) return true;
        return false;
    }
    
    /**
     * Present the end of game with changeing the players icon image
     */
    public void setPlayerKilled(){
        this.maze[this.player.getPosition().getY()][this.player.getPosition().getX()] = new Killed();
    }
}
