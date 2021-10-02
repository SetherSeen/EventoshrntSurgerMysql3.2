
package eventoshrntsurgermysql;
public class BeanCuenta {

    public String getId_cuenta() {
        return Id_cuenta;
    }

    public void setId_cuenta(String Id_cuenta) {
        this.Id_cuenta = Id_cuenta;
    }

    public String getNombre_cuenta() {
        return Nombre_cuenta;
    }

    public void setNombre_cuenta(String Nombre_cuenta) {
        this.Nombre_cuenta = Nombre_cuenta;
    }

    public String getTipo_cuenta() {
        return Tipo_cuenta;
    }

    public void setTipo_cuenta(String Tipo_cuenta) {
        this.Tipo_cuenta = Tipo_cuenta;
    }

    public String getEstatus_cuenta() {
        return Estatus_cuenta;
    }

    public void setEstatus_cuenta(String Estatus_cuenta) {
        this.Estatus_cuenta = Estatus_cuenta;
    }

    public String getId_cliente() {
        return Id_cliente;
    }

    public void setId_cliente(String Id_cliente) {
        this.Id_cliente = Id_cliente;
    }

    public String getActividad() {
        return Actividad;
    }

    public void setActividad(String Actividad) {
        this.Actividad = Actividad;
    }

    public String getFecha_centralizacion() {
        return Fecha_centralizacion;
    }

    public void setFecha_centralizacion(String Fecha_centralizacion) {
        this.Fecha_centralizacion = Fecha_centralizacion;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String Comentario) {
        this.Comentario = Comentario;
    }

    public String getCometarioTecnico() {
        return CometarioTecnico;
    }

    public void setCometarioTecnico(String CometarioTecnico) {
        this.CometarioTecnico = CometarioTecnico;
    }
    private String Id_cuenta;
    private String Nombre_cuenta;
    private String Tipo_cuenta;
    private String Estatus_cuenta;
    private String Id_cliente;
    private String Actividad;
    private String Fecha_centralizacion;
    private String Comentario;
    private String CometarioTecnico;
}
