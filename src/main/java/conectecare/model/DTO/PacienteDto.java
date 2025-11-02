package conectecare.model.DTO;

public class PacienteDto {
    private String nome;
    private String cpf;
    private Integer idade;
    private String email;
    private String telefone;
    private String senha;
    private Integer idPatologia;

    // Construtor vazio
    public PacienteDto() {
    }

    // Construtor com parâmetros
    public PacienteDto(String nome, String cpf, Integer idade, String email, String telefone,String senha, Integer idPatologia) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.idPatologia = idPatologia;
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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
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

    public Integer getIdPatologia() {
        return idPatologia;
    }

    public void setIdPatologia(Integer idPatologia) {
        this.idPatologia = idPatologia;
    }
}