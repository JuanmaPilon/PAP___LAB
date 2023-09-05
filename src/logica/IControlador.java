package logica;

import java.util.ArrayList;
import java.util.Date;

public interface IControlador {
    
public abstract void AltaDeUsuarioTurista(String nickname, String nombre, String apellido, String correo, Date fNacimiento, String nacionalidad);
public abstract void AltaDeUsuarioProveedor(String nickname, String nombre, String apellido, String correo, Date fNacimiento, String descripcion, String link, Departamento depto);//throws UsuarioRepetidoException;// es una prueba
//    public abstract void ConsultaDeUsuario();//Mauri
//    public abstract void ModificarDatosDeUsuario();//Nati
public abstract void AltaDeActividadTuristica(String nombre, String descripcion, int duracion, float costo, String ciudad, Date fAlta, ArrayList<SalidaTuristica> listaSalidaTuristica, ArrayList<Paquete> listaPaquete);//Juanma
//    public abstract void ConsultaActividadTuristica();//Carlos
//    public abstract void AltaDeSalidaTuristica();
//    public abstract void ConsultaDeSalidaTuristica();
//    public abstract void InscripcionDeSalidaTuristica();
//    public abstract void CrearPaqueteDeActividadesTuristicas();
//    public abstract void AgregarActividadTuristicaAPaquete();
//    public abstract void ConsultaDePaqueteDeActividadTuristicas();

   public abstract void AltaDeDepartamento(String nombre, String descripcion, String url);
}
