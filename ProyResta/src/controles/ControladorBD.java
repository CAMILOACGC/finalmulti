package Controles;

import modelos.Empleado;
import Vista.JIMempleados;
import Vista.JIempleados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import modelos.EmpleadoDAO;
import javax.swing.JTable;

public class ControladorBD implements ActionListener {
    
    private JIempleados frmE;

    public ControladorBD(JIMempleados frmE) {
        this.frmE = new JIempleados();
        this.frmE.getBtnGuardarEmpleado1().addActionListener(this);
        this.frmE.getBtnactualizarem().addActionListener(this);
        this.frmE.getBtndespedido().addActionListener(this);
        // Habilitar selección única de filas
        this.frmE.getTablaEmplados().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
   
    public ControladorBD() {
        this.frmE = new JIempleados();    
        this.frmE.getBtnGuardarEmpleado1().addActionListener(this);
        this.frmE.getBtnactualizarem().addActionListener(this);
        this.frmE.getBtndespedido().addActionListener(this);
        // Habilitar selección única de filas
        this.frmE.getTablaEmplados().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        try {
            // Asumiendo que el orden en la tabla es: cedula, nombre, telefono, codigo, direccion, salario, horas
            String cedula = tabla.getValueAt(fila, 0).toString();    // cedula/ID
            String nombre = tabla.getValueAt(fila, 1).toString();    // nombre
            String telefono = tabla.getValueAt(fila, 2).toString();  // telefono
            String codigo = tabla.getValueAt(fila, 3).toString();    // codigo
            String direccion = tabla.getValueAt(fila, 4).toString(); // direccion
            double salario = Double.parseDouble(tabla.getValueAt(fila, 5).toString());
            int horas = Integer.parseInt(tabla.getValueAt(fila, 6).toString());

            // Debug para ver los valores
            System.out.println("Datos a actualizar:");
            System.out.println("Cedula: " + cedula);
            System.out.println("Nombre: " + nombre);
            System.out.println("Telefono: " + telefono);
            System.out.println("Codigo: " + codigo);
            System.out.println("Direccion: " + direccion);
            System.out.println("Salario: " + salario);
            System.out.println("Horas: " + horas);

            Empleado emp = new Empleado(
                codigo,     // COD
                direccion, // DIC
                salario,   // SAL
                horas,     // HOR
                cedula,    // ID (cédula)
                nombre,    // NOM
                telefono   // TEL
            );
            empleadoDAO.setObjE(emp);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmE, "Error al obtener datos de la fila: " + ex.getMessage());
        }
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
                    try {
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
                    } catch (Exception ex) {
                        System.out.println("Error al calcular sueldo en fila " + i + ": " + ex.getMessage());
                    }
                }
            }
            frmE.getTablaEmplados().setModel(modelo);
        } 
        else if(e.getSource().equals(frmE.getBtnactualizarem())) {
            try {
                EmpleadoDAO empleadoDAO = new EmpleadoDAO();
                enviarDatosDAO(empleadoDAO, frmE.getTablaEmplados());
                if(empleadoDAO.getObjE() != null) {
                    String resultado = empleadoDAO.actualizar();
                    JOptionPane.showMessageDialog(frmE, resultado);
                    if(resultado.contains("exitosa")) {
                        // Actualizar la tabla solo si la actualización fue exitosa
                        frmE.getTablaEmplados().setModel(empleadoDAO.consultar());
                    }
                }
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(frmE, "Error al actualizar: " + ex.getMessage());
            }
        }
        else if(e.getSource().equals(frmE.getBtndespedido())) {
            int confirmar = JOptionPane.showConfirmDialog(frmE, 
                "¿Está seguro de eliminar este empleado?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);
                
            if(confirmar == JOptionPane.YES_OPTION) {
                try {
                    EmpleadoDAO empleadoDAO = new EmpleadoDAO();
                    enviarDatosDAO(empleadoDAO, frmE.getTablaEmplados());
                    if(empleadoDAO.getObjE() != null) {
                        String resultado = empleadoDAO.eliminar(empleadoDAO.getObjE().getID());
                        JOptionPane.showMessageDialog(frmE, resultado);
                        if(resultado.contains("exitosa")) {
                            // Actualizar la tabla solo si la eliminación fue exitosa
                            frmE.getTablaEmplados().setModel(empleadoDAO.consultar());
                        }
                    }
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(frmE, "Error al eliminar: " + ex.getMessage());
                }
            }
        }
    }
}