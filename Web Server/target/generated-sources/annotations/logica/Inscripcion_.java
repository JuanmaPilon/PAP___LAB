package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.SalidaTuristica;
import logica.TipoPago;
import logica.Turista;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-11-08T20:23:32")
@StaticMetamodel(Inscripcion.class)
public class Inscripcion_ { 

    public static volatile SingularAttribute<Inscripcion, Float> costo;
    public static volatile SingularAttribute<Inscripcion, Date> fInscripcion;
    public static volatile SingularAttribute<Inscripcion, Turista> turista;
    public static volatile SingularAttribute<Inscripcion, TipoPago> tipoPago;
    public static volatile SingularAttribute<Inscripcion, Long> id;
    public static volatile SingularAttribute<Inscripcion, Integer> cantTurista;
    public static volatile SingularAttribute<Inscripcion, SalidaTuristica> salida;

}