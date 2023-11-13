/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author natil
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DTCompra {
    private Long id;
    private String nicknameTurista;
    private Date fCompra;
    private int cantTuristas;
    private float costoTotal;
    private Date vencimiento;
    private String nombrePaquete;

    public DTCompra(Long id, String nicknameTurista, Date fCompra, int cantTuristas, float costoTotal, Date vencimiento, String nombrePaquete) {
        this.id = id;
        this.nicknameTurista = nicknameTurista;
        this.fCompra = fCompra;
        this.cantTuristas = cantTuristas;
        this.costoTotal = costoTotal;
        this.vencimiento = vencimiento;
        this.nombrePaquete = nombrePaquete;
    }

    public Long getId() {
        return id;
    }

    public String getNicknameTurista() {
        return nicknameTurista;
    }

    public Date getfCompra() {
        return fCompra;
    }

    public int getCantTuristas() {
        return cantTuristas;
    }

    public float getCostoTotal() {
        return costoTotal;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public String getNombrePaquete() {
        return nombrePaquete;
    }
 
    
    

}
