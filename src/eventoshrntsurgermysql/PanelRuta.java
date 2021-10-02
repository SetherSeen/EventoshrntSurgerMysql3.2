package eventoshrntsurgermysql;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PanelRuta extends JFrame{
private JButton Guardare;
    private JTextField ruta;
    private JButton Examinar;
    public PanelRuta() {
        super("Ruta archivos.");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/gcs_logotipo.PNG"));
        this.setIconImage(icon);
        Hilo3Ruta r=new Hilo3Ruta();
           Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = pantalla.width;
        JPanel panel=new JPanel();
        panel.setLayout(null);
        ruta = new JTextField();
        ruta.setBounds(15,10,200, 25);
        ruta.setText(r.leerRutaBat());
        Guardare = new JButton("Guardar");
        Guardare.setBounds(15, 50, 150, 30);
        Guardare.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
         Hilo3Ruta ru=new Hilo3Ruta();
                ru.stard(ruta.getText());
               
            }
        });
        Examinar=new JButton("Examinar");
       Examinar.setBounds(205,10,110, 25);
        Examinar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           JFileChooser selecion=new JFileChooser();
           FileNameExtensionFilter filter = new FileNameExtensionFilter("Base db", "db", "db");
            selecion.setFileFilter(filter);
           int option=selecion.showOpenDialog(Examinar);
           if(option==JFileChooser.APPROVE_OPTION){
              ruta.setText(selecion.getSelectedFile().getAbsolutePath());
           }
        
            }
        });

        panel.add(ruta);
        panel.add(Examinar);
        panel.add(Guardare);
         this.setSize(ancho / 4, 220);
        this.add(panel);
      
    }
    
}
