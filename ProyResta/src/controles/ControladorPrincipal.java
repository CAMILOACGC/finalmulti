/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controles;

import Controles.ControladorEmpleados;
import Controles.ControladorRegisPlato;
import Vista.JIMempleados;
import Vista.JIMeseros;
import Vista.JIRegisplato;
import Vista.MDIPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import modelos.*;

public class ControladorPrincipal implements ActionListener {

    private MDIPrincipal principal;
    private Reporte objp;
    private Factura objf;


    public ControladorPrincipal() {
        this.principal = new MDIPrincipal();
        this.objp=objp;
        this.objf=objf;

        // Registra los botones en el menú
        this.principal.getBtnventas().addActionListener(this);
        this.principal.getMnmRegistrarplato().addActionListener(this);
        this.principal.getMnmRepoteempleados().addActionListener(this);
        this.principal.getMnmiteadministrador().addActionListener(this);
        this.principal.getMnmitecajero().addActionListener(this);
        this.principal.getMnmmostrarfactura().addActionListener(this);
        this.principal.getMnmhacerpedido().addActionListener(this);
        this.principal.getCobrar().addActionListener(this);
    }
 
    public void iniciar() {
        principal.setTitle("Productos MDI");
        principal.setLocationRelativeTo(null);
        principal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        principal.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(principal.getCobrar()))
        {
            ArchPdf pdf=new ArchPdf();
            pdf.crear_PDF(objp);
        }
         if(e.getSource().equals(principal.getMnmmostrarfactura()))
        {
            ArchPdffactura pdf=new ArchPdffactura();
            pdf.crear_PDF(objf);
        }
        System.out.println("Botón presionado en ControladorPrincipal"); // Depuración

        if (e.getSource().equals(principal.getMnmRepoteempleados())) {
            System.out.println("Abriendo ventana de empleados..."); // Depuración

            JIMempleados empleados = new JIMempleados();
            principal.getPanelventanas().add(empleados);
            empleados.setVisible(true); // Asegura que la ventana se muestre

            ControladorEmpleados controlempl = new ControladorEmpleados(empleados);
            controlempl.iniciar();
        }

        if (e.getSource().equals(principal.getMnmRegistrarplato())) {
            System.out.println("Abriendo ventana de plato..."); // Depuración

            JIRegisplato platos = new JIRegisplato();
            principal.getPanelventanas().add(platos);
            platos.setVisible(true); // Asegura que la ventana se muestre
       
            ControladorRegisPlato controlplato = new ControladorRegisPlato(platos);
            controlplato.iniciar();
            
        }

         if (e.getSource().equals(principal.getMnmhacerpedido()) || e.getSource().equals(principal.getBtnventas())) {
            System.out.println("Abriendo ventana de meseros..."); // Depuración

            JIMeseros meseros = new JIMeseros();
            principal.getPanelventanas().add(meseros);
            meseros.setVisible(true);
           ControladorMeseros controlmese = new ControladorMeseros();// Asegura que la ventana se muestre
           controlmese.iniciar();
        }
         
    }
}

