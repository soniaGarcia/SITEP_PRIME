package sv.gob.mh.sitepcommon.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "SITEP_DIRECCION")
@SequenceGenerator(name = "SEQ_DIRECCION", sequenceName = "SEQ_DIRECCION", allocationSize = 1)
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion implements BaseEntity<Long> {

    private static final long serialVersionUID =  -4866348215606008846L; 

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DIRECCION")
    @Column(nullable = false)
    Long id;

    @Column(length = 200)
    @Size(max = 200)
    String colonia;
 
    @Column(length = 200)
    @Size(max = 200)
    String calle;
 
    @Column(length = 200)
    @Size(max = 200)
    String casa;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumns({
        @JoinColumn(name="PAIS_ID", referencedColumnName="ID", nullable = false)
    })
    Pais paisId;
 
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumns({
        @JoinColumn(name="DEPARTAMENTO_ID", referencedColumnName="ID", nullable = false)
    })
    Departamento departamentoId;
 
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumns({
        @JoinColumn(name="MUNICIPIO_ID", referencedColumnName="ID", nullable = false)
    })
    Municipio municipioId;
 
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumns({
        @JoinColumn(name="CLIENTE_ID", referencedColumnName="ID", nullable = false)
    })
    Cliente clienteId;

} 