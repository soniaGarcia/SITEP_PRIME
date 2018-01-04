package sv.gob.mh.sitepcommon.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDepartamento is a Querydsl query type for Departamento
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDepartamento extends EntityPathBase<Departamento> {

    private static final long serialVersionUID = 1611089120L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDepartamento departamento = new QDepartamento("departamento");

    public final StringPath codigo = createString("codigo");

    public final StringPath descripcion = createString("descripcion");

    public final SetPath<Direccion, QDireccion> direccionSet = this.<Direccion, QDireccion>createSet("direccionSet", Direccion.class, QDireccion.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<Municipio, QMunicipio> municipioSet = this.<Municipio, QMunicipio>createSet("municipioSet", Municipio.class, QMunicipio.class, PathInits.DIRECT2);

    public final QPais paisId;

    public QDepartamento(String variable) {
        this(Departamento.class, forVariable(variable), INITS);
    }

    public QDepartamento(Path<? extends Departamento> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDepartamento(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDepartamento(PathMetadata<?> metadata, PathInits inits) {
        this(Departamento.class, metadata, inits);
    }

    public QDepartamento(Class<? extends Departamento> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.paisId = inits.isInitialized("paisId") ? new QPais(forProperty("paisId")) : null;
    }

}

