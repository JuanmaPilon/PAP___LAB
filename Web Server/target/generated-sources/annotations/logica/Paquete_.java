package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Actividad;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-11T22:32:35")
=======
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-11T22:09:14")
>>>>>>> 90a5027d919e6d933dcd9778436327f665b96dff
@StaticMetamodel(Paquete.class)
public class Paquete_ { 

    public static volatile SingularAttribute<Paquete, String> descripcion;
    public static volatile ListAttribute<Paquete, Actividad> listaActividades;
    public static volatile SingularAttribute<Paquete, Date> fechaAlta;
    public static volatile SingularAttribute<Paquete, Integer> descuento;
    public static volatile SingularAttribute<Paquete, Integer> validez;
    public static volatile SingularAttribute<Paquete, String> nombre;

}