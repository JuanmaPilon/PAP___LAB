package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Compra;
import logica.Inscripcion;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-14T11:19:46")
@StaticMetamodel(Turista.class)
public class Turista_ extends Usuario_ {

    public static volatile ListAttribute<Turista, Compra> listaCompras;
    public static volatile ListAttribute<Turista, Inscripcion> listaInscripcion;
    public static volatile SingularAttribute<Turista, String> nacionalidad;

}