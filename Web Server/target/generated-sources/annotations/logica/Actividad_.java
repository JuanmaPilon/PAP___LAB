package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Categoria;
import logica.Departamento;
import logica.Paquete;
import logica.Proveedor;
import logica.SalidaTuristica;
import logica.TipoEstado;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-16T12:22:21")
=======
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-15T13:59:11")
>>>>>>> 690019c23508b15d1aa6fb3f2a2b5a8bd60c05d6
@StaticMetamodel(Actividad.class)
public class Actividad_ { 

    public static volatile SingularAttribute<Actividad, String> descripcion;
    public static volatile SingularAttribute<Actividad, TipoEstado> estado;
    public static volatile SingularAttribute<Actividad, Date> fAlta;
    public static volatile SingularAttribute<Actividad, Float> costo;
    public static volatile SingularAttribute<Actividad, String> ciudad;
    public static volatile ListAttribute<Actividad, Paquete> listaPaquete;
    public static volatile ListAttribute<Actividad, SalidaTuristica> listaSalidaTuristica;
    public static volatile ListAttribute<Actividad, Categoria> listaCategoria;
    public static volatile SingularAttribute<Actividad, Integer> duracion;
    public static volatile SingularAttribute<Actividad, Departamento> departamento;
    public static volatile SingularAttribute<Actividad, Proveedor> proveedor;
    public static volatile SingularAttribute<Actividad, String> nombre;

}