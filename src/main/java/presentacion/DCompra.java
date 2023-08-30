package presentacion;

import logica.Compra;
import java.util.Date;

public class DCompra {
    private Date fCompra;
    private int fContTurista;
    private float fCostoTotal;
    private Date vencimiento;
    private DPaquete paquete;
    public DCompra(Date fCompra, int fContTurista, float fCostoTotal, Date vencimiento) {
        this.fCompra = fCompra;
        this.fContTurista = fContTurista;
        this.fCostoTotal = fCostoTotal;
        this.vencimiento = vencimiento;
        this.paquete = paquete;
    }
    public void setPaquete(DPaquete paquete) {
        this.paquete = paquete;
    }
    public DPaquete getPaquete() {
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
    public Compra parse(){
        return new Compra(getfCompra(),getfContTurista(),getfCostoTotal(),getVencimiento());
    }
}
