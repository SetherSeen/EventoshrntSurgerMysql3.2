package eventoshrntsurgermysql;

import Utilerias.ConexionMysql;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PdfImprecion {
public void unirPdf(){
      List<InputStream> list = new ArrayList<InputStream>();
        try {
             Connection con=ConexionMysql.ObtenerConexion();
        PreparedStatement ps=con.prepareStatement("select*from pfInprimir order by pos");
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            for(int i=0;i<Integer.parseInt(rs.getString("cantidad"));i++){
                File archivo=new File("C:/Users/usuario/Desktop/Bases/Reportes/"+rs.getString("Id_cuenta")+".pdf");
                if(archivo.exists()){
                FileInputStream input=new  FileInputStream(archivo);
                 list.add(input);}
        }
        }
            OutputStream out = new FileOutputStream(new File("C:/Users/usuario/Desktop/Bases/Reportes/result.pdf"));
            doMerge(list, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
        Logger.getLogger(PdfImprecion.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

   
    public static void doMerge(List<InputStream> list, OutputStream outputStream)
            throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        for (InputStream in : list) {
            PdfReader reader = new PdfReader(in);
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                document.newPage();
                PdfImportedPage page = writer.getImportedPage(reader, i);
                cb.addTemplate(page, 0, 0);
            }
        }
       
        outputStream.flush();
        document.close();
        outputStream.close();
    }
    public static void main(String[] args) {
        PdfImprecion f=new PdfImprecion();
        f.unirPdf();
    }
    
    
}

