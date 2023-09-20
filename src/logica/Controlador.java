package logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import persistencia.ControladoraPersistencia;
import persistencia.exceptions.CorreoElectronicoExistenteException;
import persistencia.exceptions.NicknameExistenteException;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

public class Controlador implements IControlador{
    
    public Controlador() {}
    
    private static Controlador control;
    public static Controlador getInstance(){
        if (control == null){
            control = new Controlador();
        }
            return control;
    };
   
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    //descomentado por una prueba:
    @Override
    public void AltaDeUsuarioTurista(String nickname, String nombre, String apellido, String correo, 
                                        Date fNacimiento, String nacionalidad) throws NicknameExistenteException, PreexistingEntityException, CorreoElectronicoExistenteException, Exception{
    
        Turista turista = new Turista(); 
        turista.setNickname(nickname);
        turista.setNombre(nombre);
        turista.setApellido(apellido);
        turista.setCorreo(correo);
        turista.setfNacimiento(fNacimiento);
        turista.setNacionalidad(nacionalidad);
        
        try {
           controlPersis.guardarTurista(turista);
        } catch (NicknameExistenteException ex) {
            throw new NicknameExistenteException("Nickname ya en uso por un usuario");
        } catch (CorreoElectronicoExistenteException e) {
            throw new CorreoElectronicoExistenteException("Correo electrónico ya en uso por un usuario ");
        }
    };
   
    @Override
    public List<Paquete> consultaPaquetes(){
        return controlPersis.consultaPaquete();
    };
   
    @Override
    public void AltaDeUsuarioProveedor(String nickname, String nombre, String apellido, String correo, 
   Date fNacimiento, String descripcion, String link) throws CorreoElectronicoExistenteException, NicknameExistenteException, PreexistingEntityException, Exception {
   Proveedor proveedor = new Proveedor();
   proveedor.setNickname(nickname);
   proveedor.setNombre(nombre);
   proveedor.setApellido(apellido);
   proveedor.setCorreo(correo);
   proveedor.setfNacimiento(fNacimiento);
   proveedor.setDescripcion(descripcion);
   proveedor.setLink(link);
   
   try {
           controlPersis.guardarProveedor(proveedor);
        } catch (NicknameExistenteException ex) {
            throw new NicknameExistenteException("Nickname ya en uso por un usuario");
        } catch (CorreoElectronicoExistenteException e) {
            throw new CorreoElectronicoExistenteException("Correo electrónico ya en uso por un usuario ");
        }
}

    //String nombreProveedor, String nombreDep,
    @Override
    public void guardarActividad(String nombreActividad, String descripcionActividad, int duracionActividad, float costoActividad, String nombreCuidad, Date fecha,String nombreProveedor, String nombreDepartamento) throws PreexistingEntityException, Exception{
        Actividad actividad = new Actividad();
        actividad.setCiudad(nombreCuidad);
        actividad.setNombre(nombreActividad);
        actividad.setDescripcion(descripcionActividad);
        actividad.setDuracion(duracionActividad);
        actividad.setCosto(costoActividad);
        actividad.setfAlta(fecha);
   
        Departamento dep = new Departamento();//creo dep auxiliar
        dep = controlPersis.traerDepartamento(nombreDepartamento);// encuentro el departamento y lo cargo en dep
        // dep.getListaActTur().add(actividad);
        actividad.setDepartamento(controlPersis.traerDepartamento(nombreDepartamento));// hago que mi actividad apunte al departamento que me traje
        //idem con proveedor
        
        try{
        Proveedor pro = new Proveedor();
        pro = controlPersis.traerProveedor(nombreProveedor);
        actividad.setProveedor(pro);
   
        controlPersis.guardarActividad(actividad);
        }catch (PreexistingEntityException e) {
            throw new PreexistingEntityException("Ya existe ua actividad con ese nombre");
        }

   }
   
