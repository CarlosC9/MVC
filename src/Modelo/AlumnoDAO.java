/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.mysql.cj.protocol.Resultset;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author AlumnadoTarde
 */
public class AlumnoDAO {
    
    Conexion conexion;
    
    public AlumnoDAO() {
        conexion = new Conexion(); 
    }
    
    public ArrayList<Alumno> obtenerTodosLosAlumnos() {
        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
        
        conexion.ejecutarConsulta("");
    }
    
    
}
