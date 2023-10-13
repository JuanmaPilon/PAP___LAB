package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Actividad;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-12T23:12:13")
=======
<<<<<<< HEAD
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-12T22:36:36")
=======
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-12T22:53:00")
>>>>>>> 28a95b965703917fb9ff13fcc72e2fbfa6b21348
>>>>>>> 4d91fe88c6bccafd55a20560b45a802994e4ed76
@StaticMetamodel(Paquete.class)
public class Paquete_ { 

    public static volatile SingularAttribute<Paquete, String> descripcion;
    public static volatile ListAttribute<Paquete, Actividad> listaActividades;
    public static volatile SingularAttribute<Paquete, Date> fechaAlta;
    public static volatile SingularAttribute<Paquete, Integer> descuento;
    public static volatile SingularAttribute<Paquete, Integer> validez;
    public static volatile SingularAttribute<Paquete, String> nombre;

}