package conectecare.model.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "ENDERECOS")
public class Enderecos {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_enderecos")
    @SequenceGenerator(name = "seq_enderecos", sequenceName = "SEQ_ENDERECOS")
    @Column(name = "ID_ENDERECO")
    private int id;

    @Column(name = "CEP", length = 9, nullable = false)
    private String cep;

    @Column(name = "LOGRADOURO", length = 255, nullable = false)
    private String logradouro;

    @Column(name = "CIDADE", length = 100, nullable = false)
    private String cidade;

    @Column(name = "ESTADO", length = 2, nullable = false)
    private String estado;

    @Column(name = "COMPLEMENTO", length = 100)
    private String complemento;

    @Column(name = "BAIRRO", length = 100)
    private String bairro;

    @Column(name = "LOCALIDADE", length = 100)
    private String localidade;



    public Enderecos() {
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
        return this.logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
