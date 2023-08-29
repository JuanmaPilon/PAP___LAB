import java.util.ArrayList;
import java.util.List;

public class Paquete {
    
    private String nombre;
    private String desc;
    private int validez;
    private int descuento;

 public Paquete(String nombre, String desc, int validez, int descuento) {
    this.nombre = nombre;
    this.desc = desc;
    this.validez = validez;
    this.descuento = descuento;
}
///////////////////
public void setNombre(String nombre) {
    this.nombre = nombre;
}

public void setDesc(String desc) {
    this.desc = desc;
}

public void setValidez(int validez) {
    this.validez = validez;
}

public void setDescuento(int descuento) {
    this.descuento = descuento;
}
////////////////////
public String getNombre() {
    return nombre;
}

public String getDesc() {
    return desc;
}

public int getValidez() {
    return validez;
}

public int getDescuento() {
    return descuento;
}



}
