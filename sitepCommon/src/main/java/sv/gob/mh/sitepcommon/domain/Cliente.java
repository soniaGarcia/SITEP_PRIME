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
@Table(name = "SITEP_CLIENTE")
@SequenceGenerator(name = "SEQ_CLIENTE", sequenceName = "SEQ_CLIENTE", allocationSize = 1)
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente implements BaseEntity<Long> {

    private static final long serialVersionUID =  -4731601005334612989L; 

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIENTE")
    @Column(nullable = false)
    Long id;

    @Column(length = 200, nullable = false)
    @Size(min = 1, max = 200)
    @Basic(optional = false)
    @NotNull
    String nombres;
 
    @Column(length = 200, nullable = false)
    @Size(min = 1, max = 200)
    @Basic(optional = false)
    @NotNull
    String apellidos;
 
    @Column(length = 10)
    @Size(max = 10)
    String dui;
 
    @Column(length = 14)
    @Size(max = 14)
    String nit;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumns({
        @JoinColumn(name="PROFESION_ID", referencedColumnName="ID", nullable = false)
    })
    Profesion profesionId;
 
    @OneToMany
    @JoinColumns({
        @JoinColumn(name = "CLIENTE_ID", referencedColumnName="ID")
    })
    Set<Direccion> direccionSet;

} 