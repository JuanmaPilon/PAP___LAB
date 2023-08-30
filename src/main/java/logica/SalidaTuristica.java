package logica;

import presentacion.DSalidaTuristica;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;
public class SalidaTuristica {
    private String nombre;
    private int cantMax;
    private Date fAlta;
    private Date fSalida;
    private String lugar;

    
    public SalidaTuristica(String nombre, int cantMax, Date fAlta, Date fSalida, String lugar) {
        this.nombre = nombre;
        this.cantMax = cantMax;
        this.fAlta = fAlta;
        this.fSalida = fSalida;
        this.lugar = lugar;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCantMax() {
        return cantMax;
    }
    public void setCantMax(int cantMax) {
        this.cantMax = cantMax;
    }
    public Date getfAlta() {
        return fAlta;
    }
    public void setfAlta(Date fAlta) {
        this.fAlta = fAlta;
    }
    public Date getfSalida() {
        return fSalida;
    }
    public void setfSalida(Date fSalida) {
        this.fSalida = fSalida;
    }
    public String getLugar() {
        return lugar;
    }
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    public DSalidaTuristica parse(){
        return new DSalidaTuristica(getNombre(),getCantMax(),getfAlta(),getfSalida(),getLugar());
    }

}
