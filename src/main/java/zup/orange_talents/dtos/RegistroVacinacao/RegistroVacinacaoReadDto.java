package zup.orange_talents.dtos.RegistroVacinacao;

import java.sql.Timestamp;

public class RegistroVacinacaoReadDto {

    //#region Atributos
    private long id;
    private Timestamp vacinatedAt;
    private VacinaRegistroVacinacaoDto vacina;
    private CadastroRegistroVacinacaoDto pessoa;
    //#endregion

    //#region Construtores

    public RegistroVacinacaoReadDto() { }

    //#endregion

    //#region Getters e Setters

    public long getId() {return id; }

    public void setId(long id) { this.id = id; }

    public Timestamp getVacinatedAt() {
        return vacinatedAt;
    }

    public void setVacinatedAt(Timestamp vacinatedAt) {
        this.vacinatedAt = vacinatedAt;
    }

    public VacinaRegistroVacinacaoDto getVacina() {
        return vacina;
    }

    public void setVacina(VacinaRegistroVacinacaoDto vacina) {
        this.vacina = vacina;
    }

    public CadastroRegistroVacinacaoDto getPessoa() {
        return pessoa;
    }

    public void setPessoa(CadastroRegistroVacinacaoDto pessoa) {
        this.pessoa = pessoa;
    }

    

    //#endregion

    

    
   

   
}
