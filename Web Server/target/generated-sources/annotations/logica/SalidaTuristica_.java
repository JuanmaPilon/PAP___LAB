package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Actividad;
import logica.Inscripcion;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-17T14:51:12")
=======
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-16T23:25:02")
>>>>>>> 6f7d3a37885ab2614cf2bcf0a7ec85ba3e78bf9b
@StaticMetamodel(SalidaTuristica.class)
public class SalidaTuristica_ { 

    public static volatile SingularAttribute<SalidaTuristica, Date> fAlta;
    public static volatile SingularAttribute<SalidaTuristica, Integer> cantMax;
    public static volatile ListAttribute<SalidaTuristica, Inscripcion> listaInscripciones;
    public static volatile SingularAttribute<SalidaTuristica, String> lugar;
    public static volatile SingularAttribute<SalidaTuristica, Date> fSalida;
    public static volatile SingularAttribute<SalidaTuristica, String> nombre;
    public static volatile SingularAttribute<SalidaTuristica, Actividad> actividad;

}