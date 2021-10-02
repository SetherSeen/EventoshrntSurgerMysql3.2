package eventoshrntsurgermysql;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ReportePanelFrameword extends JFrame implements ChangeListener {
    private JButton todoReporte;
    private JButton reporteIndividual;
    private JTextField cuenta;
    private JCheckBox mes1;
    private JCheckBox mes7;
    private JCheckBox mes2;
    private JCheckBox mes3;
    private JCheckBox mes4;
    private JCheckBox mes5;
    private JCheckBox mes6;
    int[] mesn1 = new int[7];
    Hilo5Reporte hr1;
    
    ReportePanelFrameword() {
        super("Reportes");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/gcs_logotipo.PNG"));
        this.setIconImage(icon);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        FechaMysql f = new FechaMysql();
        reporteIndividual = new JButton("ReposteIndividual");
        cuenta = new JTextField();
        cuenta.setBounds(100,220, 160, 25);
        mes1 = new JCheckBox(f.fechaPasadaNombreMes(0), false);
        mes1.setBounds(10, 10, 150, 30);
        mes1.addChangeListener(this);
        mes2 = new JCheckBox(f.fechaPasadaNombreMes(1), false);
        mes2.setBounds(10, 40, 150, 30);
        mes2.addChangeListener(this);
        mes3 = new JCheckBox(f.fechaPasadaNombreMes(2), false);
        mes3.setBounds(10, 70, 150, 30);
        mes3.addChangeListener(this);
        mes4 = new JCheckBox(f.fechaPasadaNombreMes(3), false);
        mes4.setBounds(10, 100, 150, 30);
        mes4.addChangeListener(this);
        mes5 = new JCheckBox(f.fechaPasadaNombreMes(4), false);
        mes5.setBounds(10, 130, 150, 30);
        mes5.addChangeListener(this);
        mes6 = new JCheckBox(f.fechaPasadaNombreMes(5), false);
        mes6.setBounds(10, 160, 150, 30);
        mes6.addChangeListener(this);
        mes7 = new JCheckBox(f.fechaPasadaNombreMes(6), false);
        mes7.addChangeListener(this);
        mes7.setBounds(10, 190, 150, 30);
        todoReporte = new JButton("Todos los reportes");
        todoReporte.setBounds(100, 250, 100, 20);
        todoReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int k = 0; k < mesn1.length; k++) {
                    System.out.println(mesn1[k]);
                    if (mesn1[k] != -1) {
                        hr1 = new Hilo5Reporte("", mesn1[k], 0);
                        hr1.stard();
                    }
                }
            }
        });
        reporteIndividual=new JButton("reporte individual");
        reporteIndividual.setBounds(200, 250, 100,20);
        reporteIndividual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              for(int i=0;i<mesn1.length;i++){
              if(mesn1[i]>-1){
                   System.out.println(mesn1[i]+"  "+cuenta.getText());
                  hr1=new Hilo5Reporte(cuenta.getText(),mesn1[i],1);
                  hr1.stard();
              }
              }
            }
            });
        
        panel.add(mes7);
        panel.add(mes6);
        panel.add(mes5);
        panel.add(mes4);
        panel.add(mes3);
        panel.add(mes2);
        panel.add(mes1);
        panel.add(todoReporte);
        panel.add(reporteIndividual);
        panel.add(cuenta);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = pantalla.width;
        this.setSize(ancho / 3, 320);
        this.add(panel);

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (mes1.isSelected()) {
            mesn1[0] = 0;
        }else mesn1[0]=-1;
        if (mes2.isSelected() == true) {
            mesn1[1] = 1;
        }else mesn1[1]=-1;
        if (mes3.isSelected() == true) {
            mesn1[2] = 2;
        }else mesn1[2]=-1;
        if (mes4.isSelected() == true) {
            mesn1[3] = 3;
        }else mesn1[3]=-1;
        if (mes5.isSelected() == true) {
            mesn1[4] = 4;
        }else mesn1[4]=-1;
        if (mes6.isSelected() == true) {
            mesn1[5] = 5;
        }else mesn1[5]=-1;
        if (mes7.isSelected() == true) {
            mesn1[6] = 6;
        }else mesn1[6]=-1;
    }
}

    

