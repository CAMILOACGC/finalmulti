/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Camilo
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Conexion;

public class platoDAO {
    private PlatoP objPlato;
    private String mensaje;

    public platoDAO(PlatoP objPlato) {
        this.objPlato = objPlato;
    }

    public platoDAO() {
        this.objPlato = new PlatoP();
    }

    public DefaultTableModel consultar() {
        DefaultTableModel plantilla = new DefaultTableModel();
        Conexion con = new Conexion();
        try {
            con.conectar();
            JOptionPane.showMessageDialog(null, con.getConexion());
            Statement consulta = con.getConexion().createStatement();
            try (ResultSet datos = consulta.executeQuery("SELECT * FROM platos")) {
                ResultSetMetaData campos = datos.getMetaData();
                
                for (int i = 1; i <= campos.getColumnCount(); i++) {
                    plantilla.addColumn(campos.getColumnName(i));
                }

                while (datos.next()) {
                    Object fila[] = new Object[campos.getColumnCount()];
                    for (int i = 0; i < campos.getColumnCount(); i++) {
                        fila[i] = datos.getObject(i + 1);
                    }
                    plantilla.addRow(fila);
                }
            }
            con.getConexion().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        return plantilla;
    }

    public String insertar2() {
        try {
            Conexion conexion = new Conexion();
            PreparedStatement consulta;
            conexion.conectar();
            String comando = "INSERT INTO platos VALUES (?, ?, ?, ?, ?, ?, ?)";
            consulta = conexion.getConexion().prepareStatement(comando);
            consulta.setString(1, objPlato.getID());
            consulta.setString(2, objPlato.getNOM());
            consulta.setDouble(3, objPlato.getPREC());
            consulta.setString(4, objPlato.getPRO());
            consulta.setString(5, objPlato.getCAN());
            consulta.setString(6, objPlato.getACOM());
            consulta.setString(7, objPlato.getGASEOSA());
            consulta.execute();
            mensaje = "Registro exitoso...";
            consulta.close();
            conexion.getConexion().close();
        } catch (SQLException ex) {
            mensaje = "Error al intentar insertar...\n" + ex;
        }
        return mensaje;
    }

    public String actualizar() {
        try {
            Conexion conexion = new Conexion();
            PreparedStatement consulta;
            conexion.conectar();
            String instruccion = "UPDATE platos SET nombre = ?, precio = ?, proteina = ?, cantidad_personas = ?, acompanamiento = ?, bebida = ? WHERE id = ?";
            consulta = conexion.getConexion().prepareStatement(instruccion);
            consulta.setString(1, objPlato.getNOM());
            consulta.setDouble(2, objPlato.getPREC());
            consulta.setString(3, objPlato.getPRO());
            consulta.setString(4, objPlato.getCAN());
            consulta.setString(5, objPlato.getACOM());
            consulta.setString(6, objPlato.getGASEOSA());
            consulta.setString(7, objPlato.getID());
            consulta.execute();
            mensaje = "Actualización exitosa...";
        } catch (SQLException ex) {
            mensaje = "Error al intentar actualizar...\n" + ex;
        }
        return mensaje;
    }

    public String eliminar() {
        try {
            Conexion conexion = new Conexion();
            PreparedStatement consulta;
            conexion.conectar();
            String comando = "DELETE FROM platos WHERE id = ?";
            consulta = conexion.getConexion().prepareStatement(comando);
            consulta.setString(1, objPlato.getID());
            consulta.execute();
            mensaje = "Se ha eliminado el registro...";
            consulta.close();
            conexion.getConexion().close();
        } catch (SQLException ex) {
            mensaje = "Error al intentar eliminar...\n" + ex;
        }
        return mensaje;
    }

    
    
    public List<PlatoP> listarTodo() {
    List<PlatoP> lista = new ArrayList<>();
    Conexion conexion = new Conexion();

    try {
        conexion.conectar();
        Statement consulta = conexion.getConexion().createStatement();
        ResultSet datos = consulta.executeQuery("SELECT * FROM platos");

        while (datos.next()) {
            PlatoP p = new PlatoP();
            p.setID(datos.getString("id"));
            p.setNOM(datos.getString("nombre"));
            p.setPREC(datos.getDouble("precio"));
            p.setPRO(datos.getString("proteina"));
            p.setCAN(datos.getString("cantidad_personas"));
            p.setACOM(datos.getString("acompanamiento"));
            p.setGASEOSA(datos.getString("bebida"));
            lista.add(p);
        }

        conexion.getConexion().close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al listar los platos: " + ex.getMessage());
    }

    return lista;
}




    public PlatoP getObjPlato() {
        return objPlato;
    }

    public void setObjPlato(PlatoP objPlato) {
        this.objPlato = objPlato;
    }

    @Override
    public String toString() {
        return objPlato.toString();
    }
}
