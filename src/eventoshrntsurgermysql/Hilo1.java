package eventoshrntsurgermysql;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Hilo1 implements Runnable {
  volatile boolean running;
  Thread t;
    public Hilo1(boolean running) {
        this.running=running;
        t=new Thread(this);
    }
  
  public void stard(){
  t.start();
  }
    
    @Override
    public void run() {
        LeerSqliteMysql l = new LeerSqliteMysql();
        while (running) {
            try {
                l.SqliteLectura(1);
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilo1.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    public void stop(){
    running=false;
    }
}
