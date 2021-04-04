package zup.orange_talents.dtos.RegistroVacinacao;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.br.CPF;

public class RegistroVacinacaoCreateDto {

    @NotNull @Email
    private String emailVacinado;

    @NotNull @NotBlank
    private String nomeVacina;

    @NotNull @PastOrPresent
    private Date vacinatedAt;

    @CPF 
    private String cpf;

    //#region Getters and Setters
    public String getEmailVacinado() { return emailVacinado; }
    public void setEmailVacinado(String emailVacinado) { this.emailVacinado = emailVacinado; }

    public String getNomeVacina() { return this.nomeVacina; } 
    public void setNomeVacina(String nomeVacina) { this.nomeVacina = nomeVacina; }

    public Date getVacinatedAt() { return vacinatedAt; } 
    public void setVacinatedAt(Date vacinatedAt) { this.vacinatedAt = vacinatedAt; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    //#endregion
}
