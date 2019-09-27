package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class AlumnoDAO {
    
    Conexion conexion;
    
    public AlumnoDAO(Conexion conexion) {
        this.conexion = conexion;
    }
    
    
    public ResultSet obtenerTodosLosAlumnos() throws SQLException {
        ResultSet rs = null;
        
        Statement stmt = conexion.getConexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = stmt.executeQuery("select * from alumnos");
        
        return rs;
    }
    
    
    public void eliminarAlumno(String registro) throws SQLException {
        
        String sql = "delete from alumnos where registro=" + registro;
        
        int numeroCambios = ejecutarSQLActualizacion(sql);
        
        if (numeroCambios < 1) {
            throw new SQLException();
        }
    }
    
    
    public void modificarAlumno(Alumno alumnoModificado) throws SQLException {
        String sql = "update alumnos set dni='" + alumnoModificado.getDni() + "', nombre='" + alumnoModificado.getNombre() + "', "
        + "apellido1='" + alumnoModificado.getApellido1() + "', apellido2='" + alumnoModificado.getApellido2() + "' "
        + "where registro=" + alumnoModificado.getRegistro();
        
        int numeroCambios = ejecutarSQLActualizacion(sql);
        
        if (numeroCambios < 1) {
            throw new SQLException();
        }
    }
    
    
    private int ejecutarSQLActualizacion(String sql) throws SQLException {
        Statement stament = this.conexion.getConexion().createStatement();
        
        int numeroCambios = stament.executeUpdate(sql);
        
        return numeroCambios;
    }
}
