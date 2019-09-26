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


public class Controlador {
    
    //Conector, View and Model
    private Conexion conexion;
    private AlumnoDAO modelo;
    private JFAlumnos vista;

    //Initializes view, conector and model. Also creates listeners
    public Controlador() {
        
        this.vista = new JFAlumnos();
        
        try {
            this.conexion = new Conexion();
            this.modelo = new AlumnoDAO(this.conexion);
            this.refrescarDatosTabla();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this.vista, "No se puede conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        
        this.createListeners();
       
    }
    
    
    //Define the events
    private void createListeners() {
        
        //When user clicks a row, The TextViews show the data of the selected row
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
        
        //When user clicks the deregister button, The database deletes the record written on "regitro" TextView
        this.vista.getBtnBajas().addActionListener((ActionEvent arg0) -> {
            try {
                modelo.eliminarAlumno(vista.getTxtRegistro().getText());
                this.refrescarDatosTabla();
                JOptionPane.showMessageDialog(vista, "Se ha dado de baja al registro " 
                        + vista.getTxtRegistro().getText() + " con exito","Finalizado",JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(vista, "Error al intentar dar de baja","Error",JOptionPane.ERROR_MESSAGE);
            }
        });
        
        /*When user clicks the modify button, The database modify the record written on "regitro" TextView
        with data written on the remaining Textviews*/
        this.vista.getBtnModificaciones().addActionListener((ActionEvent arg0) -> {
            String registro = vista.getTxtRegistro().getText();
            String dni = vista.getTxtDni().getText();
            String nombre = vista.getTxtNombre().getText();
            String apellido1 = vista.getTxtApellido1().getText();
            String apellido2 = vista.getTxtApellido2().getText();
            
            try {
                modelo.modificarAlumno(registro, dni, nombre, apellido1, apellido2);
                this.refrescarDatosTabla();
                JOptionPane.showMessageDialog(vista, "Se ha modificado el registro " 
                        + vista.getTxtRegistro().getText() + " con exito","Finalizado",JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(vista, "Error al intentar modificar","Error",JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // When user clicks the exit button, The program ends
        this.vista.getBtnSalir().addActionListener((ActionEvent arg0) -> {
            System.exit(EXIT_ON_CLOSE);
        });
        
    }
    
    // Refresh the table data
    private void refrescarDatosTabla() throws SQLException {
        VistaTabla vTabla = new VistaTabla(modelo.obtenerTodosLosAlumnos());
        vista.getTablaAlumnos().setModel(vTabla);
    }
    
    // Model to be added to the table for shows data
    class VistaTabla extends AbstractTableModel{
        ResultSet _rs;
        ResultSetMetaData md; //contiene información sobre la estructura de un ResulSet,especialmente sobre sus nom campos
        int _numColumnas;
        int _numFilas;

        public VistaTabla(ResultSet rs){
          this._rs=rs;
          try{
              md=rs.getMetaData();
              _rs.last();
              _numFilas=_rs.getRow();
              _numColumnas=md.getColumnCount();

          }
          catch( SQLException ex){
          }
        }
        @Override
        public int getRowCount() {
            return _numFilas;

        }

        @Override
        public int getColumnCount() {
            return _numColumnas;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            try {
                _rs.absolute(rowIndex+1);
                Object o=_rs.getObject(columnIndex +1);
                return o;
            }
            catch (SQLException ex){
                return ex.toString();
            }

        }
    
    
    }
    
}
