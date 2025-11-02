package conectecare.model.Entity;
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

    public Pessoa(int id, String nome, String cpf, int idade, String email, String telefoneContato, String senha) {
    }

    public Pessoa() {
    }

    /// pega a minha primarykey e mapeia ela para conseguir incrementar a cada nova pessoa cadastrada
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoas")
    @SequenceGenerator(
            name = "seq_pessoas",
            sequenceName = "SEQ_PESSOAS",
            allocationSize = 1
    )
    @Column(name = "ID")
    private int id;

    /// coluna nome

    @Column(name = "NOME", nullable = true, length = 255)
    private String nome;

    @Column(name = "CPF", nullable = true, unique = true, length = 14)
    private String cpf;

    @Column(name = "IDADE", precision = 3)
    private int idade;

    @Column(name = "EMAIL", length = 255, unique = true)
    private String email;

    @Column(name = "TELEFONECONTATO", length = 20)
    private String telefoneContato;

    @Column(name = "SENHA", length = 50, nullable = false)
    private String senha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PATOLOGIA")
    private Patologia patologia;


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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
