package conectecare.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import conectecare.model.Entity.Cuidador;

public class CuidadorDto  {
    private String nome;
    private String cpfCuidador;
    private String cpfPaciente;
    private String email;
    private int idade;
    private String telefoneContato;
    private String senha;
    private boolean aceitarTermo;
    private String correlacaoPaciente;

    public CuidadorDto() {
    }

    public CuidadorDto(String nome, String cpfCuidador, String email, int idade, String telefoneContato, String senha, boolean aceitarTermo, String correlacaoPaciente, String cpfPaciente, String cep, String logradouro, String cidade, String estado, String complemento, String bairro, String localidade) {
//        super(cep, logradouro, cidade, estado, complemento, bairro, localidade);
        this.nome = nome;
        this.cpfCuidador = cpfCuidador;
        this.email = email;
        this.idade = idade;
        this.telefoneContato = telefoneContato;
        this.senha = senha;
        this.aceitarTermo = aceitarTermo;
        this.correlacaoPaciente = correlacaoPaciente;
        this.cpfPaciente = cpfPaciente;
    }

    public CuidadorDto(Cuidador cuidador) {
        this.nome = cuidador.getNome();
        this.cpfCuidador = cuidador.getCpfCuidador();
        this.email = cuidador.getEmail();
        this.idade = cuidador.getIdade();
        this.telefoneContato = cuidador.getTelefoneContato();
        this.aceitarTermo = cuidador.getAceitarTermo();
        this.correlacaoPaciente = cuidador.getCorrelacaoPaciente();

        if (cuidador.getPaciente() != null) {
            this.cpfPaciente = cuidador.getPaciente().getCpfPaciente();
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getcpfCuidador() {
        return cpfCuidador;
    }

    public void setcpfCuidador(String cpfCuidador) {
        this.cpfCuidador = cpfCuidador;
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

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean getAceitarTermo() {
        return aceitarTermo;
    }

    public void setAceitarTermo(boolean aceitarTermo) {
        this.aceitarTermo = aceitarTermo;
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
