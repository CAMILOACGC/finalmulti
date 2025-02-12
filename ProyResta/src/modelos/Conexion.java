package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    private Connection conexion;
    private String bd, usuario, clave, mensaje;

    public Conexion() {
        this.conexion = null;
        this.bd = "restaurante";  // Asegúrate de que esta sea tu base de datos real
        this.usuario = "root";
        this.clave = "123456";
        this.mensaje = "";
    }

    public void conectar() {
        try {
            // Cargar el driver correcto para MySQL moderno
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // URL de conexión a MySQL
            String ruta = "jdbc:mysql://localhost:3306/" + bd + "?serverTimezone=UTC";
            System.out.println("Intentando conectar a: " + ruta);
            
            // Establecer conexión
            conexion = DriverManager.getConnection(ruta, usuario, clave);
            
            if (conexion != null) {
                mensaje = "Conexión establecida con éxito.";
                System.out.println(mensaje);
            } else {
                mensaje = "No se pudo establecer conexión.";
                System.out.println(mensaje);
            }
        } catch (ClassNotFoundException ex) {
            mensaje = "Error: No se encontró el driver de MySQL.";
            System.out.println(mensaje);
            ex.printStackTrace();
        } catch (SQLException ex) {
            mensaje = "Error de SQL: " + ex.getMessage();
            System.out.println(mensaje);
            ex.printStackTrace();
        }
    }

    public Connection getConexion() {
        if (conexion == null) {
            System.out.println("Advertencia: La conexión es nula.");
        }
        return conexion;
    }

    public void cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexión: " + ex.getMessage());
        }
    }
}

