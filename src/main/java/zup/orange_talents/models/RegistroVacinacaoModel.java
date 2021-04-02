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
    private Long id;

    @Column(length = 150)
    @Email 
    private String emailVacinado;

    @Column(nullable = false) @CreationTimestamp
    private Timestamp vacinatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private CadastroModel pessoa;    

    @ManyToOne
        private VacinaModel vacina;

    //Constructors
    //Default
    public RegistroVacinacaoModel() {  }

    @Override
    public String toString(){
        return String.format("%1$s - %2$s - %3$s - %4$s", id, emailVacinado, pessoa.getPrimeiroNome(), vacina.getNome());
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
