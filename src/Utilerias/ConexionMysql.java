package Utilerias;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexionMysql {    
   
    private static ConexionMysql instance = new ConexionMysql();
    public static final String URL = "jdbc:mysql://localhost:3306/gcs";
    public static final String USER = "root";
    public static final String PASSWORD = "sekia6";
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public ConexionMysql() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//              System.out.println("Conexion exitosa :" +URL+", "+USER+", "+PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Coneion Mysql "+e);
              Logger.getLogger(ConexionMysql.class.getName()).log(Level.SEVERE, null, e);
        }
        return connection;
    }
    public static Connection ObtenerConexion() {
        return instance.createConnection();
    }
}
