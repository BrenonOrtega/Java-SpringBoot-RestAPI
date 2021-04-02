package zup.orange_talents.dtos.RegistroVacinacao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

public class RegistroVacinacaoCreateDto {

    @NotNull @Email
    private String emailVacinado;
    
    @NotNull @CPF
    private String cpfVacinado;

    @NotNull @NotBlank
    private String nomeVacina;

    public String getEmailVacinado() {
        return emailVacinado;
    }

    public void setEmailVacinado(String emailVacinado) {
        this.emailVacinado = emailVacinado;
    }

    public String getCpfVacinado() {
        return cpfVacinado;
    }

    public void setCpfVacinado(String cpfVacinado) {
        this.cpfVacinado = cpfVacinado;
    }

    public String getNomeVacina() {
        return nomeVacina;
    }

    public void setNomeVacina(String nomeVacina) {
        this.nomeVacina = nomeVacina;
    }


}
