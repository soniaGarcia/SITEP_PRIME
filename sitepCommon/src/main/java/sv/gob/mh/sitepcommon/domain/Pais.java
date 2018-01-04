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
import sv.gob.mh.sitepcommon.domain.util.BaseEntity;

@Entity
@Table(name = "SITEP_PAIS")
@SequenceGenerator(name = "SEQ_PAIS", sequenceName = "SEQ_PAIS", allocationSize = 1)
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pais implements BaseEntity<Long> {

    private static final long serialVersionUID =  -5840176249286592627L; 

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PAIS")
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
 
    @Column(length = 50)
    @Size(max = 50)
    String moneda;

    @OneToMany
    @JoinColumns({
        @JoinColumn(name = "PAIS_ID", referencedColumnName="ID")
    })
    Set<Departamento> departamentoSet;
 
    @OneToMany
    @JoinColumns({
        @JoinColumn(name = "PAIS_ID", referencedColumnName="ID")
    })
    Set<Direccion> direccionSet;

} 