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
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-12T23:12:13")
=======
<<<<<<< HEAD
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-12T22:36:36")
=======
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-12T22:53:00")
>>>>>>> 28a95b965703917fb9ff13fcc72e2fbfa6b21348
>>>>>>> 4d91fe88c6bccafd55a20560b45a802994e4ed76
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