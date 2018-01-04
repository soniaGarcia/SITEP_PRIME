package sv.gob.mh.sitepcommon.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPais is a Querydsl query type for Pais
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPais extends EntityPathBase<Pais> {

    private static final long serialVersionUID = 828577719L;

    public static final QPais pais = new QPais("pais");

    public final StringPath codigo = createString("codigo");

    public final SetPath<Departamento, QDepartamento> departamentoSet = this.<Departamento, QDepartamento>createSet("departamentoSet", Departamento.class, QDepartamento.class, PathInits.DIRECT2);

    public final StringPath descripcion = createString("descripcion");

    public final SetPath<Direccion, QDireccion> direccionSet = this.<Direccion, QDireccion>createSet("direccionSet", Direccion.class, QDireccion.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath moneda = createString("moneda");

    public QPais(String variable) {
        super(Pais.class, forVariable(variable));
    }

    public QPais(Path<? extends Pais> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPais(PathMetadata<?> metadata) {
        super(Pais.class, metadata);
    }

}

