package Controles;

import modelos.Empleado;
import Vista.JIMempleados;
import Vista.JIempleados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.EmpleadoDAO;
import javax.swing.JTable;

public class ControladorBD implements ActionListener {
    
    private  JIempleados frmE;

    public ControladorBD(JIMempleados frmE) {
        this.frmE =new  JIempleados();
        this.frmE.getBtnGuardarEmpleado1().addActionListener(this);
        this.frmE.getBtnactualizarem().addActionListener(this);
        this.frmE.getBtndespedido().addActionListener(this);
    }
   
    public ControladorBD() {
        this.frmE = new JIempleados();    
        this.frmE.getBtnGuardarEmpleado1().addActionListener(this);
        this.frmE.getBtnactualizarem().addActionListener(this);
        this.frmE.getBtndespedido().addActionListener(this);
    }
    
    public void iniciar() {
        this.frmE.setTitle("Gestión de Empleados");
        this.frmE.setLocation(50, 30);
        this.frmE.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frmE.setVisible(true);
    }
    
    public void agregarEmpleados(Empleado emp, JTable tabla) {
        Object datos[] = {
            emp.getID(),         // ID de Persona
            emp.getNOM(),        // Nombre de Persona
            emp.getTEL(),        // Teléfono de Persona
            emp.getCOD(),        // Código de Empleado
            emp.getDIC(),        // Dirección de Empleado
            emp.getSAL(),        // Salario por hora
            emp.getHOR(),        // Horas trabajadas
            emp.calcularSueldoHo() // Sueldo calculado
        };
        DefaultTableModel plantilla = (DefaultTableModel) tabla.getModel();
        plantilla.addRow(datos);
    }
    
    public void enviarDatosDAO(EmpleadoDAO empleadoDAO, JTable tabla) {
        int fila = tabla.getSelectedRow();
        if(fila != -1) {
            Empleado emp = new Empleado(
                tabla.getValueAt(3, fila).toString(),  // COD
                tabla.getValueAt(4, fila).toString(),  // DIC
                Double.parseDouble(tabla.getValueAt(5, fila).toString()),  // SAL
                Integer.parseInt(tabla.getValueAt(6, fila).toString()),    // HOR
                tabla.getValueAt(0, fila).toString(),  // ID
                tabla.getValueAt(1, fila).toString(),  // NOM
                tabla.getValueAt(2, fila).toString()   // TEL
            );
            empleadoDAO.setObjE(emp);
        } else {
            JOptionPane.showMessageDialog(frmE, "Por favor, seleccione un empleado de la tabla");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(frmE.getBtnGuardarEmpleado1())) {      
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            DefaultTableModel modelo = empleadoDAO.consultar();
            // Añadir columna de sueldo calculado si no existe
            if(modelo.getColumnCount() == 7) {
                modelo.addColumn("Sueldo Total");
                // Calcular sueldo para cada fila
                for(int i = 0; i < modelo.getRowCount(); i++) {
                    Empleado emp = new Empleado(
                        modelo.getValueAt(i, 3).toString(),  // COD
                        modelo.getValueAt(i, 4).toString(),  // DIC
                        Double.parseDouble(modelo.getValueAt(i, 5).toString()),  // SAL
                        Integer.parseInt(modelo.getValueAt(i, 6).toString()),    // HOR
                        modelo.getValueAt(i, 0).toString(),  // ID
                        modelo.getValueAt(i, 1).toString(),  // NOM
                        modelo.getValueAt(i, 2).toString()   // TEL
                    );
                    modelo.setValueAt(emp.calcularSueldoHo(), i, 7);
                }
            }
            frmE.getTablaEmplados().setModel(modelo);
        } 
        else if(e.getSource().equals(frmE.getBtnactualizarem())) {
            try {
                EmpleadoDAO empleadoDAO = new EmpleadoDAO();
                enviarDatosDAO(empleadoDAO, frmE.getTablaEmplados());
                JOptionPane.showMessageDialog(frmE, empleadoDAO.actualizar());
                frmE.getTablaEmplados().setModel(empleadoDAO.consultar());
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(frmE, "Error en los datos numéricos. Verifique salario y horas trabajadas.");
            }
        }
        else if(e.getSource().equals(frmE.getBtndespedido())) {
            int confirmar = JOptionPane.showConfirmDialog(frmE, 
                "¿Está seguro de eliminar este empleado?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);
                
            if(confirmar == JOptionPane.YES_OPTION) {
                EmpleadoDAO empleadoDAO = new EmpleadoDAO();
                enviarDatosDAO(empleadoDAO, frmE.getTablaEmplados());
                JOptionPane.showMessageDialog(frmE, empleadoDAO.eliminar(
                    empleadoDAO.getObjE().getID() // Usar ID como clave primaria
                ));
                frmE.getTablaEmplados().setModel(empleadoDAO.consultar());
            }
        }
    }
    
    
}