package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Actividad;
import logica.Inscripcion;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-11T15:46:29")
=======
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-11T00:32:12")
>>>>>>> 90ff77df907fc9cf7065f647dad44adb2df387cb
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