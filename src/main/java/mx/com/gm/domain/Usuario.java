package mx.com.gm.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsurio;

    @Column(name = "username")
    @NotEmpty
    private String nombreUsuario;

    @Column(name = "password")
    @NotEmpty
    private String contrasena;
    
    @OneToMany
    @JoinColumn(name = "id_usuario")
    private List<Rol> roles;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    private Persona persona;

}
