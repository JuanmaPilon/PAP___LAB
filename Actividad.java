import java.util.Date;

public class Actividad {
    private String nombre;
    private String desc;
    private int duracion;
    private float costo;
    private String ciudad;
    private Date fAlta;
//////
public Actividad(String nombre,String desc, int duracion, float costo, String ciudad, Date fAlta) {
    this.nombre = nombre;
    this.desc = desc;
    this.duracion = duracion;
    this.costo = costo;
    this.ciudad = ciudad;
    this.fAlta = fAlta;
}
///////////
public void setNombre(String nombre) {
    this.nombre = nombre;
}

public void setDesc(String desc) {
    this.desc = desc;
}

public void setDuracion(int duracion) {
    this.duracion = duracion;
}

public void setCosto(float costo) {
    this.costo = costo;
}

public void setCiudad(String ciudad) {
    this.ciudad = ciudad;
}

public void setfAlta(Date fAlta) {
    this.fAlta = fAlta;
}
////////////////
public String getNombre() {
    return nombre;
}

public String getDesc() {
    return desc;
}

public int getDuracion() {
    return duracion;
}

public float getCosto() {
    return costo;
}

public String getCiudad() {
    return ciudad;
}

public Date getfAlta() {
    return fAlta;
}

}
