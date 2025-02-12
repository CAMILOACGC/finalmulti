/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controles;

import Controles.ControladorEmpleados;
import Vista.JIMempleados;
import Vista.MDIPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import modelos.*;

public class ControladorPrincipal implements ActionListener {

    private MDIPrincipal principal;
    private PlatoP plato;
    private Cliente cliente;

    public ControladorPrincipal() {
        this.principal = new MDIPrincipal();
        this.plato = new PlatoP();
        this.cliente = new Cliente();

        // Registra los botones en el menú
        this.principal.getBtnventas().addActionListener(this);
        this.principal.getMnmRegistrarplato().addActionListener(this);
        this.principal.getMnmRepoteempleados().addActionListener(this);
        this.principal.getMnmiteadministrador().addActionListener(this);
        this.principal.getMnmitecajero().addActionListener(this);
        this.principal.getMnmitemesero().addActionListener(this);
        this.principal.getMnmmostrarfactura().addActionListener(this);
    }

    public void iniciar() {
        principal.setTitle("Productos MDI");
        principal.setLocationRelativeTo(null);
        principal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        principal.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Botón presionado en ControladorPrincipal"); // Depuración

        if (e.getSource().equals(principal.getMnmRepoteempleados())) {
            System.out.println("Abriendo ventana de empleados..."); // Depuración

            JIMempleados empleados = new JIMempleados();
            principal.getPanelventanas().add(empleados);
            empleados.setVisible(true); // Asegura que la ventana se muestre

            ControladorEmpleados controlempl = new ControladorEmpleados(empleados);
            controlempl.iniciar();
        }
    }
}
