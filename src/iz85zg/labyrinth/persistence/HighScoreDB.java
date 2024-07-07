/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iz85zg.labyrinth.persistence;

import java.sql.Timestamp;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author iz85zg
 */
public class HighScoreDB {
    private final Connection connection;
    private ArrayList<HighScore> highscores;
    
    public HighScoreDB(){
        Connection c = null;
        try{
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/labyrinth", "root", "1234");
        }catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        this.connection = c;
        this.highscores = new ArrayList<>();
        loadHighScores();
    }
    
    /**
     * Loading every previous player's record from the connected database
     */
    private void loadHighScores(){
        try(Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM labyrinthgamescores")){
            HighScore data;
            while(rs.next()){
                String name = rs.getString("name");
                int score = rs.getInt("score");
                Timestamp timestamp = rs.getTimestamp("datetime");
                data = new HighScore(name, score, timestamp);
                this.highscores.add(data);
            }
        }catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
    
    /**
     * Getting the list representation of the database's records
     * @return 
     */
    public ArrayList<HighScore> getHighScores(){
        ArrayList<HighScore> list = new ArrayList<>();
        for(HighScore hs : this.highscores){
            list.add(hs);
        }
        return list;
    }
    /**
     * Getting the list representation of the database's records
     * in proper form for the table view
     * @return 
     */
    public ArrayList<HighScore> getSortedHighScores(){
        ArrayList<HighScore> list = this.getHighScores();
        list.sort(null);
        Collections.reverse(list);
        return list;
    }
    
    public void printHighScores(){
        for(HighScore hs : this.highscores){
            System.out.println(hs);
        }
    }
    
    private void refreshHighScoresList(){
        this.highscores = new ArrayList<>();
        this.loadHighScores();
    }
    
    /**
     * Inserting new record to the connected database
     * @param hs
     * @return 
     */
    public int insertHighScore(HighScore hs){
        int returnedID = 0;
        String statement = "INSERT INTO labyrinthgamescores (name, score, datetime) VALUES (?, ?, ?)";
        try(PreparedStatement pstmt = this.connection.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setString(1, hs.getName());
            pstmt.setInt(2, hs.getScore());
            pstmt.setTimestamp(3, hs.getTimestamp());
            pstmt.executeUpdate();
            ResultSet key = pstmt.getGeneratedKeys();
            if(key.next()) returnedID = key.getInt(1);
            key.close();
        }catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        this.refreshHighScoresList();
        return returnedID;
    }
    
    /**
     * Updateing a record int the connected database
     * @param id
     * @param score 
     */
    public void updateHighScore(int id, int score){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String statement = "UPDATE labyrinthgamescores SET score = ?, datetime = ? WHERE id = ?";
        try(PreparedStatement pstmt = this.connection.prepareStatement(statement)){
            pstmt.setInt(1, score);
            pstmt.setTimestamp(2, timestamp);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        }catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        this.refreshHighScoresList();
    }
    
    /**
     * Important close method for ending the game properly
     */
    public void closeConnection(){
        try{
            this.connection.close();
        }catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
}