   @Override
   public void AltaSalidaTuristica(String nombre, int cantMax, Date fAlta, Date fSalida, String lugar, String nombreActividad) throws PreexistingEntityException, Exception{
       SalidaTuristica salidaTuristica = new SalidaTuristica();
        salidaTuristica.setNombre(nombre);
        salidaTuristica.setCantMax(cantMax);
        salidaTuristica.setfAlta(fAlta);
        salidaTuristica.setfSalida(fSalida);
        salidaTuristica.setLugar(lugar);
        
        Actividad actividad = ConsultaActividadTuristica(nombreActividad);
        salidaTuristica.setActividad(actividad);
        actividad.getListaSalidaTuristica().add(salidaTuristica);
        try{
        controlPersis.guardarSalidaTuristica(salidaTuristica, actividad);
        } catch (PreexistingEntityException e) {
            throw new PreexistingEntityException("Ya existe un departamento con ese nombre");
        }
    }
////////////
   @Override
   public ArrayList<String> listaActividades(){//tiene el nombre de los departamentos, no el objeto
       return controlPersis.listaActividades();
   };
   @Override
   public ArrayList<String> listaPaquetes(){//tiene el nombre de los departamentos, no el objeto
       return controlPersis.listaPaquetes();
   };
   ///////
   @Override 
   public ArrayList<String> listaSalActividadTuristica(String actividad){
       
       
       return controlPersis.listaSalActividadTuristica(actividad);
   };
   
   @Override
   public ArrayList<Departamento> listaDepartamentos(){
       return controlPersis.listaDepartamentos();
   };
   @Override
   public ArrayList<String> listaDeptos(){//tiene el nombre de los departamentos, no el objeto
       return controlPersis.listaDeptos();
   };
   @Override
   public ArrayList<String> listaActividadesTuristicas(String departamento){
       return controlPersis.listaActividadesTuristicas(departamento);
   };
   
   @Override
   public ArrayList<String> listaUsuarios(){
     return controlPersis.listaUsuarios();
   };
   
    @Override
   public ArrayList<String> listaProveedores(){
     return controlPersis.listaProveedores();
   };
 
   @Override
   public Usuario ConsultaDeUsuario(String nickname){
       return controlPersis.consultaUsuario(nickname);
   };
   
   @Override
   public Actividad ConsultaActividadTuristica(String nombreActividad){
       return controlPersis.consultaActividad(nombreActividad);
   };
   
   
   @Override
   public void AltaDeActividadTuristica(String nombre, String descripcion, int duracion, float costo, String ciudad, Date fAlta, ArrayList<SalidaTuristica> listaSalidaTuristica, ArrayList<Paquete> listaPaquete){

    Actividad actividad = new Actividad();
       
    //falta control si nombre ya existe o si es null nombre
    actividad.setNombre(nombre);
    actividad.setDescripcion(descripcion);
    actividad.setDuracion(duracion);
    actividad.setCosto(costo);
    actividad.setCiudad(ciudad);
    actividad.setfAlta(fAlta);
    actividad.setListaSalidaTuristica(listaSalidaTuristica);
    actividad.setListaPaquete(listaPaquete);

    //controlPersis.guardarActividad(actividad);

};//Juanma
   
   //Devuelve la Salida Turistica con nombre nombreSalida
   @Override
    public SalidaTuristica ConsultaSalidaTuristica(String nombreSalida){
        return controlPersis.consultaSalida(nombreSalida);
    };
    
    //Alta de Departamento. No requiere GUI.
    @Override
    public void AltaDeDepartamento(String nombre, String descripcion, String url) throws PreexistingEntityException, Exception{
       
       Departamento depto = new Departamento();
       depto.setNombre(nombre);
       depto.setDescripcion(descripcion);
       depto.setUrl(url);
       
       try{
       controlPersis.guardarDepartamento(depto);
       }catch (PreexistingEntityException e) {
            throw new PreexistingEntityException("Ya existe un departamento con ese nombre");
        }
   };
   
