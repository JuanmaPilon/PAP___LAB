package logica;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Actividad implements Serializable {
    @Id
    private String nombre;
    private String descripcion;
    private int duracion;
    private float costo;
    private String ciudad;
    @Temporal(TemporalType.DATE)
    private Date fAlta;
    @OneToMany
    private ArrayList<SalidaTuristica> listaSalidaTuristica;
    @ManyToMany
    private ArrayList<Paquete> listaPaquete;
    @ManyToOne
    private Departamento departamento;
    @ManyToOne
    private Proveedor proveedor; 

    public Actividad() {
    }

    public Actividad(String nombre, String descripcion, int duracion, float costo, String ciudad, Date fAlta, ArrayList<SalidaTuristica> listaSalidaTuristica, ArrayList<Paquete> listaPaquete, Departamento departamento, Proveedor proveedor) {
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

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
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

    public ArrayList<SalidaTuristica> getListaSalidaTuristica() {
        return listaSalidaTuristica;
    }

    public ArrayList<Paquete> getListaPaquete() {
        return listaPaquete;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public void setListaSalidaTuristica(ArrayList<SalidaTuristica> listaSalidaTuristica) {
        this.listaSalidaTuristica = listaSalidaTuristica;
    }

    public void setListaPaquete(ArrayList<Paquete> listaPaquete) {
        this.listaPaquete = listaPaquete;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    


    //COMENTARIO GIT

}
