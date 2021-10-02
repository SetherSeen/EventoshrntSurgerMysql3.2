

package eventoshrntsurgermysql;

import Utilerias.ConexionMysql;
import Utilerias.ConexionSqLite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeerSqliteMysql {
    public void SqliteLectura(int ops){
         char[] caracter = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
        'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B',
        'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '.', ',', ':', ' ', 'á', 'é', 'í', 'ó', '(', ')', 'Ó', 'Á', 'É', 'Í'};
        try {
            int t = mesdebasededatos(ops);
            int[] unidad = consultatablafalla();
            String sql = "SELECT * FROM EventosHRNT where (eventoNo>" + unidad[0] + ");";
            if (unidad[1] != t) {
                sql = "SELECT * FROM EventosHRNT;";
                borrardato();
            }
            if (unidad[0] == 0) {
                sql = "SELECT * FROM EventosHRNT;";
            }
            Connection c=null;
            if(ops==1){
            FechaMysql f=new FechaMysql();
                c = ConexionSqLite.ObtenerConexionSqLite("\\\\Central-hrntwt\\hrnt\\REP\\"+f.fechaPasadaNombreMes(0));
            }
            if(ops==2)
            c = ConexionSqLite.ObtenerConexionSqLite("C:\\Users\\usuario\\Desktop\\Bases");            
            Statement  stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String mes = "", dia = "", año = "", hora = "", u = "";
                String eventoNo = rs.getString("eventoNo");
                String uuid = rs.getString("uuid");
                String fechahora = rs.getString("fechahora");
                String receptor = rs.getString("receptor");
                String clientecuenta = rs.getString("clientecuenta");
                String tablaevento = rs.getString("tablaevento");
                String codigoevento = rs.getString("codigoevento");
                String eventodescripcion = rs.getString("eventodescripcion");
                String evento = rs.getString("evento");
                String eventovalido = rs.getString("eventovalido");
                String operador = rs.getString("operador");
                EventoshrntSurgerMysql.eventoConexionDirecta.setText("|" + eventoNo 
                        + "   |" + "|" + uuid + "  |" + "|" + fechahora + "    |"
                        + "|" + receptor + "   |" + "|" + clientecuenta + "    |" 
                        + "|" + tablaevento + "  |" + "|" + codigoevento + " |"
                        + "|" + eventodescripcion + "|" + "| " + evento + "   |"
                        + "|" + eventovalido + "  |" + "|" + operador + " |");
                for (int i = 0; i < evento.length(); i++) {
                    if (i > 3 && i < 6) {
                        dia += evento.charAt(i);
                    }
                    if (i > 6 && i < 9) {
                        mes += evento.charAt(i);
                    }
                    if (i > 9 && i < 12) {
                        año += evento.charAt(i);
                    }
                    if (i > 13 && i < 19) {
                        hora += evento.charAt(i);
                    }
                }
                fechahora = año + "-" + mes + "-" + dia + " " + hora + ":00";
                for (int j = 0; j < eventodescripcion.length(); j++) {
                    for (int k = 0; k < caracter.length; k++) {
                        if ("Apertura".equals(u)) {
                            u += " ";
                        }
                        if ("Cierre".equals(u)) {
                            u += " ";
                        }
                        if (eventodescripcion.charAt(j) == caracter[k]) {
                            u += eventodescripcion.charAt(j);
                        }
                    }
                }
                eventodescripcion = u;
                if (!"-- :00".equals(fechahora)) {
                    mySqlIncerxion(eventoNo, uuid, fechahora, receptor, clientecuenta, tablaevento, codigoevento, eventodescripcion, evento, eventovalido, operador);
                    unidad = consultatablafalla();
                    if (unidad[0] == 0) {
                        insercionfallas(Integer.parseInt(eventoNo), mes);
                    }
                    if (unidad[0] > 0) {
                        actualizartabla(Integer.parseInt(eventoNo), mes);
                    }
                }
            }
            rs.close();
            stmt.close();
            c.close();
             try {
                    Thread.sleep(189);
                } catch (InterruptedException ex) {
                    Logger.getLogger(LeerSqliteMysql.class.getName()).log(Level.SEVERE, null, ex);
                }
        } catch (SQLException ex) {
            Logger.getLogger(LeerSqliteMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     static public void mySqlIncerxion(String eventoNo, String uuid, String fechahora, String receptor, String clientecuenta, String tablaevento, String codigoevento, String eventodescripcion, String evento, String eventovalido, String operador) {
        try {
            Connection con = ConexionMysql.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement("Insert into eventosHRNT(eventoNo,fechahora,uuid,"
                    + "receptor,clientecuenta,tablaevento,codigoevento,eventodescripcion,"
                    + "evento,eventovalido,operador,Nota,Verificado)VALUES(?,?,?,?,?,?,?,?,?,?,?,'',0);");
            ps.setString(1, eventoNo);
            ps.setString(2, fechahora);
            ps.setString(3, uuid);
            ps.setString(4, receptor);
            ps.setString(5, clientecuenta);
            ps.setString(6, tablaevento);
            ps.setString(7, codigoevento);
            ps.setString(8, eventodescripcion);
            ps.setString(9, evento);
            ps.setString(10, eventovalido);
            ps.setString(11, operador);
         int i= ps.executeUpdate();
           ps.close();
            con.close();
           } catch (SQLException ex) {
               System.out.println("EVENTOS DE EERORE :"+ex);

        }
    }

    static public int[] consultatablafalla() {
        int[] res = new int[2];
        try {
            Connection con = ConexionMysql.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement("select*from fallas;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res[0] = rs.getInt("eventoNo");
                res[1] = rs.getInt("Descripccion");                
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(LeerSqliteMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    static public void insercionfallas(int eventoNo, String mes) {
        try {
            Connection con = ConexionMysql.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO fallas(Id_falla,Descripccion,eventoNo)values(1,'" + mes + "'," + eventoNo + ");");
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(LeerSqliteMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static public void actualizartabla(int eventoNo, String mes) {
        try {
            Connection con = ConexionMysql.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE  fallas SET Descripccion='" + mes + "',eventoNo=" + eventoNo + " where Id_falla=1;");
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(LeerSqliteMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static public int mesdebasededatos(int ops) {
         FechaMysql f=new FechaMysql();
        int rest = 0;
        String evento = "", mes = "";
        try {
           Connection c=null;
            if(ops==1)
            c = ConexionSqLite.ObtenerConexionSqLite("\\\\Central-hrntwt\\hrnt\\REP\\"+f.fechaPasadaNombreMes(0));
            if(ops==2)
            c = ConexionSqLite.ObtenerConexionSqLite("C:\\Users\\usuario\\Desktop\\Bases");
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("select*from EventosHRNT;");
            while (rs.next()) {
                evento = rs.getString("evento");
                break;
            }
            rs.close();
            st.close();
            c.close();
            if (!"".equals(evento)) {
                for (int i = 0; i < evento.length(); i++) {
                    if (i > 6 && i < 9) {
                        mes += evento.charAt(i);
                    }
                }
            }
            rest = Integer.parseInt(mes);
        } catch (SQLException ex) {
            Logger.getLogger(LeerSqliteMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rest;
    }

    static public void borrardato() {
        try {
            Connection con = ConexionMysql.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement("delete from fallas;");
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(LeerSqliteMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
