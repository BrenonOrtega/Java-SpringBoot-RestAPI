package zup.orange_talents.dtos.RegistroVacinacao;

import java.util.Date;

public class CadastroRegistroVacinacaoDto {

    //#region Atributos
    public String cpf;
    public String primeiroNome;
    public String sobrenome;
    public String email;
    public Date dataNascimento;
    //#endregion


    //#region Getters and Setters
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    //#endregion

    
}
