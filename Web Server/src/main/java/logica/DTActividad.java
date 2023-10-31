/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;
import java.util.Date;

public class DTActividad {
    private String nombre;
    private String descripcion;
    private int duracion;
    private float costo;
    private String ciudad;
    private Date fAlta;
    private ArrayList<String> listaNombresSalidaTuristica; 
    private ArrayList<String> listaNombresPaquete; 
    private String nombreDepartamento;
    private String nombreProveedor;
    private ArrayList<String> listaNombresCategoria; 
    
     public DTActividad(String nombre, String descripcion, int duracion, float costo, String ciudad, Date fAlta, String nombreProveedor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.costo = costo;
        this.ciudad = ciudad;
        this.fAlta = fAlta;
        this.nombreProveedor = nombreProveedor;
    }
     
    public DTActividad(String nombre, String descripcion, int duracion, float costo, String ciudad, Date fAlta, ArrayList<String> listaNombresSalidaTuristica, ArrayList<String> listaNombresPaquete, String nombreDepartamento, String nombreProveedor, ArrayList<String> listaNombresCategoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.costo = costo;
        this.ciudad = ciudad;
        this.fAlta = fAlta;
        this.listaNombresSalidaTuristica = listaNombresSalidaTuristica;
        this.listaNombresPaquete = listaNombresPaquete;
        this.nombreDepartamento = nombreDepartamento;
        this.nombreProveedor = nombreProveedor;
        this.listaNombresCategoria = listaNombresCategoria;
    }

    public DTActividad() {
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getfAlta() {
        return fAlta;
    }

    public void setfAlta(Date fAlta) {
        this.fAlta = fAlta;
    }

    public ArrayList<String> getNombresSalidaTuristica() {
        return listaNombresSalidaTuristica;
    }

    public void setListaNombresSalidaTuristica(ArrayList<String> listaNombresSalidaTuristica) {
        this.listaNombresSalidaTuristica = listaNombresSalidaTuristica;
    }

    public ArrayList<String> getNombresPaquete() {
        return listaNombresPaquete;
    }

    public void setListaNombresPaquete(ArrayList<String> listaNombresPaquete) {
        this.listaNombresPaquete = listaNombresPaquete;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public ArrayList<String> getNombresCategoria() {
        return listaNombresCategoria;
    }

    public void setListaNombresCategoria(ArrayList<String> listaNombresCategoria) {
        this.listaNombresCategoria = listaNombresCategoria;
    }


    
}

