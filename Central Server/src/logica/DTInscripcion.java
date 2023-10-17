/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.Date;

/**
 *
 * @author natil
 */
public class DTInscripcion {
    private Long id;
    private String nicknameTurista;
    private String nombreSalidaTuristica;
    private Date fInscripcion;
    private int cantTurista;
    private float costo;

    public DTInscripcion() {
    }

    public DTInscripcion(String nicknameTurista, String nombreSalidaTuristica, Date fInscripcion, int cantTurista, float costo) {
        this.nicknameTurista = nicknameTurista;
        this.nombreSalidaTuristica = nombreSalidaTuristica;
        this.fInscripcion = fInscripcion;
        this.cantTurista = cantTurista;
        this.costo = costo;
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
