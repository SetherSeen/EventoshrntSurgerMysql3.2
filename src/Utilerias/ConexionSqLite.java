package Utilerias;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ConexionSqLite {
    
    private static ConexionSqLite instance = new ConexionSqLite();
    public static final String DRIVER_CLASS = "org.sqlite.JDBC";
    public ConexionSqLite() {
        try {


            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection(String ruta) {

        Connection connection = null;
        try {
            //este nombre va acambiar segun sea la base de datos a leeer guardad la base de datos en la ruta que esta puesta
            connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"\\EventosHRNT.db");
            connection.setAutoCommit(false);
//             System.out.println("Opened database successfully");
        } catch (SQLException e) {
              Logger.getLogger(ConexionMysql.class.getName()).log(Level.SEVERE, null, e);
              JOptionPane.showMessageDialog(null, "ConeionSqlite"+e);
        }
      
        return connection;
    }

    public static Connection ObtenerConexionSqLite(String ruta) {
        return instance.createConnection(ruta);
    }
}
