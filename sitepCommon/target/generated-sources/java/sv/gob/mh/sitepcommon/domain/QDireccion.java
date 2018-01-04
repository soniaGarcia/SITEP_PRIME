package sv.gob.mh.sitepcommon.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDireccion is a Querydsl query type for Direccion
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDireccion extends EntityPathBase<Direccion> {

    private static final long serialVersionUID = -1042005548L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDireccion direccion = new QDireccion("direccion");

    public final StringPath calle = createString("calle");

    public final StringPath casa = createString("casa");

    public final QCliente clienteId;

    public final StringPath colonia = createString("colonia");

    public final QDepartamento departamentoId;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMunicipio municipioId;

    public final QPais paisId;

    public QDireccion(String variable) {
        this(Direccion.class, forVariable(variable), INITS);
    }

    public QDireccion(Path<? extends Direccion> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDireccion(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDireccion(PathMetadata<?> metadata, PathInits inits) {
        this(Direccion.class, metadata, inits);
    }

    public QDireccion(Class<? extends Direccion> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.clienteId = inits.isInitialized("clienteId") ? new QCliente(forProperty("clienteId"), inits.get("clienteId")) : null;
        this.departamentoId = inits.isInitialized("departamentoId") ? new QDepartamento(forProperty("departamentoId"), inits.get("departamentoId")) : null;
        this.municipioId = inits.isInitialized("municipioId") ? new QMunicipio(forProperty("municipioId"), inits.get("municipioId")) : null;
        this.paisId = inits.isInitialized("paisId") ? new QPais(forProperty("paisId")) : null;
    }

}

