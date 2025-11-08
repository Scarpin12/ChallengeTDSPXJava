package conectecare.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PacienteDto {
    private String nome;
    private String cpfPaciente;
    private Integer idade;
    private String email;
    private String telefoneContato;
    private String senha;
    private Boolean aceitarTermo;
    private Integer idPatologia;


    public PacienteDto() {
    }

    public PacienteDto(String nome, String cpfPaciente, Integer idade, String email, String telefoneContato,String senha, boolean aceitarTermo, Integer idPatologia, String cep, String logradouro, String cidade, String estado, String complemento, String bairro, String localidade) {
//        super(cep, logradouro, cidade, estado, complemento, bairro, localidade);
        this.nome = nome;
        this.cpfPaciente = cpfPaciente;
        this.idade = idade;
        this.email = email;
        this.telefoneContato = telefoneContato;
        this.senha = senha;
        this.aceitarTermo = aceitarTermo;
        this.idPatologia = idPatologia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfPaciente() { // C maiúsculo
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) { // C maiúsculo e removido o 'f'
        this.cpfPaciente = cpfPaciente;
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

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefone) {
        this.telefoneContato = telefoneContato;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAceitarTermo() {
        return aceitarTermo;
    }

    public void setAceitarTermo(Boolean aceitarTermo) {
        this.aceitarTermo = aceitarTermo;
    }

    public Integer getIdPatologia() {
        return idPatologia;
    }

    public void setIdPatologia(Integer idPatologia) {
        this.idPatologia = idPatologia;
    }
}