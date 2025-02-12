/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.ArrayList;

/**
 *
 * @author Camilo 
 */
public class LisPro {
    private ArrayList<Producto> ListaPro; 

    public LisPro(ArrayList<Producto> ListaPro) {
        this.ListaPro = ListaPro;
    }

      public LisPro() {
        this.ListaPro = new ArrayList<>(); 
    }
  

    
    public ArrayList<Producto> getListaPro() {
        return ListaPro;
    }

    public void setListaPro(ArrayList<Producto> ListaPro) {
        this.ListaPro = ListaPro;
    }
}

