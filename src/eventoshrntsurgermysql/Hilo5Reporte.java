package eventoshrntsurgermysql;

import Utilerias.ConexionMysql;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class Hilo5Reporte implements Runnable{
Thread t;
private String cuenta;
private int nuero;
private int tipo;
    public Hilo5Reporte(String cuneta,int numero,int tipo) {
        t=new Thread(this);
    this.cuenta=cuneta;
    this.nuero=numero;
    this.tipo=tipo;
    }

    public void stard(){
    t.start();
    }
    
    @Override
    public void run() {
    if(tipo==1)
    listaCuentasActivasIndividual(cuenta, nuero);
    else
        listaCuentasActivasTodas(nuero);
      
    }
    
     public void Generar_reporte(String Id_cuenta, String Nombre_cuneta, int mes) {
    try {
        FechaMysql ff = new FechaMysql();
        System.out.println(ff.fechaPasadaNombreMes(mes)+"----------");
        DaoNombreResponsable nr = new DaoNombreResponsable();
        DaoConsultaContenidoReporte hh = new DaoConsultaContenidoReporte();
        JasperReport reporte = (JasperReport) JRLoader.loadObject(new File("ReporteGCS4.jasper"));
        Map parametros = new HashMap();
        parametros.clear();
        parametros.put("mes", "Correspondiente al mes: " + ff.fechaPasadaNombreMes(mes) + ".");
        parametros.put("cuenta", "No Cuenta: " + Id_cuenta);
        parametros.put("nombrecuenta", "Nombre: " + Nombre_cuneta);
        parametros.put("cliente", nr.nombreEncargado(Id_cuenta));
        parametros.put("nombreReporte", Id_cuenta + "-" + ff.fechaPasadaNombreMes(mes));
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(hh.LecturaDocHrnt(Id_cuenta, mes, "C:\\Users\\usuario\\Desktop\\Bases\\Reportes")));
        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("C:\\Users\\usuario\\Desktop\\Bases\\Reportes\\"+Id_cuenta +".pdf"));
        exporter.exportReport();
    } catch (JRException ex) {
        Logger.getLogger(Hilo5Reporte.class.getName()).log(Level.SEVERE, null, ex);
    } catch (UnsupportedEncodingException ex) {
        Logger.getLogger(Hilo5Reporte.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
     
      public void listaCuentasActivasIndividual(String Id_cuenta, int numeromes) {
        List lista = new ArrayList();
        try {
            Connection con = ConexionMysql.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement("select*from cuenta where  Id_cuenta='" + Id_cuenta + "';");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BeanCuenta b = new BeanCuenta();
                b.setId_cuenta(rs.getString(1));
                b.setNombre_cuenta(rs.getString(2));
                lista.add(b);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Hilo5Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    for (Object lista1 : lista) {
        BeanCuenta l = new BeanCuenta();
        l = (BeanCuenta) lista1;
        try {
            Generar_reporte(l.getId_cuenta(), l.getNombre_cuenta(), numeromes);
            Thread.sleep(80);
        } catch (InterruptedException ex) {
            Logger.getLogger(Hilo5Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
          JOptionPane.showMessageDialog(null, "Fin de creacion reportes !!!");
    }

    public void listaCuentasActivasTodas(int numeromes) {
        List lista = new ArrayList();
        try {
            Connection con = ConexionMysql.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement("select*from cuenta where  Nombre_cuenta NOT like '%(BAJA)%';");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BeanCuenta b = new BeanCuenta();
                b.setId_cuenta(rs.getString(1));
                b.setNombre_cuenta(rs.getString(2));
                System.out.println(b.getId_cuenta() + " " + b.getNombre_cuenta());
                lista.add(b);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Hilo5Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    for (Object lista1 : lista) {
        BeanCuenta l = new BeanCuenta();
        l = (BeanCuenta) lista1;
        try {
            Generar_reporte(l.getId_cuenta(), l.getNombre_cuenta(), numeromes);
            Thread.sleep(80);
        } catch (InterruptedException ex) {
            Logger.getLogger(Hilo5Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    JOptionPane.showMessageDialog(null, "Fin de creacion reportes !!!");
    }
}
