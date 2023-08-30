package persistencia;

import logica.Compra;
import java.util.Date;

public class PCompra {
    private Date fCompra;
    private int fContTurista;
    private float fCostoTotal;
    private Date vencimiento;
    private PPaquete paquete;
    public PCompra(Date fCompra, int fContTurista, float fCostoTotal, Date vencimiento) {
        this.fCompra = fCompra;
        this.fContTurista = fContTurista;
        this.fCostoTotal = fCostoTotal;
        this.vencimiento = vencimiento;
        this.paquete = paquete;
    }
    public void setPaquete(PPaquete paquete) {
        this.paquete = paquete;
    }
    public PPaquete getPaquete() {
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
