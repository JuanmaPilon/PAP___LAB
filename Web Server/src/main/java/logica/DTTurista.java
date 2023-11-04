/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.List;


public class DTTurista extends DTUsuario{
    private String nacionalidad;



    public DTTurista(String nickname, String nombre, String apellido, String correo, String fNacimiento, String nacionalidad, String contrasenia){
        super ( nickname,  nombre,  apellido,  correo,  fNacimiento, contrasenia);
        this.nacionalidad = nacionalidad;
    }


    public String getNacionalidad() {
        return nacionalidad;
    }
    
    
    
}
