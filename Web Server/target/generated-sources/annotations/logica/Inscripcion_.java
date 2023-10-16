package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.SalidaTuristica;
import logica.Turista;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-16T12:22:21")
=======
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-15T13:59:11")
>>>>>>> 690019c23508b15d1aa6fb3f2a2b5a8bd60c05d6
@StaticMetamodel(Inscripcion.class)
public class Inscripcion_ { 

    public static volatile SingularAttribute<Inscripcion, Float> costo;
    public static volatile SingularAttribute<Inscripcion, Date> fInscripcion;
    public static volatile SingularAttribute<Inscripcion, Turista> turista;
    public static volatile SingularAttribute<Inscripcion, Long> id;
    public static volatile SingularAttribute<Inscripcion, Integer> cantTurista;
    public static volatile SingularAttribute<Inscripcion, SalidaTuristica> salida;

}