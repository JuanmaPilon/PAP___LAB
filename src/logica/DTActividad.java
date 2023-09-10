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
    private ArrayList<SalidaTuristica> listaSalidaTuristica;
    private ArrayList<Paquete> listaPaquete;
    private Departamento departamento;
    private Proveedor proveedor;

    // Constructores, getters y setters para DTActividad

    public DTActividad() {
    }

    public DTActividad(String nombre, String descripcion, int duracion, float costo, String ciudad, Date fAlta, ArrayList<SalidaTuristica> listaSalidaTuristica, ArrayList<Paquete> listaPaquete, Departamento departamento, Proveedor proveedor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.costo = costo;
        this.ciudad = ciudad;
        this.fAlta = fAlta;
        this.listaSalidaTuristica = listaSalidaTuristica;
        this.listaPaquete = listaPaquete;
        this.departamento = departamento;
        this.proveedor = proveedor;
    }

    // Getters y setters para todos los atributos

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

    public ArrayList<SalidaTuristica> getListaSalidaTuristica() {
        return listaSalidaTuristica;
    }

    public void setListaSalidaTuristica(ArrayList<SalidaTuristica> listaSalidaTuristica) {
        this.listaSalidaTuristica = listaSalidaTuristica;
    }

    public ArrayList<Paquete> getListaPaquete() {
        return listaPaquete;
    }

    public void setListaPaquete(ArrayList<Paquete> listaPaquete) {
        this.listaPaquete = listaPaquete;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
