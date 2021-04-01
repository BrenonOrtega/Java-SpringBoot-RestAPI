package zup.orange_talents.dtos.Cadastro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CadastroReadDto {
    //#region Atributos
    public Long id;
    public String cpf;
    public String nome;
    public String email;
    public Date dataNascimento;
    public List<Object> registroVacinacoes = new ArrayList<>();
    
    
    //#endregion

    //#region Construtor
    public CadastroReadDto(){
       
    }
    //#endregion

    //#region Getters and Setters
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
    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }    
    //#endregion
    
}
