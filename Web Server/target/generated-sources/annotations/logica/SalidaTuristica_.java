package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Actividad;
import logica.Inscripcion;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-10T22:55:18")
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