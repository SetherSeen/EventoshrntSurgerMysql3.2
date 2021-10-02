package eventoshrntsurgermysql;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoConsultaContenidoReporte {
     static char[] caracter = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
        'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ',
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B',
        'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O',
        'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '.', ',', ':',
        ' ', 'á', 'é', 'í', 'ó', 'ú', '(', ')', 'Ó', 'Á', 'É', 'Í', 'Ú', '\'', '/', '*', '#', ';',};
        public List LecturaDocHrnt(String Id_cuenta, int Mes, String ruta) {
        FechaMysql f = new FechaMysql();
        List lista = new ArrayList();
        String cadenita = f.fechaPasadaNombreMes(Mes);
        programacmd(Id_cuenta,cadenita);
        try {
            String linea = "";
            int i = 0;
            FileInputStream arch = new FileInputStream(ruta+"\\" + Id_cuenta + ".doc");
            DataInputStream dato = new DataInputStream(arch);
            BufferedReader bf = new BufferedReader(new InputStreamReader(dato));
            try {
                while ((linea = bf.readLine()) != null) {
                    linea += "' ";
                    System.out.println(linea);
                    if (i > 18) {

                        String la = linea.replaceAll("\'e1", "á");
                        String lA = la.replaceAll("\'c1", "Á");
                        String lE = lA.replaceAll("\'c9", "É");
                        String le = lE.replaceAll("\'e9", "é");
                        String lI = le.replaceAll("\'cd", "Í");
                        String li = lI.replaceAll("\'ed", "í");
                        String lO = li.replaceAll("\'d3", "Ó");
                        String lo = lO.replaceAll("\'f3", "ó");
                        String lo1 = lo.replaceAll("\'f3n.", "ó");
                        String lU = lo1.replaceAll("\'da", "Ú");
                        String lu = lU.replaceAll("\'fa", "ú");
                        String ln = lu.replaceAll("\'f1", "ñ");
                        String nueva = "";
                        for (int j = 0; j < ln.length(); j++) {
                            for (int kg = 0; kg < caracter.length; kg++) {
                                if (lu.charAt(j) == caracter[kg]) {
                                    nueva += ln.charAt(j);
                                }
                            }
                        }
                        String lN = nueva.replaceAll("d1", "Ñ");
                        String lT = lN.replaceAll("tabtab", "     ");
                        String lp = lT.replaceAll("par'", "");
                        String lc = lp.replaceAll("f0fs22", "");
                        String lps = lc.replaceAll("par S", "S");
                        String lpd = lps.replaceAll("par D", "D");
                        String lpl = lpd.replaceAll("par L", "L");
                        String lpm = lpl.replaceAll("par M", "M");
                        String lpj = lpm.replaceAll("par J", "J");
                        String lpv = lpj.replaceAll("par V", "V");
                        String lp0 = lpv.replaceAll("par  0", " 0");
                        String lp1 = lp0.replaceAll("par  1", " 1");
                        String lp2 = lp1.replaceAll("par  2", " 2");
                        String lpp = lp2.replaceAll("par ' ", "");
                        String lppp = lpp.replaceAll("' ", "");
                        String lppa = lppp.replaceAll("pardsa200sl276slmult1 ", "");
                        String lppb = lppa.replaceAll("pardsl276slmult1 ", "");
                        String lppb2 = lppb.replaceAll(".tab", ".");
                        String lppb1 = lppb2.replaceAll("Re.lecimiento", "Restablecimiento");
                        String lppb123 = lppb1.replaceAll(".pr", " ");
                        String lppb3 = lppb123.replaceAll("par * ", " ");
                        String lppb124 = lppb3.replaceAll("Aperturapor", "Apertura    por");
                        String lppb125 = lppb124.replaceAll("Cierrepor", "Cierre  por");
                        String lppb126 = lppb125.replaceAll("ocedió", "porcedió");
                        String lppb127 = lppb126.replaceAll("e.lecimiento", "establecimiento");
                        String lppb128 = lppb127.replaceAll("par'*'*'*'*'*'", "");
                        String lineaotranostra="";
                        boolean okinawa=false;
                        if(i==19||i==20){
                            if(lppb128.length()<=6){
                            lppb128="";
                            }else{
                        for(int hua=0;hua<lppb128.length();hua++){
                          if(lppb128.charAt(hua)=='*'&&
                                  lppb128.charAt(hua+1)=='*'&&
                                  lppb128.charAt(hua+2)=='*'&&
                                  lppb128.charAt(hua+3)=='*'&&
                                  lppb128.charAt(hua+4)=='*'){
                              hua+=5;
                              okinawa=true;
                          }
                         if(okinawa==true){
                             lineaotranostra+=lppb128.charAt(hua);
                         }
                        }
                        lppb128=lineaotranostra;
                        }
                            
                        }
                        
                        String cadenanegada = "";
                        for (int hi = 0; hi < lppb128.length(); hi++) {
                            if (hi > 20) {
                                cadenanegada += lppb128.charAt(hi);
                            }
                        }
                        String si[] = fraseCompleta(lppb128);
                        for (String si1 : si) {
                            BeanEventoReporte be = new BeanEventoReporte();
                            if (!"".equals(si1)) {
                                be.setEvento(si1);
                                if (!cadenanegada.equals("Restablecimiento de Sistema Celular.")) {
                                    if (!cadenanegada.equals("Código no Definido para HRNT : ")) {
                                    if (!cadenanegada.equals("Cdigo no Definido para HRNT :")) {
                                    if (!cadenanegada.equals("Cdigo no Definido para HRNT : ")) {
                                        if (!cadenanegada.equals("Prueba del Sistema Auxiliar.")) {
                                            if (!cadenanegada.equals("Prueba del Sistema Celular.")) {
                                                if (!cadenanegada.equals("rtura. (Posible Falla en la Comunicacin.)")) {
                                                if (!cadenanegada.equals("rtura. (Posible Falla en la Comunicación.)")) {
                                                    if (!cadenanegada.equals("erre. (Posible Falla en la Comunicación.)")) {
                                                    if (!cadenanegada.equals("rre. (Posible Falla en la Comunicacin.)")) {
                                                    if (!cadenanegada.equals("Código Inexistente")) {
                                                        System.out.println(be.getEvento());
                                                        lista.add(be);
                                                    }
                                                    }
                                                    }
                                                    }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    }
                    i++;
                }
            } catch (IOException ex) {
                Logger.getLogger(DaoConsultaContenidoReporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DaoConsultaContenidoReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
        public void programacmd(String Id_cuenta, String cad) {

        try {
            Process p = Runtime.getRuntime().exec("cmd /C COPY \\\\CENTRAL-HRNTWT\\hrnt\\REP\\" + cad+ "\\" + Id_cuenta + ".doc \"C:\\Users\\usuario\\Desktop\\Bases\\Reportes");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(DaoConsultaContenidoReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
           public String[] fraseCompleta(String linea) {
        String[] frase = new String[50];
        int contador = 0;
        int cuna = 85;
        for (int j = 0; j < frase.length; j++) {
            frase[j] = "";
        }
        for (int i = 0; i < linea.length(); i++) {
            if (i == cuna) {
                contador++;
                cuna += 85;
            }
            frase[contador] += linea.charAt(i);
        }
        return frase;
    }
}
