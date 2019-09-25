
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author AlumnadoTarde
 */
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
        Statement stament = this.conexion.getConexion().createStatement();
        
        int numeroCambios = stament.executeUpdate("delete from alumnos where registro=" + registro);
        
        if (numeroCambios < 1) {
            throw new SQLException();
        }
    }
    
    
}
