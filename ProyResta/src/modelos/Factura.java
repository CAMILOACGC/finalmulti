/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Camilo
 */
public class Factura {
private  String Nro;
private Persona Cliente;
private LisPro ListaPro;
private Fecha Fecha;

    public Factura(String Nro, Persona Cliente, LisPro ListaPro, Fecha Fecha) {
        this.Nro = Nro;
        this.Cliente = Cliente;
        this.ListaPro = ListaPro;
        this.Fecha = Fecha;
    }
public Factura() {
        this.Nro = "";
        this.Cliente = new Cliente()  ;
        this.ListaPro = new LisPro();
        this.Fecha = new Fecha();
    }

    public String getNro() {
        return Nro;
    }

    public void setNro(String Nro) {
        this.Nro = Nro;
    }

    public Persona getCliente() {
        return Cliente;
    }

    public void setCliente(Persona Cliente) {
        this.Cliente = Cliente;
    }

    public LisPro getListaPro() {
        return ListaPro;
    }

    public void setListaPro(LisPro ListaPro) {
        this.ListaPro = ListaPro;
    }

    public Fecha getFecha() {
        return Fecha;
    }

    public void setFecha(Fecha Fecha) {
        this.Fecha = Fecha;
    }

    @Override
    public String toString() {
        return "Factura" 
                + "Nro=" + Nro 
                + " Cliente=" + Cliente 
                + ", ListaPro=" + ListaPro 
                + ", Fecha=" + Fecha ;
    }
   
   
   }




  


 
 

    
    


