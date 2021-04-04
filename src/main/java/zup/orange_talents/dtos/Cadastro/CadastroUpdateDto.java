package zup.orange_talents.dtos.Cadastro;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.lang.Nullable;

public class CadastroUpdateDto {
    //#region Declaração de Atributos
    @NotNull @Email 
    private String email;

    @Past @Nullable
    private Date dataNascimento;

    @NotNull
    private String primeiroNome;

    @NotNull 
    private String sobrenome;
    //#endregion

    //#region Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
   
    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) {    this.dataNascimento = dataNascimento; }

    public String getPrimeiroNome() { return primeiroNome; }
    public void setPrimeiroNome(String primeiroNome) { this.primeiroNome = primeiroNome; }

    public String getSobrenome() { return sobrenome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }
    
}
