package logica;

import presentacion.DCompra;
import java.util.Date;

public class Compra {
    private Date fCompra;
    private int fContTurista;
    private float fCostoTotal;
    private Date vencimiento;
    private Paquete paquete;
    public Compra(Date fCompra, int fContTurista, float fCostoTotal, Date vencimiento) {
        this.fCompra = fCompra;
        this.fContTurista = fContTurista;
        this.fCostoTotal = fCostoTotal;
        this.vencimiento = vencimiento;
        this.paquete = paquete;
    }
    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }
    public Paquete getPaquete() {
        return paquete;
    }
    public Date getfCompra() {
        return fCompra;
    }
    public int getfContTurista() {
        return fContTurista;
    }
    public float getfCostoTotal() {
        return fCostoTotal;
    }
    public Date getVencimiento() {
        return vencimiento;
    }
    public void setfCompra(Date fCompra) {
        this.fCompra = fCompra;
    }
    public void setfContTurista(int fContTurista) {
        this.fContTurista = fContTurista;
    }
    public void setfCostoTotal(float fCostoTotal) {
        this.fCostoTotal = fCostoTotal;
    }
    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }
    public DCompra parse(){
        return new DCompra(getfCompra(),getfContTurista(),getfCostoTotal(),getVencimiento());
    }
}
