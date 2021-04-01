package zup.orange_talents.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="vacinas")
@Table(schema="tenant_name")
public class VacinaModel {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="vacina_id", updatable = false)
    public Long id;

    @Column(nullable = false, length = 100)
    public String Nome;

    @Column(length = 60)
    public String fabricante;

    @Column(length = 40)
    public String doenca;

    @OneToMany(mappedBy = "vacina")
    public List<RegistroVacinacaoModel> registroVacinacao = new ArrayList<RegistroVacinacaoModel>();

}
