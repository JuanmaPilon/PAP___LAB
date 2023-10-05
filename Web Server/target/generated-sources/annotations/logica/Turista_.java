package logica;

import java.util.ArrayList;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Inscripcion;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-10-05T17:34:55")
@StaticMetamodel(Turista.class)
public class Turista_ extends Usuario_ {

    public static volatile SingularAttribute<Turista, ArrayList> listaCompras;
    public static volatile ListAttribute<Turista, Inscripcion> listaInscripcion;
    public static volatile SingularAttribute<Turista, String> nacionalidad;

}