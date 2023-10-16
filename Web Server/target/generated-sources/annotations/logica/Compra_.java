package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Inscripcion;
import logica.Paquete;
import logica.Turista;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-16T12:22:21")
=======
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-15T13:59:11")
>>>>>>> 690019c23508b15d1aa6fb3f2a2b5a8bd60c05d6
@StaticMetamodel(Compra.class)
public class Compra_ { 

    public static volatile SingularAttribute<Compra, Date> vencimiento;
    public static volatile SingularAttribute<Compra, Integer> cantTuristas;
    public static volatile SingularAttribute<Compra, Float> costoTotal;
    public static volatile SingularAttribute<Compra, Turista> turista;
    public static volatile ListAttribute<Compra, Inscripcion> listaInscripcion;
    public static volatile SingularAttribute<Compra, Long> id;
    public static volatile SingularAttribute<Compra, Date> fCompra;
    public static volatile SingularAttribute<Compra, Paquete> paquete;

}