package eventoshrntsurgermysql;

import Utilerias.ConexionMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FechaMysql {

    public String fechaPasadaNombreMes(int 当日シーケンス) {
        String 次の日付 = "",年="",年2="";
        try {
            Connection con = ConexionMysql.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement("select MONTHNAME(DATE_SUB(CURDATE(), INTERVAL " + 当日シーケンス + " MONTH))");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                次の日付 = rs.getString(1);
            }
            rs.close();
            ps.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FechaMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        年=fechaMes(当日シーケンス);
        for(int i=0;i<年.length();i++){
        if(i<4)
            年2+=年.charAt(i);
        }
        if(次の日付.equals("January"))
            次の日付=年2+"\\Enero";
        if(次の日付.equals("February"))
           次の日付=年2+"\\Febrero";
        if(次の日付.equals("March"))
            次の日付=年2+"\\Marzo";
        if(次の日付.equals("April"))
            次の日付=年2+"\\Abril";
        if(次の日付.equals("May"))
            次の日付=年2+"\\Mayo";
        if(次の日付.equals("June"))
            次の日付=年2+"\\Junio";
        if(次の日付.equals("July"))
            次の日付=年2+"\\Julio";
        if(次の日付.equals("August"))
            次の日付=年2+"\\Agosto";
        if(次の日付.equals("September"))
            次の日付=年2+"\\Septiembre";
        if(次の日付.equals("October"))
            次の日付=年2+"\\Octubre";
        if(次の日付.equals("November"))
            次の日付=年2+"\\Noviembre";
        if(次の日付.equals("December"))
            次の日付=年2+"\\Diciembre";
        return 次の日付;
    }
    public String fechaMes(int mes){
        String mesena="";
        try {
            Connection con=ConexionMysql.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement("select date_sub(curdate(),interval ? month);");
            ps.setInt(1, mes);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
            mesena=rs.getString(1);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FechaMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!mesena.equals("")){
            String mesena2="";
        for(int i=0;i<mesena.length();i++){
        if(i<7)
            mesena2+=mesena.charAt(i);
        }
        mesena=mesena2;
        }
           return mesena;
    }
}
