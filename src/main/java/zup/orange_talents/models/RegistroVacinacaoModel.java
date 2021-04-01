package zup.orange_talents.models;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.Email;

import java.sql.Timestamp;

@Entity(name="registro_vacinacao")
@Table(schema="tenant_name")
public class RegistroVacinacaoModel {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="registro_vacina_id", updatable = false)
    public Long id;

    @Column
    @Email
    public String emailVacinado;

    @Column(nullable = false) @CreationTimestamp
    public Timestamp vacinatedAt;

    @ManyToOne
    
    public CadastroModel pessoa;    

    @ManyToOne
    public VacinaModel vacina;

    //Constructors
    //Default
    public RegistroVacinacaoModel() {  }

    //TODO Controller
    public RegistroVacinacaoModel(String email) {

    }


    //Getters and Setters from Here.
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getEmailVacinado() { return emailVacinado; }

    public void setEmailVacinado(String emailVacinado) { this.emailVacinado = emailVacinado; }

    public Timestamp getVacinatedAt() { return vacinatedAt; }

    public CadastroModel getPessoa() {return pessoa;}

    public void setPessoa(CadastroModel pessoa) { this.pessoa = pessoa; }

    public VacinaModel getVacina() { return vacina; }

    public void setVacina(VacinaModel vacina) { this.vacina = vacina; }

}
