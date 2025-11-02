package conectecare.model.Entity;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("MEDICO")
public class Medico extends Pessoa{

    public Medico(int id, String nome, String cpf, int idade, String email, String telefoneContato, String senha,  String crm, Especialidade especialidade) {
        super( id, nome, cpf, idade, email, telefoneContato, senha);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public Medico() {
    }

    @Column(name = "CRM", unique = true, length = 20)
    private String crm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESPECIALIDADE")
    private Especialidade especialidade;

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Especialidade getEspecialidades() {
        return especialidade;
    }

    public void setEspecialidades(Especialidade especialidades) {
        this.especialidade = especialidades;
    }
}
