package eventoshrntsurgermysql;

import Utilerias.ConexionMysql;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Hilo6SucInformacion implements Runnable {
volatile boolean running;
    Thread t;

    public Hilo6SucInformacion(boolean running) {
        t = new Thread(this);
        this.running=running;
    }
    public void stop(){
    running=false;
    }
    public void stard() {
        t.start();
    }
    char[] caracter = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
        'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p',
        'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
        'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S',
        'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '.', ',', ':', ' ', 'á', 'é', 'í', 'ó',
        'ù', '(', ')', 'Ó', 'Á', 'É', 'Í', 'Ú', '-', '"', '�', '/', '&', '*', '@',
        '<', '>', '{', '}', '\'', '#', '_', '-', '!', '°', ';'};

    @Override
    public void run() {
        leersuc(lecturaArchivosSuc("\\\\Central-hrntwt\\hrnt\\Bases"));
    }

    public List lecturaArchivosSuc(String ruta) {
        List lista = new ArrayList();
        File f = new File(ruta);
        File[] fichetos = f.listFiles();
        for (int i = 0; i < fichetos.length; i++) {
            String suc = "";
            for (int j = 0; j < fichetos[i].getName().length(); j++) {
                if (j > 3) {
                    suc += fichetos[i].getName().charAt(j);
                }
            }
            if (suc.equals(".Suc")) {
                lista.add(fichetos[i]);
            }
        }
        return lista;
    }

    public void leersuc(List suc) {
        for (int y = 0; y < suc.size(); y++) {
            if(running==true){
              
            try {
                FileInputStream fs = new FileInputStream(suc.get(y).toString());
                DataInputStream entra = new DataInputStream(fs);
                BufferedReader buf = new BufferedReader(new InputStreamReader(entra));
                String strLinea,
                        textocompleto = "",
                        Id_cuenta = "",
                        Nombrecuenta = "",
                        equipo = "",
                        istitucion = "",
                        sucursa = "",
                        grupo = "",
                        contrato = "",
                        domicilio = "",
                        entrecalle = "",
                        yotracalle = "",
                        colonia = "",
                        delegacion = "",
                        CP = "",
                        ciudad = "",
                        estado = "",
                        pais = "",
                        comentario = "",
                        fechaistalado = "",
                        tecnico = "",
                        ftelefonoC = "";
                String telefonoC[] = new String[6];
                telefonoC[0] = "";
                telefonoC[1] = "";
                telefonoC[2] = "";
                telefonoC[3] = "";
                telefonoC[4] = "";
                telefonoC[5] = "";
                String Nombrerestelefono[] = new String[12];
                Nombrerestelefono[0] = "";
                Nombrerestelefono[1] = "";
                Nombrerestelefono[2] = "";
                Nombrerestelefono[3] = "";
                Nombrerestelefono[4] = "";
                Nombrerestelefono[5] = "";
                Nombrerestelefono[6] = "";
                Nombrerestelefono[7] = "";
                Nombrerestelefono[8] = "";
                Nombrerestelefono[9] = "";
                Nombrerestelefono[10] = "";
                Nombrerestelefono[11] = "";
                String otrotelef[] = new String[12];
                otrotelef[0] = "";
                otrotelef[1] = "";
                otrotelef[2] = "";
                otrotelef[3] = "";
                otrotelef[4] = "";
                otrotelef[5] = "";
                otrotelef[6] = "";
                otrotelef[7] = "";
                otrotelef[8] = "";
                otrotelef[9] = "";
                otrotelef[10] = "";
                otrotelef[11] = "";
                String usuario[] = new String[64];
                String zona[] = new String[502];
                int espacio = 0,
                        espacior = 0,
                        saltor = 0,
                        salto = 0,
                        otromas = 0,
                        espacion = 0,
                        salton = 0,
                        espaciou = 0,
                        saltou = 0,
                        espacioz = 0,
                        saltoz = 0,
                        variableu = 3437,
                        variablez = 7923;
                boolean oj = false;
                boolean ojr = false;
                boolean ojn = false;
                boolean oju = false;
                boolean ojz = false;
                boolean diferente = false;
                boolean fineline = false;
                for (int k = 0; k < usuario.length; k++) {
                    usuario[k] = "";
                }
                for (int k = 0; k < zona.length; k++) {
                    zona[k] = "";
                }

                while ((strLinea = buf.readLine()) != null) {
                    textocompleto += strLinea;
                }
                for (int i = 20; i < textocompleto.length(); i++) {

                    if (i < 24) {
                        Id_cuenta += textocompleto.charAt(i);
                    }
                    if (i >= 30 && i <= 116) {
                        for (int hu = 0; hu < caracter.length; hu++) {
                            if (fineline == false && textocompleto.charAt(i) == caracter[hu]) {
                                if ('"' == textocompleto.charAt(i)) {
                                    Nombrecuenta += "'";
                                } else {
                                    Nombrecuenta += textocompleto.charAt(i);
                                }
                                diferente = true;
                                break;
                            }
                            if (textocompleto.charAt(i) != caracter[hu] && diferente == true) {
                                otromas++;
                            }
                        }
                        if (otromas == caracter.length) {
                            fineline = true;
                        } else {
                            otromas = 0;
                        }
                    }
                    if (i > 116 && i < 169) {
                        for (int hu = 0; hu < caracter.length; hu++) {
                            if (textocompleto.charAt(i) == caracter[hu]) {
                                istitucion += textocompleto.charAt(i);
                            }
                        }
                    }
                    if (i > 169 && i < 223) {
                        for (int hu = 0; hu < caracter.length; hu++) {
                            if (textocompleto.charAt(i) == caracter[hu]) {
                                sucursa += textocompleto.charAt(i);
                            }
                        }
                    }
                    if (i > 223 && i < 273) {
                        for (int hu = 0; hu < caracter.length; hu++) {
                            if (textocompleto.charAt(i) == caracter[hu]) {
                                grupo += textocompleto.charAt(i);
                            }
                        }
                    }
                    if (i > 273 && i < 324) {
                        for (int hu = 0; hu < caracter.length; hu++) {
                            if (textocompleto.charAt(i) == caracter[hu]) {
                                contrato += textocompleto.charAt(i);
                            }
                        }
                    }
                    if (i > 332 && i < 362) {
                        for (int hu = 0; hu < caracter.length; hu++) {
                            if (textocompleto.charAt(i) == caracter[hu]) {
                                equipo += textocompleto.charAt(i);
                            }
                        }

                    }

                    if (i > 558 && i < 659) {
                        for (int hu = 0; hu < caracter.length; hu++) {
                            if (textocompleto.charAt(i) == caracter[hu]) {
                                domicilio += textocompleto.charAt(i);
                            }
                        }
                    }
                    if (i > 658 && i < 729) {
                        for (int hu = 0; hu < caracter.length; hu++) {
                            if (textocompleto.charAt(i) == caracter[hu]) {
                                entrecalle += textocompleto.charAt(i);
                            }
                        }
                    }
                    if (i > 728 && i < 799) {
                        for (int hu = 0; hu < caracter.length; hu++) {
                            if (textocompleto.charAt(i) == caracter[hu]) {
                                yotracalle += textocompleto.charAt(i);
                            }
                        }
                    }
                    if (i > 798 && i < 869) {
                        for (int hu = 0; hu < caracter.length; hu++) {
                            if (textocompleto.charAt(i) == caracter[hu]) {
                                colonia += textocompleto.charAt(i);
                            }
                        }
                    }
                    if (i > 868 && i < 919) {
                        for (int hu = 0; hu < caracter.length; hu++) {
                            if (textocompleto.charAt(i) == caracter[hu]) {
                                delegacion += textocompleto.charAt(i);
                            }
                        }
                    }
                    if (i > 918 && i < 926) {
                        for (int hu = 0; hu < caracter.length; hu++) {
                            if (textocompleto.charAt(i) == caracter[hu]) {
                                CP += textocompleto.charAt(i);
                            }
                        }
                    }
                    if (i > 925 && i < 976) {
                        for (int hu = 0; hu < caracter.length; hu++) {
                            if (textocompleto.charAt(i) == caracter[hu]) {
                                ciudad += textocompleto.charAt(i);
                            }
                        }
                    }
                    if (i > 975 && i < 1025) {
                        for (int hu = 0; hu < caracter.length; hu++) {
                            if (textocompleto.charAt(i) == caracter[hu]) {
                                estado += textocompleto.charAt(i);
                            }
                        }
                    }
                    if (i > 1024 && i < 1076) {
                        for (int hu = 0; hu < caracter.length; hu++) {
                            if (textocompleto.charAt(i) == caracter[hu]) {
                                pais += textocompleto.charAt(i);
                            }
                        }
                    }
                    if (i > 1075 && i < 1657) {
                        for (int hu = 0; hu < caracter.length; hu++) {
                            if (textocompleto.charAt(i) == caracter[hu]) {
                                comentario += textocompleto.charAt(i);
                            }
                        }
                    }
                    if (i > 1656 && i < 1758) {
                        for (int hu = 0; hu < caracter.length; hu++) {
                            if (textocompleto.charAt(i) == caracter[hu]) {
                                tecnico += textocompleto.charAt(i);
                            }
                        }
                    }
                    if (i > 1755 && i < 1854) {
                        for (int hu = 0; hu < caracter.length; hu++) {
                            if (textocompleto.charAt(i) == caracter[hu]) {
                                fechaistalado += textocompleto.charAt(i);
                            }
                        }
                    }

                    if (i > 1853 && i < 2099) {
                        for (int j = 0; j < caracter.length; j++) {
                            if (textocompleto.charAt(i) == caracter[j]) {
                                telefonoC[salto] += textocompleto.charAt(i);
                                oj = true;
                                break;
                            }
                            if (textocompleto.charAt(i) != caracter[j] && oj == true) {
                                espacio++;
                            }
                        }
                        if (i == 1885 && telefonoC[0].equals("")
                                || i == 1925 && telefonoC[1].equals("")
                                || i == 1963 && telefonoC[2].equals("")
                                || i == 2003 && telefonoC[3].equals("")
                                || i == 2041 && telefonoC[4].equals("")) {
                            salto++;
                            oj = false;
                            espacio = 0;
                        }
                        if (espacio == caracter.length) {
                            oj = false;
                            espacio = 0;
                            salto++;
                        } else {
                            espacio = 0;
                        }
                    }
                    if (i > 2098 && i < 2762) {/////
                        for (int j = 0; j < caracter.length; j++) {
                            if (textocompleto.charAt(i) == caracter[j]) {
                                Nombrerestelefono[saltor] += textocompleto.charAt(i);
                                ojr = true;
                                break;
                            }
                            if (textocompleto.charAt(i) != caracter[j] && ojr == true) {
                                espacior++;
                            }
                        }
                        if (i == 2136 && Nombrerestelefono[0].equals("")
                                || i == 2174 && Nombrerestelefono[1].equals("")
                                || i == 2246 && Nombrerestelefono[2].equals("")
                                || i == 2284 && Nombrerestelefono[3].equals("")
                                || i == 2356 && Nombrerestelefono[4].equals("")
                                || i == 2394 && Nombrerestelefono[5].equals("")
                                || i == 2466 && Nombrerestelefono[6].equals("")
                                || i == 2504 && Nombrerestelefono[7].equals("")
                                || i == 2576 && Nombrerestelefono[8].equals("")
                                || i == 2614 && Nombrerestelefono[9].equals("")
                                || i == 2686 && Nombrerestelefono[10].equals("")) {
                            saltor++;
                            ojr = false;
                            espacior = 0;
                        }
                        if (espacior == caracter.length) {
                            ojr = false;
                            espacior = 0;
                            saltor++;
                        } else {
                            espacior = 0;
                        }
                    }
                    if (i > 2761 && i < 3421) {
                        for (int j = 0; j < caracter.length; j++) {
                            if (textocompleto.charAt(i) == caracter[j]) {
                                otrotelef[salton] += textocompleto.charAt(i);
                                ojn = true;
                                break;
                            }
                            if (textocompleto.charAt(i) != caracter[j] && ojn == true) {
                                espacion++;
                            }
                        }
                        if (i == 2787 && otrotelef[0].equals("")
                                || i == 2827 && otrotelef[1].equals("")
                                || i == 2896 && otrotelef[2].equals("")
                                || i == 2939 && otrotelef[3].equals("")
                                || i == 3007 && otrotelef[4].equals("")
                                || i == 3051 && otrotelef[5].equals("")
                                || i == 3118 && otrotelef[6].equals("")
                                || i == 3158 && otrotelef[7].equals("")
                                || i == 3227 && otrotelef[8].equals("")
                                || i == 3268 && otrotelef[9].equals("")
                                || i == 3338 && otrotelef[10].equals("")) {
                            salton++;
                            ojn = false;
                            espacion = 0;
                        }
                        if (espacion == caracter.length) {
                            ojn = false;
                            espacion = 0;
                            salton++;
                        } else {
                            espacion = 0;
                        }
                    }
                    if (i > 3420 && i < 7903) {
                        for (int j = 0; j < caracter.length; j++) {
                            if (textocompleto.charAt(i) == caracter[j]) {
                                usuario[saltou] += textocompleto.charAt(i);
                                oju = true;
                                break;
                            }
                            if (textocompleto.charAt(i) != caracter[j] && oju == true) {
                                espaciou++;
                            }
                        }
                        if (i == variableu && usuario[saltou].equals("") && saltou < 63) {
                            saltou++;
                            oju = false;
                            espaciou = 0;
                            variableu += 70;
                        }
                        if (espaciou == caracter.length) {
                            oju = false;
                            espaciou = 0;
                            saltou++;
                            variableu += 70;
                        } else {
                            espaciou = 0;
                        }
                    }
                    if (i > 7902 && i < 57902) {
                        for (int j = 0; j < caracter.length; j++) {
                            if (textocompleto.charAt(i) == caracter[j]) {
                                zona[saltoz] += textocompleto.charAt(i);
                                ojz = true;
                                break;
                            }
                            if (textocompleto.charAt(i) != caracter[j] && ojz == true) {
                                espacioz++;
                            }
                        }
                        if (i == variablez && zona[saltoz].equals("") && saltou < 498) {
                            saltoz++;
                            ojz = false;
                            espacioz = 0;
                            variablez += 100;
                        }
                        if (espacioz == caracter.length) {
                            ojz = false;
                            espacioz = 0;
                            saltoz++;
                            variablez += 100;
                        } else {
                            espacioz = 0;
                        }
                    }

                }
                String Nombrecuentau2 = Nombrecuenta.replaceAll("ci�n", "ción");
                String Nombrecuenta2 = Nombrecuentau2.replaceAll("CI�N", "CIÓN");
                String Nombrecuenta3 = Nombrecuenta2.replaceAll("ALMAC�N", "ALMACÉN");
                String Nombrecuenta4 = Nombrecuenta3.replaceAll("�REA", "ÁREA");
                String Nombrecuenta5 = Nombrecuenta4.replaceAll("CH�VEZ", "CHÁVEZ");
                String Nombrecuenta6 = Nombrecuenta5.replaceAll("L�PEZ", "LÓPEZ");
                String Nombrecuenta7 = Nombrecuenta6.replaceAll("MU�OZ", "MUÑOZ");
                String Nombrecuenta8 = Nombrecuenta7.replaceAll("EMPE�O", "EMPEÑO");
                String Nombrecuenta9 = Nombrecuenta8.replaceAll("C�MP", "CÓMP");
                String Nombrecuenta10 = Nombrecuenta9.replaceAll("R�A", "RÍA");
                String Nombrecuenta11 = Nombrecuenta10.replaceAll("RELLYR � MAKITA", "RELLYR Ó MAKITA");
                String Nombrecuenta12 = Nombrecuenta11.replaceAll("LAS MA�ANITAS", "LAS MAÑANITAS");
                String Nombrecuenta13 = Nombrecuenta12.replaceAll("G�ERA", "GÜERA");
                String Nombrecuenta14 = Nombrecuenta13.replaceAll("PODOL�GICO", "PODOLÓGICO");
                String Nombrecuenta15 = Nombrecuenta14.replaceAll("PODOL�GICO", "PODOLÓGICO");
                String Nombrecuenta16 = Nombrecuenta15.replaceAll("MUCI�OO", "MUCIÑOO");
                String Nombrecuenta17 = Nombrecuenta16.replaceAll("DISE�O", "DISEÑO");
                String Nombrecuenta18 = Nombrecuenta17.replaceAll("COMPA�IA", "COMPAÑIA");
                String Nombrecuenta19 = Nombrecuenta18.replaceAll("PEQUE�", "PEQUEÑ");
                String Nombrecuenta20 = Nombrecuenta19.replaceAll("BUJ�A", "BUJÍA");
                String Nombrecuenta21 = Nombrecuenta20.replaceAll("JORGE�S", "JORGE´S");
                String Nombrecuenta22 = Nombrecuenta21.replaceAll("DIV�N", "DIVÁN");
                String Nombrecuenta23 = Nombrecuenta22.replaceAll("NU�EZ", "NUÑEZ");
                String Nombrecuenta24 = Nombrecuenta23.replaceAll("RODR�GUEZ", "RODRÍGUEZ");
                String Nombrecuenta25 = Nombrecuenta24.replaceAll("HERN�NDEZ", "HERNÁNDEZ");
                String Nombrecuenta26 = Nombrecuenta25.replaceAll("AC�VEZ", "ACÉVEZ");
                String Nombrecuenta27 = Nombrecuenta26.replaceAll("V�ZQUEZ", "VÁZQUEZ");
                String Nombrecuenta28 = Nombrecuenta27.replaceAll("I�ESTA", "IÑESTA");
                String Nombrecuenta29 = Nombrecuenta28.replaceAll("I�ESTA", "IÑESTA");
                String Nombrecuenta30 = Nombrecuenta29.replaceAll("PI�EIRO", "PIÑEIRO");
                String Nombrecuenta31 = Nombrecuenta30.replaceAll("RA�L", "RAÚL");
                String Nombrecuenta32 = Nombrecuenta31.replaceAll("I�IGO", "IÑIGO");
                String Nombrecuenta33 = Nombrecuenta32.replaceAll("P�REZ", "PÉREZ");
                String Nombrecuenta34 = Nombrecuenta33.replaceAll("GONZ�", "GONZÁ");
                String Nombrecuenta35 = Nombrecuenta34.replaceAll("SE�OR", "SEÑOR");
                String Nombrecuenta36 = Nombrecuenta35.replaceAll("CA�A", "CAÑA");
                String Nombrecuenta37 = Nombrecuenta36.replaceAll("S�NCHEZ", "SÁNCHEZ");
                String Nombrecuenta38 = Nombrecuenta37.replaceAll("I�ESTA", "IÑESTA");
                String Nombrecuenta39 = Nombrecuenta38.replaceAll("MU�OZ", "MUÑOZ");
                String Nombrecuenta40 = Nombrecuenta39.replaceAll("MU�E", "MUÑE");
                String Nombrecuenta41 = Nombrecuenta40.replaceAll("JIM�NEZ", "JIMÉNEZ");
                String Nombrecuenta42 = Nombrecuenta41.replaceAll("TA�ON", "TAÑON");
                String Nombrecuenta43 = Nombrecuenta42.replaceAll("C�O", "CÍO");
                String Nombrecuenta44 = Nombrecuenta43.replaceAll("JOS�", "JOSÉ");
                String Nombrecuenta45 = Nombrecuenta44.replaceAll("BENJAM�N", "BENJAMÍN");
                String Nombrecuenta46 = Nombrecuenta45.replaceAll("G�MEZ", "GÓMEZ");
                String Nombrecuenta47 = Nombrecuenta46.replaceAll("ORA�EGUI", "ORAÑEGUI");
                String Nombrecuenta48 = Nombrecuenta47.replaceAll("MART�NEZ", "MARTÍNEZ");
                String Nombrecuenta49 = Nombrecuenta48.replaceAll("NI�O", "NIÑO");
                String Nombrecuenta50 = Nombrecuenta49.replaceAll("M�XICO", "MÉXICO");
                String Nombrecuenta51 = Nombrecuenta50.replaceAll("AVI�A", "AVIÑA");
                String Nombrecuenta52 = Nombrecuenta51.replaceAll("C�A", "CÍA");
                String Nombrecuenta53 = Nombrecuenta52.replaceAll("D�AZ", "DÍAZ");
                String Nombrecuenta54 = Nombrecuenta53.replaceAll("C.P. N�JERALORES", "C.P. NÁJERA");
                String Nombrecuenta55 = Nombrecuenta54.replaceAll("ESTA�OL", "ESTAÑOL");
                EventoshrntSurgerMysql.cuentasSuc.setText(Id_cuenta + "    " + Nombrecuenta55);
                int contadorresponsable = 0;
                for (int i = 0; i < Nombrerestelefono.length; i++) {
                    if (!Nombrerestelefono[i].equals("")) {
                        contadorresponsable++;
                    }
                }
                int usuariocontador = 0;
                for (int i = 0; i < usuario.length; i++) {
                    if (!usuario[i].equals("")) {
                        usuariocontador++;
                    }
                }
                int zonac = 0;
                for (int i = 0; i < zona.length; i++) {
                    if (!zona[i].equals("")) {
                        zonac++;
                    }
                }
                insercioncunetaets(Id_cuenta, Nombrecuenta55, comentario, fechaistalado, tecnico);
                panel_controlInercion(equipo);
                direccionConsulta(domicilio, pais, estado, ciudad, CP, delegacion, colonia, entrecalle, yotracalle, Id_cuenta);
                telefonoconsulta(telefonoC, otrotelef, Id_cuenta);
                if (usuariocontador != 0) {
                    consultaUsuarrio(usuario, Id_cuenta);
                }
                if (zonac != 0) {
                    consultazona(zona, Id_cuenta);
                }
                for (int zz = 0; zz < Nombrerestelefono.length; zz++) {
                    Nombrerestelefono[zz] = ortoG(Nombrerestelefono[zz]);
                }
                if (contadorresponsable != 0) {
                    responsableConsulta(Nombrerestelefono, Id_cuenta);
                }
                Thread.sleep(90);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Hilo6SucInformacion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Hilo6SucInformacion.class.getName()).log(Level.SEVERE, null, ex);
            }   catch (InterruptedException ex) {
                    Logger.getLogger(Hilo6SucInformacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else break;
        }
        JOptionPane.showMessageDialog(null, "Finalizo lectura  Suc");
    }

    public void insercioncunetaets(String cunta, String nombre_cuenta, String Comentario, String fechaSentralizacion, String SporteTecnico) throws UnsupportedEncodingException {
        Connection con = ConexionMysql.ObtenerConexion();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Cuenta"
                    + "(Id_cuenta,"
                    + "Nombre_cuenta,"
                    + "Tipo_cuenta,"
                    + "Estatus_cuenta,"
                    + "Id_cliente,"
                    + "Actividad,"
                    + "Fecha_centalizacion,"
                    + "Comnetario,"
                    + "Soporte_tecnico)"
                    + "VALUES"
                    + "(?,?,?,?,?,?,?,?,?);");
            ps.setString(1, cunta);
            ps.setString(2, nombre_cuenta);
            ps.setString(3, "Principal");
            ps.setString(4, "Activo");
            ps.setString(5, "1");
            ps.setString(6, "Ninguna");
            ps.setString(7, fechaSentralizacion);
            ps.setString(8, Comentario);
            ps.setString(9, SporteTecnico);
            ps.executeUpdate();
            ps.close();
            con.close();

        } catch (SQLException ex) {
            modificarCuenta(nombre_cuenta, cunta, Comentario, fechaSentralizacion, SporteTecnico);

        }
    }

    public void modificarCuenta(String Nombre_cuenta, String Id_cuenta, String Comentario, String Fecha, String Soporte) throws UnsupportedEncodingException {
        try {
            Connection con = ConexionMysql.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE cuenta set Nombre_cuenta=?,"
                    + " Comnetario=?,Fecha_centalizacion=?,Soporte_tecnico=? where Id_cuenta=?;");
            ps.setString(1, Nombre_cuenta);
            ps.setString(2, Comentario);
            ps.setString(3, Fecha);
            ps.setString(4, Soporte);
            ps.setString(5, Id_cuenta);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Hilo6SucInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void panel_controlInercion(String Id_panel_control) {
        try {
            Connection con = ConexionMysql.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO panel_control(Id_panel_control)values(?);");
            ps.setString(1, Id_panel_control);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException ex) {
//            Logger.getLogger(SucNombreCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void direccionInsercion(
            String Direccion,
            String Pais,
            String Estado,
            String Ciudad,
            String Cp,
            String Delegacion,
            String Colonia,
            String Calle1,
            String Calle2,
            String Id_cuenta) {
        try {
            Connection con = ConexionMysql.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Direccion_cuenta("
                    + "Direccion,"
                    + "Pais,"
                    + "Estado,"
                    + "Ciudad,"
                    + "Cp,"
                    + "Delegacion,"
                    + "Colonia,"
                    + "Entre_calle1,"
                    + "Entre_calle2,Id_cuenta)values(?,?,?,?,?,?,?,?,?,?);");
            ps.setString(1, Direccion);
            ps.setString(2, Pais);
            ps.setString(3, Estado);
            ps.setString(4, Ciudad);
            ps.setString(5, Cp);
            ps.setString(6, Delegacion);
            ps.setString(7, Colonia);
            ps.setString(8, Calle1);
            ps.setString(9, Calle2);
            ps.setString(10, Id_cuenta);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Hilo6SucInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void direccionModificacion(
            String Direccion,
            String Pais,
            String Estado,
            String Ciudad,
            String Cp,
            String Delegacion,
            String Colonia,
            String Calle1,
            String Calle2,
            String Id_cuenta) {
        try {
            Connection con = ConexionMysql.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE  Direccion_cuenta set "
                    + "Direccion =?,"
                    + "Pais =?,"
                    + "Estado =?,"
                    + "Ciudad =?,"
                    + "Cp =?,"
                    + "Delegacion =?,"
                    + "Colonia =?,"
                    + "Entre_calle1 =?,"
                    + "Entre_calle2 =? where Id_cuenta =?;");
            ps.setString(1, Direccion);
            ps.setString(2, Pais);
            ps.setString(3, Estado);
            ps.setString(4, Ciudad);
            ps.setString(5, Cp);
            ps.setString(6, Delegacion);
            ps.setString(7, Colonia);
            ps.setString(8, Calle1);
            ps.setString(9, Calle2);
            ps.setString(10, Id_cuenta);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Hilo6SucInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void direccionConsulta(
            String Direccion,
            String Pais,
            String Estado,
            String Ciudad,
            String Cp,
            String Delegacion,
            String Colonia,
            String Calle1,
            String Calle2,
            String Id_cuenta) {
        int contador = 0;
        try {
            Connection con = ConexionMysql.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement("select*from Direccion_cuenta where Id_cuenta=?;");
            ps.setString(1, Id_cuenta);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                contador++;
            }
            rs.close();
            ps.close();
            con.close();
            if (contador != 0) {
                direccionModificacion(Direccion, Pais, Estado, Ciudad, Cp, Delegacion, Colonia, Calle1, Calle2, Id_cuenta);
            } else {
                direccionInsercion(Direccion, Pais, Estado, Ciudad, Cp, Delegacion, Colonia, Calle1, Calle2, Id_cuenta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Hilo6SucInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void telefonos(String[] telefono, String[] otrotelefono, String Id_cuenta) {
        int avisar = 1, otrot = 0,contacto=0;
        try {
            Connection con=null;
            PreparedStatement ps = null;
            for (int i = 0; i < telefono.length; i++) {
                if (otrotelefono[avisar].equals("") && otrotelefono[avisar].equals("") && telefono[i].equals("")) {
                } else {
                    con=ConexionMysql.ObtenerConexion();
                    ps = con.prepareStatement("INSERT INTO Telefono_cuneta"
                            + "(Id_telefono_contacto,Telefono,Avisar,OtroTelefono,Id_cuenta)VALUES(?,?,?,?,?);");
                    ps.setString(1, "" + (i + 1));
                    ps.setString(2, telefono[i]);
                    ps.setString(3, otrotelefono[avisar]);
                    ps.setString(4, otrotelefono[otrot]);
                    ps.setString(5, Id_cuenta);
                    contacto=ps.executeUpdate();
                }
                otrot += 2;
                avisar += 2;
            }
            if(contacto!=0){
             ps.close();
            con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Hilo6SucInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void telefonoconsulta(String[] telefono, String[] otrotelefono, String Id_cuenta) {
        int neto = 0;
        String Id_telefono_cuenta[] = {"", "", "", "", "", ""};
        try {
            Connection con = ConexionMysql.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement("select*from Telefono_cuneta where Id_cuenta=?");
            ps.setString(1, Id_cuenta);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Id_telefono_cuenta[neto] = rs.getString(1);
                neto++;
            }
            rs.close();
            ps.close();
            con.close();
            if (neto != 0) {
                borradoReinsertadoTelefono(telefono, otrotelefono, Id_cuenta);
            } else {
                telefonos(telefono, otrotelefono, Id_cuenta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Hilo6SucInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void borradoReinsertadoTelefono(String[] telefono, String[] otrotelefono, String Id_cuenta) {
        try {
            PreparedStatement ps = null;
            Connection con = ConexionMysql.ObtenerConexion();
            ps = con.prepareStatement("delete from Telefono_cuneta  WHERE Id_cuenta='" + Id_cuenta + "';");
            ps.executeUpdate();
            con.close();
            ps.close();
            telefonos(telefono, otrotelefono, Id_cuenta);
        } catch (SQLException ex) {
            Logger.getLogger(Hilo6SucInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void responsableConsulta(String responsable[], String Id_cuenta) {
        int conteo = 0;
        try {
            Connection con = ConexionMysql.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement("select*from ResponsableCuenta where Id_Cuenta=?");
            ps.setString(1, Id_cuenta);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                conteo++;
            }
            rs.close();
            ps.close();
            con.close();
            if (conteo != 0) {
                borainserta(responsable, Id_cuenta);
            } else {
                InsercionResponsable(responsable, Id_cuenta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Hilo6SucInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void InsercionResponsable(String[] Nombrerestelefono, String Id_cuenta) {
        int nom = 1, cargo = 0;
        try {
            Connection con = null;
            PreparedStatement ps = null;
            for (int i = 0; i < Nombrerestelefono.length / 2; i++) {
                if (Nombrerestelefono[nom].equals("") && Nombrerestelefono[cargo].equals("")) {
                } else {
                    con = ConexionMysql.ObtenerConexion();
                    ps = con.prepareStatement("INSERT INTO ResponsableCuenta(Id_responsable_cuenta,Nombre_responsable_cuenta,Cagro,Id_cuenta)"
                            + "values(?,?,?,?);");
                    ps.setString(1, "" + (i + 1));
                    ps.setString(2, Nombrerestelefono[nom]);
                    ps.setString(3, Nombrerestelefono[cargo]);
                    ps.setString(4, Id_cuenta);
                    ps.executeUpdate();
                }
                nom += 2;
                cargo += 2;
            }
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Hilo6SucInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borainserta(String[] Nombrerestelefono, String Id_cuenta) {
        try {
            PreparedStatement ps = null;
            Connection con = ConexionMysql.ObtenerConexion();
            ps = con.prepareStatement("delete from ResponsableCuenta  WHERE Id_cuenta='" + Id_cuenta + "';");
            ps.executeUpdate();
            con.close();
            ps.close();
            InsercionResponsable(Nombrerestelefono, Id_cuenta);
        } catch (SQLException ex) {
            Logger.getLogger(Hilo6SucInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultaUsuarrio(String usuario[], String Id_Cuenta) {
        int contador = 0;
        try {
            Connection con = ConexionMysql.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement("select*from Usuario_cuenta where Id_Cuenta=?");
            ps.setString(1, Id_Cuenta);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                contador++;
            }
            rs.close();
            ps.close();
            con.close();
            if (contador != 0) {
                insercionmODIFICAR(usuario, Id_Cuenta);
            } else {
                insercionUsuario(usuario, Id_Cuenta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Hilo6SucInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insercionUsuario(String usuario[], String Id_Cuenta) {
        int cargo = 1, usua = 0;
        try {
            Connection con = null;
            PreparedStatement ps = null;
            for (int i = 0; i < usuario.length / 2; i++) {
                if (usuario[usua].equals("") && usuario[cargo].equals("")) {

                } else {
                    con = ConexionMysql.ObtenerConexion();
                    ps = con.prepareStatement("Insert into Usuario_cuenta(Id_usuario_cuenta,Nombre,Cargo,Id_cuenta)values(?,?,?,?);");
                    ps.setString(1, "" + (i + 1));
                    ps.setString(2, usuario[usua]);
                    ps.setString(3, usuario[cargo]);
                    ps.setString(4, Id_Cuenta);
                    ps.executeUpdate();
                }
                usua += 2;
                cargo += 2;
            }
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Hilo6SucInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insercionmODIFICAR(String usuario[], String Id_Cuenta) {
        try {
            Connection con = null;
            PreparedStatement ps = null;
            con = ConexionMysql.ObtenerConexion();
            ps = con.prepareStatement("delete from Usuario_cuenta where Id_cuenta=?;");
            ps.setString(1, Id_Cuenta);
            ps.executeUpdate();
            con.close();
            ps.close();
            insercionUsuario(usuario, Id_Cuenta);
        } catch (SQLException ex) {
            Logger.getLogger(Hilo6SucInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultazona(String[] zona, String Id_cuenta) {
        int contador = 0;
        try {
            Connection con = ConexionMysql.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement("select*from Zona_Cuenta where Id_cuenta=?");
            ps.setString(1, Id_cuenta);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                contador++;
            }
            rs.close();
            ps.close();
            con.close();
            if (contador != 0) {
                modzona(zona, Id_cuenta);
            } else {
                insertzona(zona, Id_cuenta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Hilo6SucInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertzona(String[] zona, String Id_cuenta) {
        int cargo = 1, usua = 0;
        try {
            Connection con = null;
            PreparedStatement ps = null;
            for (int i = 0; i < zona.length / 2; i++) {
                if (zona[cargo].equals("") && zona[usua].equals("")) {
                } else {
                    con = ConexionMysql.ObtenerConexion();
                    ps = con.prepareStatement("insert into Zona_Cuenta(Id_zona_servicio"
                            + ",Descripcion_zona,Equipo_zona,Id_cuenta)values(?,?,?,?);");
                    ps.setString(1, "" + (i + 1));
                    ps.setString(2, zona[usua]);
                    ps.setString(3, zona[cargo]);
                    ps.setString(4, Id_cuenta);
                    ps.executeUpdate();
                }
                usua += 2;
                cargo += 2;
            }
            ps.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Hilo6SucInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String ortoG(String palabra) {
        String palabra1 = palabra.replaceAll("I�N", "IÓN");
        String palabra2 = palabra1.replaceAll("CH�VEZ", "CHÁVEZ");
        String palabra3 = palabra2.replaceAll("GONZ�LEZ", "GONZÁLEZ");
        String palabra4 = palabra3.replaceAll("S�NCHEZ", "SÁNCHEZ");
        String palabra5 = palabra4.replaceAll("HERN�NDEZ", "HERNÁNDEZ");
        String palabra10 = palabra5.replaceAll("JIM�NEZ", "JIMÉNEZ");
        String palabra20 = palabra10.replaceAll("BENJAM�N", "BENJAMÍN");
        String palabra6 = palabra20.replaceAll("M�N", "MÁN");
        String palabra7 = palabra6.replaceAll("OS�", "OSÉ");
        String palabra8 = palabra7.replaceAll("D�Z", "DÉZ");
        String palabra9 = palabra8.replaceAll("P�R", "PÉR");
        String palabra11 = palabra9.replaceAll("C�SAR", "CÉSAR");
        String palabra12 = palabra11.replaceAll("GUTI�RREZ", "GUTIÉRREZ");
        String palabra13 = palabra12.replaceAll("ORT�Z", "ORTÍZ");
        String palabra14 = palabra13.replaceAll("MART�NEZ", "MARTÍNEZ");
        String palabra15 = palabra14.replaceAll("D�AZ", "DÍAZ");
        String palabra16 = palabra15.replaceAll("C�A", "CÍA");
        String palabra17 = palabra16.replaceAll("RODR�GUEZ", "RODRÍGUEZ");
        String palabra18 = palabra17.replaceAll("R�A", "RÍA");
        String palabra19 = palabra18.replaceAll("C�O", "CÍO");
        String palabra21 = palabra19.replaceAll("L�PEZ", "LÓPEZ");
        String palabra22 = palabra21.replaceAll("RA�L", "RAÚL");
        String palabra23 = palabra22.replaceAll("RA�L", "RAÚL");
        String palabra24 = palabra23.replaceAll("JES�S", "JESÚS");
        String palabra25 = palabra24.replaceAll("NU�EZ", "NUÑEZ");
        String palabra26 = palabra25.replaceAll("MU�OZ", "MUÑOZ");
        String palabra27 = palabra26.replaceAll("IBA�EZ", "IBAÑEZ");
        String palabra28 = palabra27.replaceAll("OMA�A", "OMAÑA");
        String palabra29 = palabra28.replaceAll("PE�A", "PEÑA");
        String palabra30 = palabra29.replaceAll("ACU�A", "ACUÑA");
        String palabra31 = palabra30.replaceAll("MUCI�O", "MUCIÑO");
        String palabra32 = palabra31.replaceAll("PATI�O", "PATIÑO");
        String palabra33 = palabra32.replaceAll("ESTA�OL", "ESTAÑOL");
        String palabra34 = palabra33.replaceAll("CASTA�EDA", "CASTAÑEDA");
        String palabra35 = palabra34.replaceAll("ORDO�EZ", "ORDOÑEZ");
        String palabra36 = palabra35.replaceAll("I�ESTA", "IÑESTA");
        String palabra37 = palabra36.replaceAll("PI�EIRO", "PIÑEIRO");
        String palabra38 = palabra37.replaceAll("I�IGO", "IÑIGO");
        String palabra39 = palabra38.replaceAll("VILLASE�OR", "VILLASEÑOR");
        String palabra40 = palabra39.replaceAll("CA�AS", "CAÑAS");
        String palabra41 = palabra40.replaceAll("AVI�A", "AVIÑA");
        String palabra42 = palabra41.replaceAll("CASTA�ON", "CASTAÑON");
        String palabra43 = palabra42.replaceAll("DUE�A", "DUEÑA");
        String palabra44 = palabra43.replaceAll("ESTA�OL", "ESTAÑOL");
        String palabra45 = palabra44.replaceAll("ORA�EGUI", "ORAÑEGUI");
        String palabra46 = palabra45.replaceAll("MA�ON", "MAÑON");
        String palabra47 = palabra46.replaceAll("V�ZQU", "VÁZQU");
        String palabra48 = palabra47.replaceAll("DUE�O", "DUEÑO");
        String palabra49 = palabra48.replaceAll("NOEM�", "NOEMÍ");
        String palabra50 = palabra49.replaceAll("RAM�REZ", "RAMÍREZ");
        String palabra51 = palabra50.replaceAll("TREVI�O", "TREVIÑO");
        String palabra52 = palabra51.replaceAll("N�JERA", "NÁJERA");
        String palabra533 = palabra52.replaceAll("G�ERA", "GÜERA");
        String palabra53 = palabra533.replaceAll("MU�ETON", "MUÑETON");
        return palabra53;
    }

    public void modzona(String[] zona, String Id_cuenta) {
        try {
            Connection con = ConexionMysql.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement("delete from Zona_Cuenta where Id_cuenta=?;");
            ps.setString(1, Id_cuenta);
            ps.executeUpdate();
            ps.close();
            con.close();
            insertzona(zona, Id_cuenta);
        } catch (SQLException ex) {
            Logger.getLogger(Hilo6SucInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
