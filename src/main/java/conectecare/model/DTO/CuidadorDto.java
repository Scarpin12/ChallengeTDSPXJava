package conectecare.model.DTO;

import java.util.List;

public class CuidadorDto {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String senha;
    private boolean disponivel;
    private List<Integer> idsEspecialidades;

    // Construtor vazio
    public CuidadorDto() {
    }

    // Construtor com parâmetros
    public CuidadorDto(String nome, String cpf, String email, String telefone, String senha, boolean disponivel) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.disponivel = disponivel;
    }

    // Getters e Setters
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

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public List<Integer> getIdsEspecialidades() {
        return idsEspecialidades;
    }

    public void setIdsEspecialidades(List<Integer> idsEspecialidades) {
        this.idsEspecialidades = idsEspecialidades;
    }
}