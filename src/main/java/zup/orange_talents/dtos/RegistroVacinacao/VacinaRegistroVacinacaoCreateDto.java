package zup.orange_talents.dtos.RegistroVacinacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VacinaRegistroVacinacaoCreateDto {

    @NotNull @NotBlank
    private String nome;

    public String getNome() {return this.nome;}
    public void setNome(String nome) { this.nome = nome ;}
    
}
