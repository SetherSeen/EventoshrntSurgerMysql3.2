package eventoshrntsurgermysql;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfCopyFields;
import com.lowagie.text.pdf.PdfReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class unionpdf {
    public static void main(String[] args) {
        try {
            PdfReader reader1 = new PdfReader("C:\\Users\\usuario\\Desktop\\Bases\\Reportes\\1668 5.pdf");
            PdfReader reader2 = new PdfReader("C:\\Users\\usuario\\Desktop\\Bases\\Reportes\\1668 1.pdf");
            PdfCopyFields copy = new PdfCopyFields(new FileOutputStream("C:\\Users\\usuario\\Desktop\\Bases\\Reportes\\salida.pdf"));
            copy.addDocument(reader1);
            copy.addDocument(reader2);
            copy.close();
        } catch (IOException ex) {
            Logger.getLogger(unionpdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(unionpdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
