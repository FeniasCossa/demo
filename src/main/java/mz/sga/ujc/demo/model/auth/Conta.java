package mz.sga.ujc.demo.model.auth;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Fenias Cossa
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "conta")
public class Conta {


    @Size(max = 9, min = 9, message = "O campo nuit so deve ter {max} digitos")
    @Id
    private String nuit;

    private Integer codigo;

    private String senha;

    @Column(unique = true)
    @Size(min = 11, max = 70, message="O email deve ter entre {min}  e {max} caracteres")
    @Email(message = "insira um email valido")
    private String email;

    @Column(name = "telefone")
    @Size(max = 9, min = 9, message = "O campo telefone só deve ter {max} digitos")
    private String telefone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    @CreationTimestamp
    @Column(name = "created_at",columnDefinition = "datetime")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",columnDefinition = "datetime")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedAt;
}
