/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author AlumnadoTarde
 */
public class Conexion {
    
    private Connection con;
    
    public Conexion() throws SQLException {
        
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        String sUrl = "jdbc:mysql://localhost:3306/libros?serverTimezone=CET";
        this.con = DriverManager.getConnection(sUrl, "root", "elrincon1920");
        
    }

    public Connection getConexion() {
        return con;
    }
    
    
    
}
