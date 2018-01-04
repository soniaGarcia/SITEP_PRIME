package sv.gob.mh.sitepcommon.domain;

import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import sv.gob.mh.sitepcommon.domain.util.BaseEntity;

@Entity
@Table(name = "SITEP_MUNICIPIO")
@SequenceGenerator(name = "SEQ_MUNICIPIO", sequenceName = "SEQ_MUNICIPIO", allocationSize = 1)
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Municipio implements BaseEntity<Long> {

    private static final long serialVersionUID =  -6154811934855269755L; 

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MUNICIPIO")
    @Column(nullable = false)
    Long id;

    @Column(length = 20, nullable = false)
    @Size(min = 1, max = 20)
    @Basic(optional = false)
    @NotNull
    String codigo;
 
    @Column(length = 200, nullable = false)
    @Size(min = 1, max = 200)
    @Basic(optional = false)
    @NotNull
    String descripcion;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumns({
        @JoinColumn(name="DEPARTAMENTO_ID", referencedColumnName="ID", nullable = false)
    })
    Departamento departamentoId;
 
    @OneToMany
    @JoinColumns({
        @JoinColumn(name = "MUNICIPIO_ID", referencedColumnName="ID")
    })
    Set<Direccion> direccionSet;

} 