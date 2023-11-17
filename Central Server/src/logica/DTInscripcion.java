/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author natil
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DTInscripcion {
    private Long id;
    private String nicknameTurista;
    private String nombreSalidaTuristica;
    private Date fInscripcion;
    private int cantTurista;
    private float costo;
    private TipoPago tipoPago;

    public DTInscripcion() {
    }

    public DTInscripcion(String nicknameTurista, String nombreSalidaTuristica, Date fInscripcion, int cantTurista, float costo, TipoPago tipoPago) {
        this.nicknameTurista = nicknameTurista;
        this.nombreSalidaTuristica = nombreSalidaTuristica;
        this.fInscripcion = fInscripcion;
        this.cantTurista = cantTurista;
        this.costo = costo;
        this.tipoPago = tipoPago;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Long getId() {
        return id;
    }

    public String getNicknameTurista() {
        return nicknameTurista;
    }

    public String getNombreSalidaTuristica() {
        return nombreSalidaTuristica;
    }

    public Date getfInscripcion() {
        return fInscripcion;
    }

    public int getCantTurista() {
        return cantTurista;
    }

    public float getCosto() {
        return costo;
    }


    
}