   @Override
    public List<String> llenarCmboBoxDep(){
       return controlPersis.llenarCmboBoxDepPersis();
   }
    


@Override 
public void crearPaqueteActividadTuristica(String nombreDePaquete, String descripcionDePaquete, int validezDePaquete, Date altaDePaquete, int descuentoDePaquete) throws PreexistingEntityException, Exception {
    Paquete paquete = new Paquete();
    paquete.setNombre(nombreDePaquete);
    paquete.setDescripcion(descripcionDePaquete);
    paquete.setValidez(validezDePaquete);
    paquete.setDescuento(descuentoDePaquete);
    paquete.setFechaAlta(altaDePaquete);//le seteo fechaAlta al paquete
try{
    controlPersis.guardarPaqueteActividadTuristica(paquete);
} catch (PreexistingEntityException e) {
            throw new PreexistingEntityException("Ya existe un paquete con ese nombre");
        }
}

   //Lista auxiliar de DTUsuarios para mostrar los usuarios (turista y proveevor) registrados en la BD.
   @Override
   public ArrayList<DTUsuario> traerUsuarioMod(){
       return controlPersis.traerUsuarios();
   }
   
   //Lista auxiliar para traer los DT de Turista registrados en la BD.
   @Override
   public ArrayList<DTTurista> traerUsuarioTurista(){
       return controlPersis.traerUsuariosTurista();
   }

   //Devuelve DTTurista del Turista a partir del nickname
   @Override
   public DTTurista traerDTTurista(String nickname){
   
       Turista t = controlPersis.traerTurista(nickname);
       //conversion date a String
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       String fnac = sdf.format(t.getfNacimiento());
       
       return new DTTurista(t.getNickname(), t.getNombre(), t.getApellido(), t.getCorreo(),
               fnac, t.getNacionalidad());
   }
   //Devuelve DTProveedor del Proveedor a partir del nickname
   @Override
   public DTProveedor traerDTProveedor(String nickname){
       Proveedor t = controlPersis.traerProveedor(nickname);
       //conversion date a String
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       String fnac = sdf.format(t.getfNacimiento());
       
       return new DTProveedor(t.getNickname(), t.getNombre(), t.getApellido(), t.getCorreo(),
               fnac, t.getDescripcion(), t.getLink());
   }
   
    //Devuelve el DTSalidaTuristica a partir del nombre de la Salida Turistica
    @Override
    public  DTSalidaTuristica traerDTSalidaTuristica(String nombreSalida){
        SalidaTuristica salida = ConsultaSalidaTuristica(nombreSalida);
        DTSalidaTuristica dtSalida = new DTSalidaTuristica(salida.getNombre(), salida.getCantMax(),
                                            salida.getfAlta(), salida.getfSalida(), salida.getLugar(), salida.getActividad().getNombre());
        
        return dtSalida;
    }
   
    //Persiste la modificacion de Usuario Proveedor. nickname y correo no se modifican. 
   @Override
    public void ModificarDatosDeUsuarioProveedor(String nickname, String nombre, String apellido, String correo, Date fecha, String descripcion, String url) {
        Proveedor p = (Proveedor) ConsultaDeUsuario(nickname);
        
        p.setNombre(nombre);
        p.setApellido(apellido);
        p.setDescripcion(descripcion);
        p.setLink(url);
        p.setfNacimiento(fecha);
        
        controlPersis.modificarProveedor(p);
    }
    
    //Persiste la modificacion de Usuario Turista. nickname y correo no se modifican. 
    @Override
    public void ModificarDatosDeUsuarioTurista(String nickname, String nombre, String apellido, String correo, Date fecha, String nacionalidad) {
        Turista t =  (Turista) ConsultaDeUsuario(nickname);
        
        t.setNombre(nombre);
        t.setApellido(apellido);
        t.setNacionalidad(nacionalidad);
        t.setfNacimiento(fecha);
        
        controlPersis.modificarTurista(t);
    }
    
    //
    @Override
    public List<String> findSalidasTuristicasDepartamento(String departamentoSeleccionado) {
        return controlPersis.findSalidasTuristicasDepartamentoPersis(departamentoSeleccionado);
    }
    
    //Devuelve lista de DT Actividad para un nombre de Departamento dado
    @Override
    public ArrayList<DTActividad> encontraActividadDepartamento(String departamentoSeleccionado) {
        return controlPersis.encontraActividadDepartamentoPersis(departamentoSeleccionado);
    }
    
