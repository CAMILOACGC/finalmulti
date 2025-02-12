package Controles;

import Vista.JIRegisplato;
import modelos.PlatoP;
import modelos.platoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorRegisPlato implements ActionListener {
    private JIRegisplato vista;
    private PlatoP plato;
    private platoDAO dao;
    private DefaultTableModel modelo;

    public ControladorRegisPlato(JIRegisplato platos ) {
        this.vista = platos;
        this.plato = new PlatoP();
        this.dao = new platoDAO();
        this.modelo = (DefaultTableModel) vista.getTablePlatos().getModel();
    }

    public void iniciar() {
        vista.setTitle("Registro de Platos");
        vista.setLocation(10, 30);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        vista.getBtnGuardarPlato().addActionListener(this);
        vista.getBtnEditarPlato().addActionListener(this);
        vista.getBtnEliminarPlato().addActionListener(this);
        
        vista.getTablePlatos().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.getTablePlatos().getSelectedRow();
                System.out.println(fila);
                if (fila >= 0) {
                    mostrarDatosEnFormulario(fila);
                }
            }
        });
        
        cargarTabla();
        vista.setVisible(true);
    }

    private void cargarTabla() {
        modelo.setRowCount(0);
        for (PlatoP p : dao.listarTodo()) {
            Object[] fila = {
                p.getID(), p.getNOM(), p.getPREC(), p.getPRO(), p.getCAN(), p.getACOM(), p.getGASEOSA()
            };
            modelo.addRow(fila);
        }
    }

    private void mostrarDatosEnFormulario(int fila) {
        vista.getTxtID().setText(modelo.getValueAt(fila, 0).toString());
        vista.getTxtNOMBRE().setText(modelo.getValueAt(fila, 1).toString());
        vista.getTxtPRECIO().setText(modelo.getValueAt(fila, 2).toString());
        vista.getTxtProteina().setText(modelo.getValueAt(fila, 3).toString());
        vista.getTxtCantidaddepersonas().setText(modelo.getValueAt(fila, 4).toString());
        vista.getTxtAcompañamiento1().setText(modelo.getValueAt(fila, 5).toString());
        vista.getjSbebida().setValue(modelo.getValueAt(fila, 6));
    }

    private void limpiarCampos() {
        vista.getTxtID().setText("");
        vista.getTxtNOMBRE().setText("");
        vista.getTxtPRECIO().setText("");
        vista.getTxtProteina().setText("");
        vista.getTxtCantidaddepersonas().setText("");
        vista.getTxtAcompañamiento1().setText("");
    }

    private void cargarDatosPlato() {
        plato.setID(vista.getTxtID().getText());
        plato.setNOM(vista.getTxtNOMBRE().getText());
        plato.setPREC(Double.parseDouble(vista.getTxtPRECIO().getText()));
        plato.setPRO(vista.getTxtProteina().getText());
        plato.setCAN(vista.getTxtCantidaddepersonas().getText());
        plato.setACOM(vista.getTxtAcompañamiento1().getText());
        plato.setGASEOSA(vista.getjSbebida().getValue().toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == vista.getBtnGuardarPlato()) {
                cargarDatosPlato();
                dao.setObjPlato(plato);
                JOptionPane.showMessageDialog(vista, dao.insertar2());
                cargarTabla();
                limpiarCampos();
            } 
            else if (e.getSource() == vista.getBtnEditarPlato()) {
                cargarDatosPlato();
                dao.setObjPlato(plato);
                JOptionPane.showMessageDialog(vista, dao.actualizar());
                cargarTabla();
                limpiarCampos();
            } 
            else if (e.getSource() == vista.getBtnEliminarPlato()) {
                int fila = vista.getTablePlatos().getSelectedRow();
                if (fila >= 0) {
                    String id = modelo.getValueAt(fila, 0).toString();
                    int confirmar = JOptionPane.showConfirmDialog(vista, "¿Está seguro de eliminar este plato?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirmar == JOptionPane.YES_OPTION) {
                        plato.setID(id);
                        dao.setObjPlato(plato);
                        JOptionPane.showMessageDialog(vista, dao.eliminar());
                        cargarTabla();
                        limpiarCampos();
                    }
                } else {
                    JOptionPane.showMessageDialog(vista, "Seleccione un plato para eliminar");
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Error en datos numéricos");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
        }
    }
}

    

