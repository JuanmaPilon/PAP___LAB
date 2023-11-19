package logica;

import java.util.List;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTSalidaTuristica {
    private String nombre;
    private int cantMax;
    private int visitas;
    private Date fAlta;
    private Date fSalida;
    private String lugar;
    private String nombreActividad;

    public DTSalidaTuristica() {
    }


    public DTSalidaTuristica(String nombre, int cantMax, Date fAlta, Date fSalida, String lugar, String nombreActividad) {
        this.nombre = nombre;
        this.cantMax = cantMax;
        this.fAlta = fAlta;
        this.fSalida = fSalida;
        this.lugar = lugar;
        this.nombreActividad = nombreActividad;
    }
    
    public DTSalidaTuristica(String nombre, int cantMax, int visitas, Date fAlta, Date fSalida, String lugar, String nombreActividad) {
        this.nombre = nombre;
        this.cantMax = cantMax;
        this.visitas = visitas;
        this.fAlta = fAlta;
        this.fSalida = fSalida;
        this.lugar = lugar;
        this.nombreActividad = nombreActividad;
    }

    public int getVisitas() {
        return visitas;
    }

    public void setVisitas(int visitas) {
        this.visitas = visitas;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public int getCantMax() {
        return cantMax;
    }

    public Date getfAlta() {
        return fAlta;
    }

    public Date getfSalida() {
        return fSalida;
    }

    public String getLugar() {
        return lugar;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }


    
    
    


}