    //Devuelve lista de DT Salida Turistica para un nombre de Actividad dado
    @Override
    public ArrayList<DTSalidaTuristica> encontraSalidasTuristicasDeActividad(String actividadSeleccionado) {
        return controlPersis.encontraSalidasTuristicasDeActividadPersis(actividadSeleccionado);
    }
   
    //
    @Override
     public void asignarActividadPaquete(String paqueteSeleccionado,String actividadSeleccionada) throws NonexistentEntityException, Exception{
         try{
         controlPersis.asignarActividadPaquetePersis(paqueteSeleccionado, actividadSeleccionada);
         } catch (NonexistentEntityException ex) {
            throw new NonexistentEntityException("El paquete con ese nombre no existe");
        }
     }
  

    
    //Chequea si ya no se supero el limite de Inscripcion para un nombre Salida Turistica. 
    //Devuelve true la Salida Turistica esta llena o se supera el max de la cantidad de turista para la Salida Turistica. 
    //Devuelve false se puede Inscribir la cantidad a Incribir a la Salida Turistica
    @Override
     public boolean salidaTuristicaLlena(String salida, int cantAInscribir){
        
        boolean resultado = true;
        int cantTotal = 0;
        //busco los obj salidaTuristica
        SalidaTuristica salidaTuristica = ConsultaSalidaTuristica(salida);
        //busco las inscripciones para ese obj salidaTuristica
        ArrayList<Inscripcion> inscripcionesSalidaTuristica = controlPersis.listarInscripcionesDeSalidaTuristica(salida);
        //me fijo en las inscripciones la cantidad de inscriptos
        for (Inscripcion i: inscripcionesSalidaTuristica){
           cantTotal=cantTotal + i.getCantTurista();
        }
        //controlo cantidad actual de inscriptos y la cantidad a inscribir no supere el max de la salida turistica
        if((cantTotal+cantAInscribir)<=salidaTuristica.getCantMax()){
            resultado = false;//no llena se puede inscribir
        }

        return resultado;
    }    
    
    //chequea si el turista ya esta inscripto a la Salida Turistica. 
    //Devuelve true si el Turistica esta ya inscripto a la Salida Turistica. 
    //Devuelve false si el Turistica se puede Inscribir a la Salida Turistica
     @Override
    public boolean turistaYaInscriptoSalidaTuristica(String salida, String turistaAlta){
        boolean resultado = false;
        //busco los obj de turista y salidaTuristica
        Turista turista = (Turista) ConsultaDeUsuario(turistaAlta);
        //me fijo en la lista de inscripciones del turista por si ya esta inscripto. obs.: no controlo fechas
        for (Inscripcion insc : turista.getListaInscripcion()){
            if(insc.getSalida().getNombre().equals(salida)){
                return true;
            }        
        }
        
        return resultado;  
    }    
        
    //Alta de Inscripcion. Pre-condicion: exite salida turistica y turista con los respectivos nombres; los parametros vienen en el formato correcto.
    @Override
    public void InscripcionASalidaTuristica(String nombreSalidaSeleccionada, String nicknameTurista, int cantTurista, int costo, Date fecha ) {
        SalidaTuristica salida = ConsultaSalidaTuristica(nombreSalidaSeleccionada);
        Turista turista = (Turista) ConsultaDeUsuario(nicknameTurista);
        Inscripcion inscripcion = new Inscripcion(turista, salida, fecha, cantTurista, costo);
        
        controlPersis.guardarInscripcion(inscripcion);
    }  
    
