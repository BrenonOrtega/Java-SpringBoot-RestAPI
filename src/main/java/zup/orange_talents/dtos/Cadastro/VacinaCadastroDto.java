package zup.orange_talents.dtos.Cadastro;

public class VacinaCadastroDto {
    private String nome;
    private String doenca;

    public VacinaCadastroDto(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDoenca() {
        return doenca;
    }

    public void setDoenca(String doenca) {
        this.doenca = doenca;
    };
}