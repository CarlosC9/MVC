/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.AlumnoDAO;
import Modelo.Conexion;
import Vista.JFAlumnos;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

/**
 *
 * @author AlumnadoTarde
 */
public class Controlador {
    
    private Conexion conexion;
    private AlumnoDAO modelo;
    private JFAlumnos vista;

    public Controlador(Conexion conexion, AlumnoDAO modelo, JFAlumnos vista) {
        this.conexion = conexion;
        this.modelo = new AlumnoDAO(this.conexion);
        this.vista = new JFAlumnos();
    }
    
    public void actionListeners() {
        
        
        vista.getTablaAlumnos().addMouseListener(new MouseAdapter() {
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
        
        
    }
    
}