    //Devuelve una lista de salidas turisticas a la que se inscribio el turista con el nickname de entrada.
    @Override
    public ArrayList<DTSalidaTuristica> traerInscSalidasDeTurista(String nickname){
        Turista t = (Turista) ConsultaDeUsuario(nickname);
        ArrayList<DTSalidaTuristica> listaInscSalidasDeTurista = new ArrayList();
        
        //DTSalidaTuristica(String nombre, int cantMax, Date fAlta, Date fSalida, String lugar) 
        
        for (Inscripcion insc : t.getListaInscripcion()){
            DTSalidaTuristica dtSalida = new DTSalidaTuristica(insc.getSalida().getNombre(), insc.getSalida().getCantMax(), 
                    insc.getSalida().getfAlta(), insc.getSalida().getfSalida(), insc.getSalida().getLugar() );
            
            listaInscSalidasDeTurista.add(dtSalida);
        }
        return listaInscSalidasDeTurista;
    }
    
    
    @Override
    public ArrayList<DTActividad> traerInscActividadesDeTurista(String nickname) {
        Turista t = (Turista) ConsultaDeUsuario(nickname);
        ArrayList<DTActividad> listaInscActividadesDeTurista = new ArrayList();
        ArrayList<Actividad> actividades = new ArrayList();
        
        //DTActividad(String nombre, String descripcion, int duracion, float costo, String ciudad, Date fAlta, String nombreProveedor)
        //armo una lista auxiliar con las actividades de las salidas a la que se inscribio el turista
        for (Inscripcion insc : t.getListaInscripcion()){
            if (!actividades.contains(insc.getSalida().getActividad())){
            actividades.add(insc.getSalida().getActividad());
            
            }   
        }
        //paso la lista de actividades a DT (asi no se repite la actividad, si el turista esta inscpto a dos salidas de la misma actividad.
        
        for(Actividad a : actividades){
                    DTActividad dtActividad = new DTActividad(a.getNombre(), a.getDescripcion(), 
                                                      a.getDuracion(), 
                                                        a.getCosto(), a.getCiudad(),
                                                    a.getfAlta(), 
                                            a.getProveedor().getNombre());
    
            listaInscActividadesDeTurista.add(dtActividad);
        }
        return listaInscActividadesDeTurista;
    }
    
    
    
