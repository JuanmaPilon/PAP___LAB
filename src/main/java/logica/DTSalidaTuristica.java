/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author natil
 */
public class DTSalidaTuristica {
    private String nombre;
    private int cantMax;
    private Date fAlta;
    private Date fSalida;
    private String lugar;
    private ArrayList<Integer> listaIdInscripciones;
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
