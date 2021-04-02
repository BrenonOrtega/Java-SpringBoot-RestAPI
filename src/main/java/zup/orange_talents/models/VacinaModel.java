package zup.orange_talents.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="vacinas")
@Table(schema="tenant_name")
public class VacinaModel {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="vacina_id", updatable = false)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 60)
    private String fabricante;

    @Column(length = 150, nullable = false)
    private String doenca;

    @OneToMany(mappedBy = "vacina", fetch = FetchType.LAZY)
    private List<RegistroVacinacaoModel> registroVacinacao = new ArrayList<RegistroVacinacaoModel>();

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getDoenca() {
        return doenca;
    }

    public void setDoenca(String doenca) {
        this.doenca = doenca;
    }

    public List<RegistroVacinacaoModel> getRegistroVacinacao() {
        return registroVacinacao;
    }

    public void setRegistroVacinacao(List<RegistroVacinacaoModel> registroVacinacao) {
        this.registroVacinacao = registroVacinacao;
    }

}
