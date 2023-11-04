package logica;


import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    private TipoEstado estado;
    @Temporal(TemporalType.DATE)
    private Date fAlta;
    @OneToMany(mappedBy="actividad")
    private List<SalidaTuristica> listaSalidaTuristica;
    @ManyToMany
    private List<Paquete> listaPaquete;
    @ManyToOne
    @JoinColumn(name="DEPARTAMENTO_NOMBRE")
    private Departamento departamento;
    @ManyToOne
    @JoinColumn(name="PROVEEDOR_NICKNAME")
    private Proveedor proveedor; 
    @ManyToMany
    private List<Categoria> listaCategoria;

    public Actividad() {
    }
//borrar luego de agregar estado
    public Actividad(String nombre, String descripcion, int duracion, float costo, String ciudad, Date fAlta, List<SalidaTuristica> listaSalidaTuristica, List<Paquete> listaPaquete, Departamento departamento, Proveedor proveedor) {
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

    public Actividad(String nombre, String descripcion, int duracion, float costo, String ciudad, TipoEstado estado, Date fAlta, List<SalidaTuristica> listaSalidaTuristica, List<Paquete> listaPaquete, Departamento departamento, Proveedor proveedor, List<Categoria> listaCategoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.costo = costo;
        this.ciudad = ciudad;
        this.estado = estado;
        this.fAlta = fAlta;
        this.listaSalidaTuristica = listaSalidaTuristica;
        this.listaPaquete = listaPaquete;
        this.departamento = departamento;
        this.proveedor = proveedor;
        this.listaCategoria = listaCategoria;
    }

    public TipoEstado getEstado() {
        return estado;
    }

    public void setEstado(TipoEstado estado) {
        this.estado = estado;
    }

    public List<Categoria> getListaCategoria() {
        return listaCategoria;
    }

    public void setListaCategoria(List<Categoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
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

    public List<SalidaTuristica> getListaSalidaTuristica() {
        return listaSalidaTuristica;
    }

    public List<Paquete> getListaPaquete() {
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

    public void setListaSalidaTuristica(List<SalidaTuristica> listaSalidaTuristica) {
        this.listaSalidaTuristica = listaSalidaTuristica;
    }

    public void setListaPaquete(List<Paquete> listaPaquete) {
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