    //Carga de los Datos de Prueba
    @Override
    public void cargarDatosDePrueba() {
        //Usuarios y Proveedores:
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");

        try {
            AltaDeUsuarioTurista("lachiqui", "Rosa Marıa", "Martınez", "mirtha.legrand.ok@hotmail.com.ar", fecha.parse("23/2/1927"), "argentina");
            AltaDeUsuarioTurista("isabelita", "Elizabeth", "Windsor", "isabelita@thecrown.co.uk", fecha.parse("21/04/1926"), "inglesa");
            AltaDeUsuarioTurista("anibal", "Anıbal", "Lecter", "anibal@fing.edu.uy", fecha.parse("31/12/1937"), "lituana");
            AltaDeUsuarioTurista("waston", "Emma", "Waston", "e.waston@gmail.com", fecha.parse("15/4/1990"), "inglesa");
            AltaDeUsuarioTurista("elelvis", "Elvis", "Lacio", "suavemente@hotmail.com", fecha.parse("30/07/1971"), "estadounidense");
            AltaDeUsuarioTurista("eleven11", "Eleven", "Once", "eleven11@gmail.com", fecha.parse("19/02/2004"), "espanola");
            AltaDeUsuarioTurista("bobesponja", "Bob", "Esponja" ,"bobesponja@nickelodeon.com", fecha.parse("01/05/1999"), "japonesa");
            AltaDeUsuarioTurista("tony", "Antonio", "Pacheco", "eltony@manya.org.uy", fecha.parse("11/04/1976"), "uruguaya");
            AltaDeUsuarioTurista("chino", "Alvaro", "Recoba", "chino@trico.org.uy", fecha.parse("17/03/1976"), "uruguaya");
            AltaDeUsuarioTurista("mastropiero", "Johann Sebastian", "Mastropiero", "johann.sebastian@gmail.com", fecha.parse("07/02/1922"), "austrıaca");
            AltaDeUsuarioProveedor("washington", "Washington", "Rocha", "washington@turismorocha.gub.uy", fecha.parse("14/09/1970"), "Hola! me llamo Washington y soy el encargado del portal de turismo del departamento de Rocha - Uruguay", "http://turismorocha.gub.uy/");
            AltaDeUsuarioProveedor("eldiez", "Pablo", "Bengoechea", "eldiez@socfomturriv.org.uy", fecha.parse("27/06/1965"), "Pablo es el presidente de la Sociedad de Fomento Turıstico de Rivera (conocida como Socfomturriv)", "http://wwww.socfomturriv.org.uy");
            AltaDeUsuarioProveedor("meche" ,"Mercedes", "Venn", "meche@colonia.gub.uy", fecha.parse("31/12/1990"), "Departamento de Turismo del Departamento de Colonia", "https://colonia.gub.uy/turismo/");
    
       } catch (ParseException ex) {
    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
} catch (CorreoElectronicoExistenteException ex) {
    JOptionPane.showMessageDialog(null, "El correo ya está en uso por otro usuario", "Error", JOptionPane.ERROR_MESSAGE);
} catch (NicknameExistenteException ex) {
    JOptionPane.showMessageDialog(null, "El nickname ya está en uso por otro usuario", "Error", JOptionPane.ERROR_MESSAGE);
} catch (PreexistingEntityException ex) {
    // Manejo de la excepción PreexistingEntityException
} catch (Exception ex) {
    JOptionPane.showMessageDialog(null, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}
        
        //Departamentos
        try{
        AltaDeDepartamento("Canelones", "Division Turismo de la Intendencia", "https://www.imcanelones.gub.uy/es");
        AltaDeDepartamento("Maldonado", "Division Turismo de la Intendencia", "https://www.maldonado.gub.uy/");
        AltaDeDepartamento("Rocha", "La Organizacion de Gestion del Destino (OGD) Rocha es un ambito de articulacion publico – privada en el sector turıstico que integran la Corporacion Rochense de Turismo y la Intendencia de Rocha a traves de su Direccion de Turismo.", "www.turismorocha.gub.uy");
        AltaDeDepartamento("Treinta y Tres", "Division Turismo de la Intendencia", "https://treintaytres.gub.uy/");
        AltaDeDepartamento("Cerro Largo", "Division Turismo de la Intendencia", "https://www.gub.uy/intendenciacerro-largo/");
        AltaDeDepartamento("Rivera", "Promociona e implementa proyectos e iniciativas sostenibles de interes turıstico con la participaci´on institucional publica – privada en bien del desarrollo socioecon´omico de la comunidad.", "www.rivera.gub.uy/social/turismo/");
        AltaDeDepartamento("Artigas", "Division Turismo de la Intendencia", "http://www.artigas.gub.uy");
        AltaDeDepartamento("Salto", "Division Turismo de la Intendencia", "https://www.salto.gub.uy");
        AltaDeDepartamento("Paysandu", "Division Turismo de la Intendencia", "https://www.paysandu.gub.uy");
        AltaDeDepartamento("Rıo Negro", "Division Turismo de la Intendencia", "https://www.rionegro.gub.uy");
        AltaDeDepartamento("Soriano", "Division Turismo de la Intendencia", "https://www.soriano.gub.uy");
        AltaDeDepartamento("Colonia", "La propuesta del Departamento de Colonia divide en cuatro actos su espectaculo anual. Cada acto tiene su magia. Desde su naturaleza y playas hasta sus tradiciones y el patrimonio mundial. Todo el a˜no se disfruta.", "https://colonia.gub.uy/turismo/");
        AltaDeDepartamento("San Jose", "Division Turismo de la Intendencia", "https://sanjose.gub.uy");
        AltaDeDepartamento("Flores", "Division Turismo de la Intendencia", "https://flores.gub.uy");
        AltaDeDepartamento("Florida", "Division Turismo de la Intendencia", "http://www.florida.gub.uy");
        AltaDeDepartamento("Lavalleja", "Division Turismo de la Intendencia", "http://www.lavalleja.gub.uy");
        AltaDeDepartamento("Durazno", "Division Turismo de la Intendencia", "https://durazno.uy");
        AltaDeDepartamento("Tacuarembo", "Division Turismo de la Intendencia", "https://tacuarembo.gub.uy");
        AltaDeDepartamento("Montevideo", "Division Turismo de la Intendencia", "https://montevideo.gub.uy/areastematicas/turismo");
        } catch (Exception ex) {
            try {
                throw new Exception("Ha ocurrido un error");
            } catch (Exception ex1) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
 
        //Actividades Turisticas y Salidas Turisticas
        try {
            guardarActividad("Degusta", "Festival gastronomico de productos locales en Rocha", 3, 800, "Rocha", fecha.parse("20/7/2022"), "washington", "Rocha");
            guardarActividad("Teatro con Sabores", "En el mes aniversario del Club Deportivo Uni´on de Rocha te invitamos a una merienda deliciosa.", 3, 500, "Rocha", fecha.parse("21/7/2022"), "washington", "Rocha");
            guardarActividad("Tour por Colonia del Sacramento", "Con guia especializado y en varios idiomas. Varios circuitos posibles.", 2, 400, "Colonia del Sacramento Colonia", fecha.parse("1/8/2022"), "meche", "Colonia");
            guardarActividad("Almuerzo en el Real de San Carlos", "Restaurante en la renovada Plaza de Toros con menu internacional", 2, 800, "Colonia del Sacramento", fecha.parse("1/8/2022"), "meche", "Colonia");
            guardarActividad("Almuerzo en Valle del Lunarejo", "Almuerzo en la Posada con ticket fijo. Menu que incluye bebida y postre casero.", 2, 300, "Tranqueras", fecha.parse("1/8/2022"), "eldiez", "Rivera");
            guardarActividad("Cabalgata en Valle del Lunarejo", "Cabalgata por el ´area protegida. Varios recorridos para elegir.", 2, 150, "Tranqueras", fecha.parse("1/8/2022"), "eldiez", "Rivera"); 
        } catch (PreexistingEntityException ex) {
    JOptionPane.showMessageDialog(null, "El nombre ya está en uso por otra actividad", "Error", JOptionPane.ERROR_MESSAGE);
} catch (Exception ex) {
    JOptionPane.showMessageDialog(null, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}
        
        
        SimpleDateFormat fechahora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        try {
            AltaSalidaTuristica("Degusta Agosto", 20, fecha.parse("21/07/2022"), fechahora.parse("20/08/2022 17:00"), "Sociedad Agropecuaria de Rocha", "Degusta");
            AltaSalidaTuristica("Degusta Setiembre", 20, fecha.parse("22/07/2022"), fechahora.parse("03/09/2022 17:00"), "Sociedad Agropecuaria de Rocha", "Degusta");
            AltaSalidaTuristica("Teatro con Sabores 1", 30, fecha.parse("23/07/2022"), fechahora.parse("04/09/2022 18:00"), "Club Deportivo Union", "Teatro con Sabores");
            AltaSalidaTuristica("Teatro con Sabores 2", 30, fecha.parse("23/07/2022"), fechahora.parse("11/09/2022 18:00"), "Club Deportivo Union", "Teatro con Sabores");
            AltaSalidaTuristica("Tour Colonia del Sacramento 11-09", 5, fecha.parse("05/08/2022"), fechahora.parse("11/09/2022 10:00"), "Encuentro en la base del Faro", "Tour por Colonia del Sacramento");
            AltaSalidaTuristica("Tour Colonia del Sacramento 18-09", 5, fecha.parse("05/08/2022"), fechahora.parse("18/09/2022 10:00"), "Encuentro en la base del Faro", "Tour por Colonia del Sacramento");
            AltaSalidaTuristica("Almuerzo 1", 5, fecha.parse("04/08/2022"), fechahora.parse("18/09/2022 12:00"), "Restaurante de la Plaza de Toros", "Almuerzo en el Real de San Carlos");
            AltaSalidaTuristica("Almuerzo 2", 5, fecha.parse("04/08/2022"), fechahora.parse("25/09/2022 12:00"), "Restaurante de la Plaza de Toros", "Almuerzo en el Real de San Carlos");
            AltaSalidaTuristica("Almuerzo 3", 4, fecha.parse("15/08/2022"), fechahora.parse("10/09/2022 12:00"), "Posada Del Lunarejo", "Almuerzo en Valle del Lunarejo");
            AltaSalidaTuristica("Almuerzo 4", 4, fecha.parse("15/08/2022"), fechahora.parse("11/09/2022 12:00"), "Posada Del Lunarejo", "Almuerzo en Valle del Lunarejo");
            AltaSalidaTuristica("Cabalgata 1", 4, fecha.parse("15/08/2022"), fechahora.parse("10/09/2022 16:00"), "Posada del Lunarejo", "Cabalgata en Valle del Lunarejo");
            AltaSalidaTuristica("Cabalgata 2", 4, fecha.parse("15/08/2022"), fechahora.parse("11/09/2022 16:00"), "Posada del Lunarejo", "Cabalgata en Valle del Lunarejo");
        
         } catch (PreexistingEntityException ex) {
    JOptionPane.showMessageDialog(null, "El nombre ya está en uso por otra salida", "Error", JOptionPane.ERROR_MESSAGE);
} catch (Exception ex) {
    JOptionPane.showMessageDialog(null, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}
        


        //crear paquetes
        try {
            crearPaqueteActividadTuristica("Disfrutar Rocha", "Actividades para hacer en familia y disfrutar arte y gastronomıa", 60, fecha.parse("10/08/2022"), 20);
            crearPaqueteActividadTuristica("Un dıa en Colonia", "Paseos por el casco historico y se puede terminar con Almuerzo en la Plaza de Toros", 45, fecha.parse("01/08/2022"), 15);
       } catch (PreexistingEntityException ex) {
    JOptionPane.showMessageDialog(null, "El nombre ya está en uso por otra actividad", "Error", JOptionPane.ERROR_MESSAGE);
} catch (Exception ex) {
    JOptionPane.showMessageDialog(null, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}


        //asignar Actividad turistica a paquete
        try{
        asignarActividadPaquete("Disfrutar Rocha", "Degusta");
        asignarActividadPaquete("Disfrutar Rocha", "Teatro con Sabores");
        asignarActividadPaquete("Un dıa en Colonia", "Tour por Colonia del Sacramento");
        asignarActividadPaquete("Un dıa en Colonia", "Almuerzo en el Real de San Carlos");
        } catch (NonexistentEntityException ex) {
    JOptionPane.showMessageDialog(null, "El paquete con ese nombre no existe", "Error", JOptionPane.ERROR_MESSAGE);
} catch (Exception ex) {
    JOptionPane.showMessageDialog(null, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}

        try {
            //inscripcion
            InscripcionASalidaTuristica("Degusta Agosto", "lachiqui", 3, 2400, fecha.parse("15/8/2022"));
            InscripcionASalidaTuristica("Degusta Agosto", "elelvis", 5, 4000, fecha.parse("16/8/2022"));
            InscripcionASalidaTuristica("Tour Colonia del Sacramento 18-09", "lachiqui", 3, 1200, fecha.parse("18/8/2022"));
            InscripcionASalidaTuristica("Tour Colonia del Sacramento 18-09", "isabelita", 1, 400, fecha.parse("19/8/2022"));
            InscripcionASalidaTuristica("Almuerzo 2",  "mastropiero", 2, 1600, fecha.parse("19/8/2022"));
            InscripcionASalidaTuristica("Teatro con Sabores 1", "chino", 1, 500, fecha.parse("19/8/2022"));
            InscripcionASalidaTuristica("Teatro con Sabores 2", "chino", 10, 5000, fecha.parse("20/8/2022"));
            InscripcionASalidaTuristica("Teatro con Sabores 2", "bobesponja", 2, 1000, fecha.parse("20/8/2022"));
            InscripcionASalidaTuristica("Teatro con Sabores 2", "anibal", 1, 500, fecha.parse("21/8/2022"));
            InscripcionASalidaTuristica("Degusta Setiembre", "tony", 11, 8800, fecha.parse("21/8/2022"));
        } catch (ParseException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

 






}//fin

