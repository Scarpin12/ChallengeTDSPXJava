package conectecare.model.DTO;

import java.util.List;

public class CuidadorDto {
    private String nome;
    private String cpf;
    private String email;
    private int idade;
    private String telefone;
    private String senha;
    private String correlacaoPaciente;
    private String  cpfPaciente;

    public CuidadorDto() {
    }

    public CuidadorDto(String nome, String cpf, String email, int idade, String telefone, String senha, String correlacaoPaciente, String cpfPaciente) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.idade = idade;
        this.telefone = telefone;
        this.senha = senha;
        this.correlacaoPaciente = correlacaoPaciente;
        this.cpfPaciente = cpfPaciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCorrelacaoPaciente() {
        return correlacaoPaciente;
    }

    public void setCorrelacaoPaciente(String correlacaoPaciente) {
        this.correlacaoPaciente = correlacaoPaciente;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }
}
