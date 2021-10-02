package eventoshrntsurgermysql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Hilo2 implements Runnable {
    Thread t;

    public Hilo2() {
    t=new Thread(this);
    }
    public void stard(){
    t.start();
    }
    @Override
    public void run() {
    LeerSqliteMysql leer=new LeerSqliteMysql();
        batcopiarpegarbasededatos();
        leer.SqliteLectura(2);
        JOptionPane.showMessageDialog(null, "Finalizo!!!");
    }
             void batcopiarpegarbasededatos() {
                 Hilo3Ruta ruta=new Hilo3Ruta();
        try {
            Process p = Runtime.getRuntime().exec("cmd /C COPY "+ruta.leerRutaBat()+" C:\\Users\\usuario\\Desktop\\Bases");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(Hilo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
