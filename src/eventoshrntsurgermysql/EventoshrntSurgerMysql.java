package eventoshrntsurgermysql;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class EventoshrntSurgerMysql extends JFrame {
 private JButton inicioContinuo;
    private JButton ruta;
    private JButton paraHilo1;
    private JButton inicioBat;
    private JButton reportes;
    private JButton SurgerLog;
    private JButton paraSurgerLog;
    private JButton cuentas;
    private JButton stopCuentas;
    public static JLabel eventoConexionDirecta;
    public static JLabel eventobatLog;
    public static JLabel cuentasSuc;
    Hilo1 h1;
    Hilo4LogSurger h4;
    Hilo6SucInformacion rilo;
    boolean ok;
    public EventoshrntSurgerMysql() {
       super("EventosHrnt");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/gcs_logotipo.PNG"));
        this.setIconImage(icon);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = pantalla.width;
        JPanel panel = new JPanel();
        panel.setLayout(null);
        inicioContinuo = new JButton("Conexion directa");
        inicioContinuo.setBounds(10,5, 130,25);
        inicioContinuo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paraHilo1.setEnabled(true);
                inicioContinuo.setEnabled(false);
                h1 = new Hilo1(true);
                h1.stard();
            }
        });
        paraHilo1 = new JButton("para conexion directa");
        paraHilo1.setBounds(140,5, 130,25);
        paraHilo1.setEnabled(false);
        paraHilo1.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                h1.stop();
                paraHilo1.setEnabled(false);
                inicioContinuo.setEnabled(true);
            }
        });
        inicioBat = new JButton("bat SqLite");
        inicioBat.setBounds(10,34, 130,25);
        inicioBat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hilo2 d = new Hilo2();
                d.stard();
            }
        });
        ruta = new JButton("ruta archivo");
        ruta.setBounds(ancho-150,5, 130,25);
        ruta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelRuta r = new PanelRuta();
                r.setVisible(true);
            }
        });
        
        SurgerLog = new JButton("Surger");
        SurgerLog.setBounds(10,60, 130, 25);
        SurgerLog.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                paraSurgerLog.setEnabled(true);
                SurgerLog.setEnabled(false);
                h4 = new Hilo4LogSurger(true);
                ok=true;
                h4.strard();
            }
        });
        paraSurgerLog = new JButton("Para surger");
        paraSurgerLog.setBounds(140, 60, 130, 25);
        paraSurgerLog.setEnabled(false);
        paraSurgerLog.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                h4.stop();
                ok=false;
                paraSurgerLog.setEnabled(false);
                SurgerLog.setEnabled(true);
            }
        });
        reportes=new JButton("Reporte");
        reportes.setBounds(10, 90, 130, 25);
        reportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           ReportePanelFrameword r=new ReportePanelFrameword();
           r.setVisible(true);
            }
        });
        cuentas=new JButton("Suc");
        cuentas.setBounds(10, 120,130, 25);
        cuentas.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cuentas.setEnabled(false);
                stopCuentas.setEnabled(true);
        rilo=new Hilo6SucInformacion(true);
         rilo.stard();
            }
        });
        stopCuentas=new  JButton("Alto suc");
        stopCuentas.setBounds(140, 120, 130, 25);
        stopCuentas.setEnabled(false);
        stopCuentas.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cuentas.setEnabled(true);
           rilo.stop();
            }
        });
        eventoConexionDirecta=new JLabel("Conexion directa :");
        eventoConexionDirecta.setBounds(280, 5, ancho-280,25);
        eventobatLog=new JLabel("bat log suerger");
        eventobatLog.setBounds(280, 60, ancho-280,25);
        cuentasSuc=new JLabel("...");
        cuentasSuc.setBounds(280, 120, ancho-280, 25);
        panel.add(inicioContinuo);
        panel.add(paraHilo1);
        panel.add(ruta);
        panel.add(inicioBat);
        panel.add(SurgerLog);
        panel.add(paraSurgerLog);
        panel.add(reportes);
        panel.add(cuentas);
        panel.add(stopCuentas);
        panel.add(eventoConexionDirecta);
        panel.add(eventobatLog);
        panel.add(cuentasSuc);
        this.add(panel);
        this.setSize(ancho, 190);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        new EventoshrntSurgerMysql().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

    

