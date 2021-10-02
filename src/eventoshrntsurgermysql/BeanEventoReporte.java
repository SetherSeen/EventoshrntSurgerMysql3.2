package eventoshrntsurgermysql;

public class BeanEventoReporte {
   private String evento;
public BeanEventoReporte()
    {
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public BeanEventoReporte( String evento)
    {
       this.evento=evento;
    } 
}
