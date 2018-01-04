package sv.gob.mh.sitepcommon.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProfesion is a Querydsl query type for Profesion
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProfesion extends EntityPathBase<Profesion> {

    private static final long serialVersionUID = -1087789691L;

    public static final QProfesion profesion = new QProfesion("profesion");

    public final SetPath<Cliente, QCliente> clienteSet = this.<Cliente, QCliente>createSet("clienteSet", Cliente.class, QCliente.class, PathInits.DIRECT2);

    public final StringPath codigo = createString("codigo");

    public final StringPath descripcion = createString("descripcion");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QProfesion(String variable) {
        super(Profesion.class, forVariable(variable));
    }

    public QProfesion(Path<? extends Profesion> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProfesion(PathMetadata<?> metadata) {
        super(Profesion.class, metadata);
    }

}

