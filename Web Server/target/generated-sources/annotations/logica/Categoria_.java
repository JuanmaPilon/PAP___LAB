package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Actividad;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-12T23:12:13")
@StaticMetamodel(Categoria.class)
public class Categoria_ { 

    public static volatile SingularAttribute<Categoria, String> nombre;
    public static volatile ListAttribute<Categoria, Actividad> listaActividad;

}