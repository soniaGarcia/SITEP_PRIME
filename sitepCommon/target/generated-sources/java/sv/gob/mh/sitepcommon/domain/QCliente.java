package sv.gob.mh.sitepcommon.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCliente is a Querydsl query type for Cliente
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCliente extends EntityPathBase<Cliente> {

    private static final long serialVersionUID = -1651223650L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCliente cliente = new QCliente("cliente");

    public final StringPath apellidos = createString("apellidos");

    public final SetPath<Direccion, QDireccion> direccionSet = this.<Direccion, QDireccion>createSet("direccionSet", Direccion.class, QDireccion.class, PathInits.DIRECT2);

    public final StringPath dui = createString("dui");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nit = createString("nit");

    public final StringPath nombres = createString("nombres");

    public final QProfesion profesionId;

    public QCliente(String variable) {
        this(Cliente.class, forVariable(variable), INITS);
    }

    public QCliente(Path<? extends Cliente> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCliente(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCliente(PathMetadata<?> metadata, PathInits inits) {
        this(Cliente.class, metadata, inits);
    }

    public QCliente(Class<? extends Cliente> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profesionId = inits.isInitialized("profesionId") ? new QProfesion(forProperty("profesionId")) : null;
    }

}

