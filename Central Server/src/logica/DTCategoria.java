/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;

public class DTCategoria {
    private String nombre;
    private ArrayList<Actividad> listaActividad;

    public DTCategoria() {
    }

    public DTCategoria(String nombre) {
        this.nombre = nombre;
    }

    public DTCategoria(String nombre, ArrayList<Actividad> listaActividad) {
        this.nombre = nombre;
        this.listaActividad = listaActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Actividad> getListaActividad() {
        return listaActividad;
    }

}
