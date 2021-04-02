package zup.orange_talents.dtos.Cadastro;

import java.util.Date;

public class RegistroVacinacaoCadastroDto {
    private Long id;
    private Date vacinatedAt;
    private VacinaCadastroDto vacina;

    public RegistroVacinacaoCadastroDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getVacinatedAt() {
        return vacinatedAt;
    }

    public void setVacinatedAt(Date vacinatedAt) {
        this.vacinatedAt = vacinatedAt;
    }

    public VacinaCadastroDto getVacina() {
        return vacina;
    }

    public void setVacina(VacinaCadastroDto vacina) {
        this.vacina = vacina;
    }
}
