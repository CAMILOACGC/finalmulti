/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;

/**
 *
 * @author Camilo
 */
public class Reporte {
    private ArrayList<Factura> Lista;
    
    public double ReporteTotalD(){
        double total=0.0;
        
       
        for (Factura factura : Lista) {
            for (Producto producto : factura.getListaPro().getListaPro()) {
                total=total+producto.CalcularValor();
               
                } 
             
             
            
        }
        return total;
    }
     public ArrayList<String> ReportePro() {
        if (Lista == null || Lista.isEmpty()) {
            return new ArrayList<>();
        }

        TreeMap<String, Integer> diccionario = new TreeMap<>();
        ArrayList<String> Venta = new ArrayList<>();

        for (Factura factura : Lista) {
            for (Producto producto : factura.getListaPro().getListaPro()) {
                diccionario.put(producto.NOM, diccionario.getOrDefault(producto.NOM, 0) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : diccionario.entrySet()) {
            Venta.add(entry.getKey() + ": " + entry.getValue());
        }

        return Venta;
    }
}