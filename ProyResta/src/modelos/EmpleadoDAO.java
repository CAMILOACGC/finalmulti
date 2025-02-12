package modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Empleado;

public class EmpleadoDAO {
    private Empleado objE;
    private String mensaje;

    public EmpleadoDAO(Empleado objE) {
        this.objE = objE;
    }

    public EmpleadoDAO() {
        this.objE = new Empleado();
    }

    public DefaultTableModel consultar() {
        DefaultTableModel plantilla = new DefaultTableModel();
        Conexion con = new Conexion();
        try {
            con.conectar();
            Statement consulta = con.getConexion().createStatement();
            ResultSet datos = consulta.executeQuery("SELECT * FROM empleados");
            ResultSetMetaData campos = datos.getMetaData();
            
            // Agregar columnas a la plantilla
            for (int i = 1; i <= campos.getColumnCount(); i++) {
                plantilla.addColumn(campos.getColumnName(i));
            }
            
            // Agregar filas a la plantilla
            while (datos.next()) {
                Object fila[] = new Object[campos.getColumnCount()];
                for (int i = 0; i < campos.getColumnCount(); i++) {
                    fila[i] = datos.getObject(i + 1);
                }
                plantilla.addRow(fila);
            }
            
            datos.close();
            con.getConexion().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        return plantilla;
    }

    public List<Empleado> listarTodos() {
        List<Empleado> empleados = new ArrayList<>();
        Conexion con = new Conexion();
        try {
            con.conectar();
            Statement consulta = con.getConexion().createStatement();
            try (ResultSet datos = consulta.executeQuery("SELECT * FROM empleados")) {
                while (datos.next()) {
                    Empleado emp = new Empleado();
                    emp.setID(datos.getString("cedula"));
                    emp.setNOM(datos.getString("nombre"));
                    emp.setCOD(datos.getString("codigo"));
                    emp.setTEL(datos.getString("telefono"));
                    emp.setDIC(datos.getString("direccion"));
                    emp.setSAL(datos.getDouble("salario"));
                    emp.setHOR(datos.getInt("horas_trabajadas"));
                    empleados.add(emp);
                }
            }
            con.getConexion().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        return empleados;
    }

    public String insertar() {
        try {
            Conexion conexion = new Conexion();
            PreparedStatement consulta = null;
            conexion.conectar();
            
            String comando = "INSERT INTO empleados (cedula, nombre, codigo, telefono, direccion, salario, horas_trabajadas) VALUES (?, ?, ?, ?, ?, ?, ?)";
            consulta = conexion.getConexion().prepareStatement(comando);
            consulta.setString(1, objE.getID());
            consulta.setString(2, objE.getNOM());
            consulta.setString(3, objE.getCOD());
            consulta.setString(4, objE.getTEL());
            consulta.setString(5, objE.getDIC());
            consulta.setDouble(6, objE.getSAL());
            consulta.setInt(7, objE.getHOR());
            
            consulta.execute();
            mensaje = "Registro de empleado exitoso...";
            consulta.close();
            conexion.getConexion().close();
        } catch (SQLException ex) {
            mensaje = "Error al intentar insertar empleado...\n" + ex;
        }
        return mensaje;
    }

    public String actualizar() {
        try {
            Conexion conexion = new Conexion();
            PreparedStatement consulta = null;
            conexion.conectar();
            
            String instruccion = "UPDATE empleados SET nombre=?, codigo=?, telefono=?, direccion=?, salario=?, horas_trabajadas=? WHERE cedula=?";
            consulta = conexion.getConexion().prepareStatement(instruccion);
          consulta.setString(1, objE.getID());
            consulta.setString(2, objE.getNOM());
            consulta.setString(3, objE.getCOD());
        
            consulta.setString(4, objE.getTEL());
            consulta.setString(5, objE.getDIC());
            consulta.setDouble(6, objE.getSAL());
            consulta.setInt(7, objE.getHOR());
            
            consulta.execute();
            mensaje = "Actualización de empleado exitosa...";
            consulta.close();
            conexion.getConexion().close();
        } catch (SQLException ex) {
            mensaje = "Error al intentar actualizar empleado...\n" + ex;
        }
        return mensaje;
    }

    public String eliminar(String cedula) {
        try {
            Conexion conexion = new Conexion();
            PreparedStatement consulta = null;
            conexion.conectar();
            
            String comando = "DELETE FROM empleados WHERE cedula=?";
            consulta = conexion.getConexion().prepareStatement(comando);
            consulta.setString(1, cedula);
            
            consulta.execute();
            mensaje = "Se ha eliminado el empleado...";
            consulta.close();
            conexion.getConexion().close();
        } catch (SQLException ex) {
            mensaje = "Error al intentar eliminar empleado...\n" + ex;
        }
        return mensaje;
    }

    public Empleado getObjE() {
        return objE;
    }

    public void setObjE(Empleado objE) {
        this.objE = objE;
    }

    @Override
    public String toString() {
        return objE.toString();
    }
}