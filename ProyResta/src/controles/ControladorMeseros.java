/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controles;

import Vista.JIMeseros;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import modelos.PlatoP;

/**
 *
 * @author Estudiante
 */
public class ControladorMeseros implements ActionListener{
    private JIMeseros fmrp;
    private PlatoP  plato;

    public ControladorMeseros (JIMeseros fmrp, PlatoP plato) {
        this.fmrp = fmrp;
        this.plato = plato;
    }
    public ControladorMeseros() {
        this.fmrp = new JIMeseros();
        this.plato = new PlatoP() {
        };
    }
    public void iniciar()
    {
        fmrp.setTitle("Registar productos");
        fmrp.setLocation(10,30);
        fmrp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fmrp.setVisible(true);
        fmrp.getBtnGuardarpedido().addActionListener(this);
        fmrp.getBtnEliminarpedido().addActionListener(this);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(fmrp.getBtnGuardarpedido())){ 
            
    }
    
    }}
