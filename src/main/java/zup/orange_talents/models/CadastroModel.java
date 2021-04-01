package zup.orange_talents.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity(name = "cadastros")
@Table(schema="tenant_name")
public class CadastroModel {

    //#region Atributos
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="cadastro_id", updatable = false)
    private Long id;

    @CreationTimestamp
    private Timestamp createdAt;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    
    @Column(nullable = false, unique = true, length=13)
    private String cpf;

    @OneToMany(mappedBy = "pessoa") @Column(name="registro_vacina_id")
    private List<RegistroVacinacaoModel> registroVacinacoes = new ArrayList<RegistroVacinacaoModel>();

    @Column(nullable = false)
    private Date dataNascimento;

    @Column(length = 30, nullable = false)
    private String primeiroNome;

    @Column(length = 50, nullable = false)
    private String sobrenome;

    @Column(nullable = false)
    private Boolean isActive = true;

    //#endregion 

    //#region getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<RegistroVacinacaoModel> getRegistroVacinacoes() {
        return registroVacinacoes;
    }

    public void setRegistroVacinacoes(List<RegistroVacinacaoModel> registroVacinacoes) {
        this.registroVacinacoes = registroVacinacoes;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    //#endregion

    
}
