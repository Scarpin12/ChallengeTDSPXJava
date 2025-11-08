package conectecare.model.DTO;

import jakarta.persistence.Column;

public class ViaCepDto {
    private int id;
    private String cep;
    private String logradouro;
    private String cidade;
    private String estado;
    private String complemento;
    private String bairro;
    private String localidade;

    public ViaCepDto() {
    }

    public ViaCepDto(int id, String cep, String logradouro, String cidade, String estado, String complemento, String bairro, String localidade) {
        this.id = id;
        this.cep = cep;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
    }

    public ViaCepDto(String cep, String logradouro, String cidade, String estado, String complemento, String bairro, String localidade) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
}
