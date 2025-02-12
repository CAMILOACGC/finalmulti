package Controles;

import Vista.JIMempleados;

import modelos.Empleado;
import modelos.EmpleadoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorEmpleados implements ActionListener {
    private JIMempleados frmE;
    private EmpleadoDAO empleadoDAO;
    private DefaultTableModel modelo;

    public ControladorEmpleados(JIMempleados frmE) {
        this.frmE = frmE;
        this.empleadoDAO = new EmpleadoDAO();
        this.modelo = (DefaultTableModel) frmE.getTablaEmplados().getModel();
    }

    public void iniciar() {
        frmE.setTitle("Gestión de Empleados");
        frmE.setLocation(10, 30);
        frmE.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmE.getBtnGuardarEmpleado1().addActionListener(this);
        frmE.getBtndespedido().addActionListener(this);
        frmE.getBtnactualizarem().addActionListener(this);

        frmE.getTablaEmplados().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = frmE.getTablaEmplados().getSelectedRow();
                if (fila >= 0) {
                    mostrarDatosEnFormulario(fila);
                }
            }
        });

        frmE.setVisible(true);
        cargarTabla();
    }

    private void cargarTabla() {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        for (Empleado emp : empleadoDAO.listarTodos()) {
            Object[] fila = {
                emp.getID(),
                emp.getNOM(),
                emp.getCOD(),
                emp.getTEL(),
                emp.getDIC(),
                emp.getSAL(),
                emp.getHOR(),
                emp.calcularSueldoHo(),
            };
            modelo.addRow(fila);
        }
    }

    private void mostrarDatosEnFormulario(int fila) {
        frmE.getTxtcedulaemple1().setText(modelo.getValueAt(fila, 0).toString());
        frmE.getTxtNombrepersonaemple().setText(modelo.getValueAt(fila, 1).toString());
        frmE.getTxtCodempleados2().setText(modelo.getValueAt(fila, 2).toString());
        frmE.getTxttelemple1().setText(modelo.getValueAt(fila, 3).toString());
        frmE.getTxtDirecemple1().setText(modelo.getValueAt(fila, 4).toString());
        frmE.getTxtSalario2().setText(modelo.getValueAt(fila, 5).toString());
        frmE.getTxtHoras().setText(modelo.getValueAt(fila, 6).toString());
    }

    private void limpiarFormulario() {
        frmE.getTxtcedulaemple1().setText("");
        frmE.getTxtNombrepersonaemple().setText("");
        frmE.getTxtCodempleados2().setText("");
        frmE.getTxttelemple1().setText("");
        frmE.getTxtDirecemple1().setText("");
        frmE.getTxtSalario2().setText("");
        frmE.getTxtHoras().setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmE.getBtnGuardarEmpleado1()) {
            guardarEmpleado();
        } else if (e.getSource() == frmE.getBtndespedido()) {
            eliminarEmpleado();
        } else if (e.getSource() == frmE.getBtnactualizarem()) {
            actualizarEmpleado();
        }
    }

    private void guardarEmpleado() {
        try {
            Empleado emp = new Empleado();
            emp.setID(frmE.getTxtcedulaemple1().getText());
            emp.setNOM(frmE.getTxtNombrepersonaemple().getText());
            emp.setCOD(frmE.getTxtCodempleados2().getText());            
            emp.setTEL(frmE.getTxttelemple1().getText());
            emp.setDIC(frmE.getTxtDirecemple1().getText());
            emp.setSAL(Double.parseDouble(frmE.getTxtSalario2().getText()));
            emp.setHOR(Integer.parseInt(frmE.getTxtHoras().getText()));

            empleadoDAO.setObjE(emp); // Asignamos el objeto antes de insertar
            String mensaje = empleadoDAO.insertar();
            JOptionPane.showMessageDialog(frmE, mensaje);
            
            cargarTabla();
            limpiarFormulario();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frmE, "Error en los datos numéricos. Verifique salario y horas trabajadas.");
        }
    }

    private void actualizarEmpleado() {
        try {
            Empleado emp = new Empleado();
            emp.setID(frmE.getTxtcedulaemple1().getText());
            emp.setNOM(frmE.getTxtNombrepersonaemple().getText());
            emp.setCOD(frmE.getTxtCodempleados2().getText());            
            emp.setTEL(frmE.getTxttelemple1().getText());
            emp.setDIC(frmE.getTxtDirecemple1().getText());
            emp.setSAL(Double.parseDouble(frmE.getTxtSalario2().getText()));
            emp.setHOR(Integer.parseInt(frmE.getTxtHoras().getText()));

            empleadoDAO.setObjE(emp);
            String mensaje = empleadoDAO.actualizar();
            JOptionPane.showMessageDialog(frmE, mensaje);
            
            cargarTabla();
            limpiarFormulario();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frmE, "Error en los datos numéricos. Verifique salario y horas trabajadas.");
        }
    }

    private void eliminarEmpleado() {
        int fila = frmE.getTablaEmplados().getSelectedRow();
        if (fila >= 0) {
            String cedula = modelo.getValueAt(fila, 0).toString();
            int confirmar = JOptionPane.showConfirmDialog(frmE, 
                "¿Está seguro de eliminar el empleado?", 
                "Confirmar eliminación", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirmar == JOptionPane.YES_OPTION) {
                String mensaje = empleadoDAO.eliminar(cedula);
                JOptionPane.showMessageDialog(frmE, mensaje);
                cargarTabla();
                limpiarFormulario();
            }
        } else {
            JOptionPane.showMessageDialog(frmE, "Seleccione un empleado para eliminar");
        }
    }
}
