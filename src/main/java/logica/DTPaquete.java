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
public class DTPaquete {
   
    private String nombre;
    private String descripcion;
    private int validez;
    private int descuento;
    private Date fechaAlta;
    private ArrayList<String> listaNombreActividades;

    public DTPaquete() {
    }

    public DTPaquete(String nombre, String descripcion, int validez, int descuento, Date fechaAlta) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.validez = validez;
        this.descuento = descuento;
        this.fechaAlta = fechaAlta;
    }

    public DTPaquete(String nombre, String descripcion, int validez, int descuento, Date fechaAlta, ArrayList<String> listaNombreActividades) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.validez = validez;
        this.descuento = descuento;
        this.fechaAlta = fechaAlta;
        this.listaNombreActividades = listaNombreActividades;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getValidez() {
        return validez;
    }

    public int getDescuento() {
        return descuento;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public ArrayList<String> getListaNombreActividades() {
        return listaNombreActividades;
    }
    
    
    
}
