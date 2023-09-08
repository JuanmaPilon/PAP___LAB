package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Departamento;
import logica.Paquete;
import logica.SalidaTuristica;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-09-07T22:40:34")
@StaticMetamodel(Actividad.class)
public class Actividad_ { 

    public static volatile SingularAttribute<Actividad, String> descripcion;
    public static volatile SingularAttribute<Actividad, Date> fAlta;
    public static volatile SingularAttribute<Actividad, Float> costo;
    public static volatile SingularAttribute<Actividad, String> ciudad;
    public static volatile ListAttribute<Actividad, Paquete> listaPaquete;
    public static volatile ListAttribute<Actividad, SalidaTuristica> listaSalidaTuristica;
    public static volatile SingularAttribute<Actividad, Integer> duracion;
    public static volatile SingularAttribute<Actividad, Departamento> departamento;
    public static volatile SingularAttribute<Actividad, String> nombre;

}