package logica;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Compra implements Serializable{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="TURISTA_NICKNAME")
    private Turista turista;
    @Temporal(TemporalType.DATE)
    private Date fCompra;
    private int cantTuristas;
    private float costoTotal;
    @Temporal(TemporalType.DATE)
    private Date vencimiento;
    @ManyToOne
    @JoinColumn(name="PAQUETE_NOMBRE")
    private Paquete paquete;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Inscripcion> listaInscripcion;  

    public Compra() {
    }

    public Compra(Turista turista, Paquete paquete, int cantTuristas, Date fCompra) {
        this.turista = turista;
        this.fCompra = fCompra;
        this.cantTuristas = cantTuristas;
        this.paquete = paquete;
    }
    
    

    public Long getId() {
        return id;
    }

    public Turista getTurista() {
        return turista;
    }

    public void setTurista(Turista turista) {
        this.turista = turista;
    }

    public Date getfCompra() {
        return fCompra;
    }

    public void setfCompra(Date fCompra) {
        this.fCompra = fCompra;
    }

    public int getCantTuristas() {
        return cantTuristas;
    }

    public void setCantTuristas(int cantTuristas) {
        this.cantTuristas = cantTuristas;
    }

    public float getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(float costoTotal) {
        this.costoTotal = costoTotal;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }
    
    
   
}
