/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.AlumnoDAO;
import Modelo.Conexion;
import Vista.JFAlumnos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author AlumnadoTarde
 */
public class Controlador {
    
    private Conexion conexion;
    private AlumnoDAO modelo;
    private JFAlumnos vista;

    public Controlador() {
        this.vista = new JFAlumnos();
        try {
            this.conexion = new Conexion();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this.vista, "No se puede conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        this.modelo = new AlumnoDAO(this.conexion);
        try {
            VistaTabla vTabla = new VistaTabla(modelo.obtenerTodosLosAlumnos());
            this.vista.getTablaAlumnos().setModel(vTabla);
        } catch (SQLException ex) {
            
        }
        this.actionListeners();
       
    }
    
    private void actionListeners() {
        
        
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
        
        vista.getBtnBajas().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    modelo.eliminarAlumno(vista.getTxtRegistro().getText());
                    JOptionPane.showMessageDialog(vista, "Se ha dado de baja al registro " + vista.getTxtRegistro().getText() + " con exito","Finalizado",JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(vista, "Error al intentar dar de baja","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        
        
        
    }
    
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
