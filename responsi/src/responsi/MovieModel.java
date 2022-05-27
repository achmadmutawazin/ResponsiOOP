/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsi;


import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author achmadmutawazin
 */
public class MovieModel {
    String DBurl = "jdbc:mysql://localhost/movie_db";
    String DBusername = "root";
    String DBpassword = "";
    Connection conn;
    Statement statement;

    public MovieModel() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(DBurl,DBusername,DBpassword);
            System.out.println("Connection Success");
        }catch(Exception ex){
            System.out.println("Connection Failed " + ex.getMessage());
        }}
        
        public String [][] read(){
        try{
            int mdata=0;
            String data[][] = new String [getData()][5];

        String query = "SELECT * FROM movie";
        ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[mdata][0] = resultSet.getString("title"); //harus sesuai nama kolom di mysql
                data[mdata][1] = String.valueOf(resultSet.getDouble("plot"));                
                data[mdata][2] = String.valueOf(resultSet.getDouble("character"));
                data[mdata][3] = String.valueOf(resultSet.getDouble("acting"));
                data[mdata][4] = String.valueOf(resultSet.getDouble("score"));
                mdata++;
            }return data;
        }catch(Exception e){
        System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return null;}}
        
        
    public void input(String title, double plot, double character, double acting, double score){
    int mdata=0;
        try {
           String query = "SELECT * FROM movie WHERE title='" + title+"'"; 
           System.out.println(title + " " + plot + " " + character + " " + acting);
           ResultSet resultSet = statement.executeQuery(query);
           
           while (resultSet.next()){ 
                mdata++;
            }
            
            if (mdata==0) {
                query = "INSERT INTO `movie`(`title`,`plot`,`character`,`acting`,`score`) VALUES('"+title+"','"+plot+"','"+character+"','"+acting+"','"+score+"')";
           
                statement = (Statement) conn.createStatement();
                statement.executeUpdate(query); //execute querynya
                JOptionPane.showMessageDialog(null, "Succesfully Added");
            }
            else {
                JOptionPane.showMessageDialog(null, "Data Already Exist");
            }
        } catch (Exception sql) {
            System.out.println(sql.getMessage());   
            JOptionPane.showMessageDialog(null, "Insert Cancelled");
        }
    }    
    
    public void update(String title, double plot, double character, double acting, double score){
    int mdata=0;     try {
           String query = "SELECT * FROM movie WHERE title='" + title+"'"; 
           ResultSet resultSet = statement.executeQuery(query);
           
           while (resultSet.next()){ 
                mdata++;
            }
           
             if (mdata==1) {
                query = "UPDATE movie SET `plot`='" + plot + "',`character`='" + character + "',`acting` = '" + acting + "',`score`='"+ score+"' WHERE `title`= '" + title + "'" ;
                statement = (Statement) conn.createStatement();
                statement.executeUpdate(query); //execute querynya
                JOptionPane.showMessageDialog(null, "Succesfully Updated");
             }
             else {
                 JOptionPane.showMessageDialog(null, "Data Invalid");
             }
           
        } catch (Exception sql) {
            System.out.println(sql.getMessage());   
            JOptionPane.showMessageDialog(null, "Update Invalid");
        }
    }
    
    public int getData(){
    int mdata=0;try{
            statement = conn.createStatement();
            String query = "SELECT * FROM movie";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){ 
                mdata++;
            }
            return mdata;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
    }
    
    public void delete(String title){
    try{
            String query = "DELETE FROM movie WHERE title = '"+title+"'";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Succefully Deleted");
            
        }catch(SQLException sql) {
            System.out.println(sql.getMessage());
        }
    }
    }
