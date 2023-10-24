package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-24T12:15:35")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> apellido;
    public static volatile SingularAttribute<Usuario, String> correo;
    public static volatile SingularAttribute<Usuario, String> nickname;
    public static volatile SingularAttribute<Usuario, Date> fNacimiento;
    public static volatile SingularAttribute<Usuario, String> contrasenia;
    public static volatile SingularAttribute<Usuario, String> nombre;

}