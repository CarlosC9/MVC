package controller;

import model.AlumnoDAO;
import model.Conexion;
import view.JFAlumnos;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import model.Alumno;


public class Controlador {
    
    
    private Conexion conexion;
    private AlumnoDAO modelo;
    private JFAlumnos vista;

    
    public Controlador() {
        
        this.vista = new JFAlumnos();
        
        try {
            this.conexion = new Conexion();
            this.modelo = new AlumnoDAO(this.conexion);
            this.refrescarDatosTabla();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this.vista, "No se puede conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        
        this.createListeners();
       
    }
    
    
    
    private void createListeners() {
        
        
        this.vista.getTablaAlumnos().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable tabla = vista.getTablaAlumnos();
                
                int fila = tabla.getSelectedRow();
                
                vista.getTxtDni().setText(String.valueOf(tabla.getValueAt(fila, 1)));
                vista.getTxtRegistro().setText(String.valueOf(tabla.getValueAt(fila, 0)));
                vista.getTxtNombre().setText(String.valueOf(tabla.getValueAt(fila, 2)));
                vista.getTxtApellido1().setText(String.valueOf(tabla.getValueAt(fila, 3)));
                vista.getTxtApellido2().setText(String.valueOf(tabla.getValueAt(fila, 4)));
            }
            
        });
        
        
        this.vista.getBtnBajaRegistro().addActionListener((ActionEvent arg0) -> {
            try {
                modelo.eliminarAlumno(vista.getTxtRegistro().getText());
                this.refrescarDatosTabla();
                JOptionPane.showMessageDialog(vista, "Se ha dado de baja al registro " 
                        + vista.getTxtRegistro().getText() + " con exito","Finalizado",JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(vista, "Error al intentar dar de baja","Error",JOptionPane.ERROR_MESSAGE);
            }
        });
        
        
        this.vista.getBtnModificarRegistro().addActionListener((ActionEvent arg0) -> {
            
            String registro = vista.getTxtRegistro().getText();
            String dni = vista.getTxtDni().getText();
            String nombre = vista.getTxtNombre().getText();
            String apellido1 = vista.getTxtApellido1().getText();
            String apellido2 = vista.getTxtApellido2().getText();
            
            Alumno alumnoModificado = new Alumno(registro,dni, nombre, apellido1, apellido2);
            
            try {
                modelo.modificarAlumno(alumnoModificado);
                this.refrescarDatosTabla();
                JOptionPane.showMessageDialog(vista, "Se ha modificado el registro " 
                        + vista.getTxtRegistro().getText() + " con exito","Finalizado",JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(vista, "Error al intentar modificar","Error",JOptionPane.ERROR_MESSAGE);
            }
            
        });
        
        
        this.vista.getBtnSalir().addActionListener((ActionEvent arg0) -> {
            System.exit(0);
        });
        
    }
    
    
    private void refrescarDatosTabla() throws SQLException {
        VistaTabla vTabla = new VistaTabla(modelo.obtenerTodosLosAlumnos());
        vista.getTablaAlumnos().setModel(vTabla);
    }
    
    
    class VistaTabla extends AbstractTableModel{
        ResultSet rs;
        ResultSetMetaData md; 
        int numColumnas;
        int numFilas;

        public VistaTabla(ResultSet rs){
          this.rs=rs;
          try{
              md=rs.getMetaData();
              this.rs.last();
              numFilas=this.rs.getRow();
              numColumnas=md.getColumnCount();

          }
          catch( SQLException ex){
          }
        }
        @Override
        public int getRowCount() {
            return numFilas;

        }

        @Override
        public int getColumnCount() {
            return numColumnas;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            try {
                rs.absolute(rowIndex+1);
                Object o=rs.getObject(columnIndex +1);
                return o;
            }
            catch (SQLException ex){
                return ex.toString();
            }

        }
    
    
    }
    
}
