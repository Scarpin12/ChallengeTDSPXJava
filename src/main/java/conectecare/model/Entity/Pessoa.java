package conectecare.model.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

/// mapeando a tabela de pessoas
@Entity
@Table(name = "PESSOAS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "TIPO_PESSOA",
        discriminatorType = DiscriminatorType.STRING
)
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoas")
    @SequenceGenerator(
            name = "seq_pessoas",
            sequenceName = "SEQ_PESSOAS",
            allocationSize = 1
    )
    @Column(name = "ID")
    private int id;

    @Column(name = "NOME", nullable = true, length = 255)
    private String nome;


    @Column(name = "IDADE", precision = 3)
    private int idade;

    @Column(name = "EMAIL", length = 255, unique = true)
    private String email;

    @Column(name = "TELEFONECONTATO", length = 25)
    private String telefoneContato;

    @Column(name = "SENHA", length = 50, nullable = false)
    private String senha;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonManagedReference("pessoas-enderecos")
    @JoinColumn(name = "ID_ENDERECO")
    private Enderecos enderecos;



    @Column(name = "ACEITARTERMO", nullable = false)
    private Boolean aceitarTermo;


    public Pessoa() {
    }

    public Pessoa(int id, String nome, int idade, String email, String telefoneContato, String senha, Boolean aceitarTermo) {
    }

    public Pessoa(int id, String nome, int idade, String email, String telefoneContato, String senha) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAceitarTermo() {
        return aceitarTermo;
    }

    public void setAceitarTermo(Boolean aceitarTermo) {
        this.aceitarTermo = aceitarTermo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
