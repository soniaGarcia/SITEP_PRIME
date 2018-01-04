package sv.gob.mh.sitepcommon.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMunicipio is a Querydsl query type for Municipio
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMunicipio extends EntityPathBase<Municipio> {

    private static final long serialVersionUID = 169315473L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMunicipio municipio = new QMunicipio("municipio");

    public final StringPath codigo = createString("codigo");

    public final QDepartamento departamentoId;

    public final StringPath descripcion = createString("descripcion");

    public final SetPath<Direccion, QDireccion> direccionSet = this.<Direccion, QDireccion>createSet("direccionSet", Direccion.class, QDireccion.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QMunicipio(String variable) {
        this(Municipio.class, forVariable(variable), INITS);
    }

    public QMunicipio(Path<? extends Municipio> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMunicipio(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMunicipio(PathMetadata<?> metadata, PathInits inits) {
        this(Municipio.class, metadata, inits);
    }

    public QMunicipio(Class<? extends Municipio> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.departamentoId = inits.isInitialized("departamentoId") ? new QDepartamento(forProperty("departamentoId"), inits.get("departamentoId")) : null;
    }

}

