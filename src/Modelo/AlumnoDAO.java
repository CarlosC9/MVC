
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
    
    public ArrayList<Alumno> obtenerTodosLosAlumnos() throws SQLException {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        ResultSet rs = null;
        
       

        Statement stmt = conexion.getConexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = stmt.executeQuery("select * from alumnos");
            
        try {
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setRegistro( String.valueOf(rs.getInt(1)) );
                alumno.setDni(rs.getString(2));
                alumno.setNombre(rs.getString(3));
                alumno.setApellido1(rs.getString(4));
                alumno.setApellido2(rs.getString(5));
                
                alumnos.add(alumno);
            }
        } catch (SQLException ex) {
            
        }
        
        return alumnos;
    }
    
    
}
