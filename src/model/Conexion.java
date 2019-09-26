package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    
    private Connection connector;
    
    public Conexion() throws SQLException {
        
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        String sUrl = "jdbc:mysql://localhost:3306/libros?useTimezone=true&serverTimezone=CET";
        this.connector = DriverManager.getConnection(sUrl, "root", "elrincon1920");
        
    }

    public Connection getConexion() {
        return connector;
    }
    
    
    
}
