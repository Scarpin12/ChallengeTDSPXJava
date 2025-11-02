package conectecare.model.Entity;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CUIDADOR")
public class Cuidador extends Pessoa{
//    private String correlacaoPaciente;

    public Cuidador(int id, String nome, String cpf, int idade, String email, String telefoneContato, String senha,  String correlacaoPaciente) {
        super(id,  nome, cpf, idade, email, telefoneContato, senha);
        this.correlacaoPaciente = correlacaoPaciente;
    }

    public Cuidador() {
    }

    @Column(name = "CORRELACAOPACIENTE", length = 50)
    private String correlacaoPaciente;

    @OneToOne(mappedBy = "cuidadorAssociado", fetch = FetchType.LAZY)
    private Paciente paciente;

    public String getCorrelacaoPaciente() {
        return correlacaoPaciente;
    }

    public void setCorrelacaoPaciente(String correlacaoPaciente) {
        this.correlacaoPaciente = correlacaoPaciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }


}
