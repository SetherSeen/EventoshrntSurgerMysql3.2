package eventoshrntsurgermysql;

import Utilerias.ConexionMysql;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hilo4LogSurger implements Runnable {

    Thread t;
    volatile boolean running;
    public Hilo4LogSurger(boolean running) {
        this.running = running;
        t = new Thread(this);
    }

    public void strard() {
        t.start();
    }

    @Override
    public void run() {
        while (running) {
            try {
                batcopiarpegarbasededatos();
                String sDirectorio = "C:\\Users\\Servicios\\Desktop\\Bases\\log";
                File f = new File(sDirectorio);
                File[] ficheros = f.listFiles();
                File[] fich = new File[ficheros.length];
                for (int x = 0; x < ficheros.length; x++) {
                    String position = "";
                    for (int su = 0; su < ficheros[x].getName().length(); su++) {
                        if (su > 7 && su < (ficheros[x].getName().length() - 4)) {
                            position += ficheros[x].getName().charAt(su);
                        }
                    }
                    fich[Integer.parseInt(position)] = ficheros[x];
                }
                surger(fich);
            } catch (IOException ex) {
                Logger.getLogger(Hilo4LogSurger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void stop() {
        running = false;
    }

    void batcopiarpegarbasededatos() {
        try {
            Process p = Runtime.getRuntime().exec("cmd /C COPY \\\\CENTRAL-HRNTWT\\Printer\\*.log C:\\Users\\Servicios\\Desktop\\Bases\\log");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
    
    public void surger(File[] log) throws IOException {
        for(int q=log.length-1;q>log.length-3;q--){
        String u = "";
        try {
    EventoshrntSurgerMysql.eventobatLog.setText("---------------------------"+log[q]+"-----------------------------------------");
            FileInputStream archivo = new FileInputStream(log[q]);
            DataInputStream entrada = new DataInputStream(archivo);
            BufferedReader buff = new BufferedReader(new InputStreamReader(entrada));
            while ((u = buff.readLine()) != null&&running) {
                String cuentas = "", codigo_evento = "",año="",mes="",dia="";
                
                for (int i = 0; i < u.length(); i++) {
                    if(i>2&&i<5)
                        mes+=u.charAt(i);
                    if(i>5&&i<8)
                        dia+=u.charAt(i);
                    if(i>29&&i<34)
                        año+=u.charAt(i);
                    if (i > 58 && i < 63) {
                        cuentas += u.charAt(i);
                    }
                    if (i > 63 && i < 71) {
                        if (u.charAt(i) != 'R') {
                            if (u.charAt(i) == '-' && i > 65) {
                                break;
                            }
                            codigo_evento += u.charAt(i);
                        }
                    }
                }
                EventoshrntSurgerMysql.eventobatLog.setText(cuentas + " ||" + codigo_evento + " ||"+año+"-"+mes+"-"+dia+ " ||" + u);
                insercionCuenta(u, cuentas, codigo_evento,año+"-"+mes+"-"+dia);
                Thread.sleep(89);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Hilo4LogSurger.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (InterruptedException ex) {
                Logger.getLogger(Hilo4LogSurger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void insercionCuenta(String evento, String Id_cuenta, String codigoevento,String fecha) {
        try {
            Connection con = ConexionMysql.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO surger"
                    + "(evento,Id_cuenta,codigoevento,fecha)VALUES(?,?,?,?);");
            ps.setString(1, evento);
            ps.setString(2, Id_cuenta);
            ps.setString(3, codigoevento);
            ps.setString(4, fecha);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException ex) {
//            Logger.getLogger(surger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
