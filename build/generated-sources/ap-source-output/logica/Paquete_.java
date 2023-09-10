package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Actividad;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-09-10T02:11:52")
@StaticMetamodel(Paquete.class)
public class Paquete_ { 

    public static volatile SingularAttribute<Paquete, String> descripcion;
    public static volatile ListAttribute<Paquete, Actividad> listaActividades;
    public static volatile SingularAttribute<Paquete, Integer> descuento;
    public static volatile SingularAttribute<Paquete, Integer> validez;
    public static volatile SingularAttribute<Paquete, String> nombre;

}