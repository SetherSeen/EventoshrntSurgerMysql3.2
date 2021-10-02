package eventoshrntsurgermysql;

import Utilerias.ConexionMysql;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoNombreResponsable {

    public String nombreEncargado(String Id_cuneta) throws UnsupportedEncodingException {
        String Nombre_responsable_cuenta = null;
        try {
            Connection con = ConexionMysql.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement("SELECT*FROM ResponsableCuenta where Id_cuenta=?;");
            ps.setString(1, Id_cuneta);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int mua=rs.getInt("Id_responsable_cuenta");
                if(mua<=1)
                Nombre_responsable_cuenta = rs.getString("Nombre_responsable_cuenta");
                break;
            }
            if(Nombre_responsable_cuenta==null)
                Nombre_responsable_cuenta=" ";
            System.out.println(Nombre_responsable_cuenta);
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoNombreResponsable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Nombre_responsable_cuenta;
    }
    }

  