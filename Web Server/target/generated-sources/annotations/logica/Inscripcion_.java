package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.SalidaTuristica;
import logica.Turista;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-10-07T16:01:47")
@StaticMetamodel(Inscripcion.class)
public class Inscripcion_ { 

    public static volatile SingularAttribute<Inscripcion, Float> costo;
    public static volatile SingularAttribute<Inscripcion, Date> fInscripcion;
    public static volatile SingularAttribute<Inscripcion, Turista> turista;
    public static volatile SingularAttribute<Inscripcion, Long> id;
    public static volatile SingularAttribute<Inscripcion, Integer> cantTurista;
    public static volatile SingularAttribute<Inscripcion, SalidaTuristica> salida;

}