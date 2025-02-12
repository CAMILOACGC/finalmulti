/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.ArrayList;
import java.sql.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Conexion;
/**
 *
 * @author Camilo
 */
public class ClienteDAO {
    public ArrayList<String> consultarNombrePlatos() {
        ArrayList<String> lista = new ArrayList<>();
        Conexion conexion = new Conexion();

        try {
            conexion.conectar();
            Statement consulta = conexion.getConexion().createStatement();
            ResultSet datos = consulta.executeQuery("SELECT nombre FROM platos");

            while (datos.next()) {
                lista.add(datos.getString("nombre"));
            }

            conexion.getConexion().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar los platos: " + ex.getMessage());
        }
        return lista;
    }   
    public double obtenerPrecioPlato(String nombre) {
        Conexion conexion = new Conexion();
        double precio = 0.0; 

        try {
            conexion.conectar();
            String sql = "SELECT precio FROM platos WHERE nombre = ?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(sql);
            consulta.setString(1, nombre);
            ResultSet datos = consulta.executeQuery();

            if (datos.next()) { 
                precio = datos.getDouble("precio");
            }

            datos.close(); 
            consulta.close(); 
            conexion.getConexion().close(); 

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener el precio: " + ex.getMessage());
        }
        return precio; 
    }


    public ClienteDAO() {
        
    }
}
