package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Actividad;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-09-09T03:26:25")
@StaticMetamodel(Proveedor.class)
public class Proveedor_ extends Usuario_ {

    public static volatile SingularAttribute<Proveedor, String> descripcion;
    public static volatile ListAttribute<Proveedor, Actividad> listaActividades;
    public static volatile SingularAttribute<Proveedor, String> link;

}