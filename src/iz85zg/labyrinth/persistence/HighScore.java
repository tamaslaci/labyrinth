/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iz85zg.labyrinth.persistence;

import java.sql.Timestamp;

/**
 *
 * @author iz85zg
 */
public class HighScore implements Comparable<HighScore>{
    private final String name;
    private final int score;
    private final Timestamp timestamp;
    
    public HighScore(String name, int score, Timestamp timestamp){
        this.name = name;
        this.score = score;
        this.timestamp = timestamp;
    }

    public String getName(){
        return this.name;
    }

    public int getScore(){
        return this.score;
    }
    
    public Timestamp getTimestamp(){
        return this.timestamp;
    }
    
    @Override public int compareTo(HighScore other){
        return (this.score < other.score) ? -1 : ((this.score == other.score) ? 0 : 1);
    }
    
    @Override public String toString(){
        return String.format("%s, %d, %s", name, score, timestamp);
    }
    
}
