/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controles;

import Vista.JIMeseros;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SpinnerListModel;
import javax.swing.table.DefaultTableModel;
import modelos.ClienteDAO;
import modelos.PlatoP;

/**
 *
 * @author Estudiante
 */
public class ControladorMeseros implements ActionListener{
    private JIMeseros fmrp;
    private int numP = 0;
    private DefaultTableModel modelo;


    public ControladorMeseros (JIMeseros fmrp) {
        this.fmrp = fmrp;
    }
    public ControladorMeseros() {
        this.fmrp = new JIMeseros();
    }
    public void iniciar()
    {
        
        fmrp.setTitle("Registar productos");
        fmrp.setLocation(10,30);
        fmrp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fmrp.setVisible(true);
        fmrp.getBtnGuardarpedido().addActionListener(this);
        fmrp.getBtnEliminarpedido().addActionListener(this);
        actualizarPlatos();
    }
    
    private void actualizarPlatos() {
        // Pedir al usuario que ingrese el nombre del producto
        ClienteDAO cDAO = new ClienteDAO();
        ArrayList<String> platos = cDAO.consultarNombrePlatos();
        System.out.println(platos);
        fmrp.getjSplatos().setModel(new SpinnerListModel(platos));
    }
    
    private void guardarPedido() {
        String nombrePlato = fmrp.getjSplatos().getValue().toString();
        String nombre = fmrp.getTxtNombrepersona1().getText();
        String cedula = fmrp.getTxtcedula1().getText();
        String email = fmrp.getTxtEMAIL1().getText();
        String telefono = fmrp.getTxttel().getText();
        ClienteDAO cDAO = new ClienteDAO();
        Double precio = cDAO.obtenerPrecioPlato(nombrePlato);
        modelo = (DefaultTableModel) fmrp.getTablePedido().getModel();
        numP += 1;
        String[] rowData = {String.valueOf(numP), nombre, nombrePlato, cedula, email, telefono, String.valueOf(precio)};
        modelo.addRow(rowData);
    }

    
    private void eliminarPedido() {
        int selectedRow = fmrp.getTablePedido().getSelectedRow();
        if (selectedRow != -1) {
            modelo = (DefaultTableModel) fmrp.getTablePedido().getModel();
            modelo.removeRow(selectedRow);
        }
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(fmrp.getBtnGuardarpedido())){ 
            guardarPedido();
        }
        else if(e.getSource().equals(fmrp.getBtnEliminarpedido())){ 
            eliminarPedido();
        }
    }
}
