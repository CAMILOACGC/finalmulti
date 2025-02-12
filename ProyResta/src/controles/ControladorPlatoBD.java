/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Camilo
 */
package Controles;

import modelos.PlatoP;
import Vista.JIRegisplato;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.platoDAO;
import javax.swing.JTable;

public class ControladorPlatoBD implements ActionListener {
    
    private JIRegisplato frmP;

    public ControladorPlatoBD(JIRegisplato frmP) {
        this.frmP = new JIRegisplato();
        this.frmP.getBtnGuardarPlato().addActionListener(this);
        this.frmP.getBtnEditarPlato().addActionListener(this);
        this.frmP.getBtnEliminarPlato().addActionListener(this);
    }

    public ControladorPlatoBD() {
        this.frmP = new JIRegisplato();    
        this.frmP.getBtnGuardarPlato().addActionListener(this);
        this.frmP.getBtnEditarPlato().addActionListener(this);
        this.frmP.getBtnEliminarPlato().addActionListener(this);
    }
    
    public void iniciar() {
        this.frmP.setTitle("Gestión de Platos");
        this.frmP.setLocation(50, 30);
        this.frmP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frmP.setVisible(true);
    }
    
    public void agregarPlato(PlatoP plato, JTable tabla) {
        Object datos[] = {
            plato.getID(),         // ID del Plato
            plato.getNOM(),        // Nombre del Plato
            plato.getPREC(),       // Precio
            plato.getPRO(),        // Proteína
            plato.getCAN(),        // Cantidad de personas
            plato.getACOM(),       // Acompañamiento
            plato.getGASEOSA()     // Bebida
        };
        DefaultTableModel plantilla = (DefaultTableModel) tabla.getModel();
        plantilla.addRow(datos);
    }
    
    public void enviarDatosDAO(platoDAO platoDAO, JTable tabla) {
        int fila = tabla.getSelectedRow();
        if(fila != -1) {
            PlatoP plato = new PlatoP(
                tabla.getValueAt(fila, 0).toString(),  // ID
                tabla.getValueAt(fila, 1).toString(),  // Nombre
                Double.parseDouble(tabla.getValueAt(fila, 2).toString(),
                tabla.getValueAt(fila, 3).toString(),  // Proteína
                tabla.getValueAt(fila, 4).toString(),  // Cantidad de personas
                tabla.getValueAt(fila, 5).toString(),  // Acompañamiento
                tabla.getValueAt(fila, 6).toString()   // Bebida
            );
            platoDAO.setObjPlato(plato);
        } else {
            JOptionPane.showMessageDialog(frmP, "Por favor, seleccione un plato de la tabla");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(frmP.getBtnGuardarPlato())) {      
            platoDAO platoDAO = new platoDAO();
            DefaultTableModel modelo = platoDAO.consultar();
            frmP.getTablePlatos().setModel(modelo);
        } 
        else if(e.getSource().equals(frmP.getBtnEditarPlato())) {
            try {
                platoDAO platoDAO = new platoDAO();
                enviarDatosDAO(platoDAO, frmP.getTablePlatos());
                JOptionPane.showMessageDialog(frmP, platoDAO.actualizar());
                frmP.getTablePlatos().setModel(platoDAO.consultar());
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(frmP, "Error en los datos numéricos. Verifique el precio del plato.");
            }
        }
        else if(e.getSource().equals(frmP.getBtnEliminarPlato())) {
            int confirmar = JOptionPane.showConfirmDialog(frmP, 
                "¿Está seguro de eliminar este plato?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);
                
            if(confirmar == JOptionPane.YES_OPTION) {
                platoDAO platoDAO = new platoDAO();
                enviarDatosDAO(platoDAO, frmP.getTablePlatos());
                JOptionPane.showMessageDialog(frmP, platoDAO.eliminar());
                frmP.getTablePlatos().setModel(platoDAO.consultar());
            }
        }
    }
}
