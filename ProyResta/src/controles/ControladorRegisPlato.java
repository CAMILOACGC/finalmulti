package Controles;

import Vista.JIRegisplato;
import modelos.PlatoP;
import modelos.platoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ControladorRegisPlato implements ActionListener {
    private JIRegisplato vista;
    private PlatoP plato;
    private platoDAO dao;

    public ControladorRegisPlato() {
        this.vista = new JIRegisplato();
        this.plato = new PlatoP();
        this.dao = new platoDAO();
    }

    public void iniciar() {
        vista.setTitle("Registro de Platos");
        vista.setLocation(10, 30);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vista.getBtnGuardarPlato().addActionListener(this);
        vista.getBtnEditarPlato().addActionListener(this);
        vista.getBtnEliminarPlato().addActionListener(this);
        cargarTabla();
        vista.setVisible(true);
    }

    private void cargarTabla() {
        vista.getTablePlatos().setModel(dao.listarPlatos());
    }

    private void limpiarCampos() {
        vista.getTxtID().setText("");
        vista.getTxtNOMBRE().setText("");
        vista.getTxtPRECIO().setText("");
        vista.getTxtProteina().setText("");
        vista.getTxtCantidaddepersonas().setText("");
        vista.getTxtAcompañamiento2().setText("");
        vista.getjSbebida().setValue("no");
    }

    private void cargarDatosPlato() {
        plato.setID(vista.getTxtID().getText());
        plato.setNOM(vista.getTxtNOMBRE().getText());
        plato.setPREC(Double.parseDouble(vista.getTxtPRECIO().getText()));
        plato.setPRO(vista.getTxtProteina().getText());
        plato.setCAN(vista.getTxtCantidaddepersonas().getText());
        plato.setACOM(vista.getTxtAcompañamiento2().getText());
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
                plato.setID(vista.getTxtID().getText());
                dao.setObjPlato(plato);
                JOptionPane.showMessageDialog(vista, dao.eliminar());
                cargarTabla();
                limpiarCampos();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Error en datos numéricos");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
        }
    }
}

    

