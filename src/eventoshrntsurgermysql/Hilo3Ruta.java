package eventoshrntsurgermysql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Hilo3Ruta implements Runnable {

    Thread t;
    volatile String ruta;

    public Hilo3Ruta() {
        t = new Thread(this);
    }

    public void stard(String ruta) {
        this.ruta = ruta;
        t.start();
    }

    @Override
    public void run() {
        escrituraTxtRutaMetodo(ruta);
        JOptionPane.showMessageDialog(null, "!!finalizo");
    }

    public void escrituraTxtRutaMetodo(String ruta) {
        try {
            FileWriter fichero = null;
            PrintWriter pw = null;
            fichero = new FileWriter("C:\\Users\\Servicios\\Desktop\\Bases\\rutaBat.txt");
            pw = new PrintWriter(fichero);
            pw.println(ruta);
            pw.close();
            fichero.close();
        } catch (IOException ex) {
            Logger.getLogger(Hilo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String leerRutaBat() {
        String ruta = "";

        File archivo = new File("C:\\Users\\Servicios\\Desktop\\Bases\\rutaBat.txt");
        if (archivo.exists()) {
            try {
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String linea;
                while ((linea = br.readLine()) != null) {
                    ruta = linea;
                }
            } catch (IOException ex) {
                Logger.getLogger(Hilo3Ruta.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "";
        }
        return ruta;
    }
}
