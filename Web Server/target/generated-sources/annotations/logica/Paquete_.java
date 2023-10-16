package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Actividad;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-16T12:22:21")
=======
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-15T13:59:11")
>>>>>>> 690019c23508b15d1aa6fb3f2a2b5a8bd60c05d6
@StaticMetamodel(Paquete.class)
public class Paquete_ { 

    public static volatile SingularAttribute<Paquete, String> descripcion;
    public static volatile ListAttribute<Paquete, Actividad> listaActividades;
    public static volatile SingularAttribute<Paquete, Date> fechaAlta;
    public static volatile SingularAttribute<Paquete, Integer> descuento;
    public static volatile SingularAttribute<Paquete, Integer> validez;
    public static volatile SingularAttribute<Paquete, String> nombre;

}