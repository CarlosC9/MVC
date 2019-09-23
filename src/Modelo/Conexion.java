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
    
    private Connection conn;
    
    public Conexion() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String sUrl = "jdbc:mysql://localhost:3306/libros?serverTimezone=CET";
            this.conn = DriverManager.getConnection(sUrl, "root", "elrincon1920");
            JOptionPane.showMessageDialog(null, "Conectado!!!!");

        } catch (SQLException ex) {
            this.conn = null;
            throw new RuntimeException("Error con la conexión!!!");
        }
    }
    
    public ResultSet ejecutarConsulta(String consulta) throws SQLException {
        ResultSet rs;
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        
        rs = st.executeQuery(consulta);
        
        return rs;
    }
}
