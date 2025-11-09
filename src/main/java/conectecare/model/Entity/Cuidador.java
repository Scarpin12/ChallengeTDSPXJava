package conectecare.model.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CUIDADOR")
public class Cuidador extends Pessoa{

     public Cuidador(int id, String nome, int idade, String email, String telefoneContato, String senha, boolean aceitarTermo, String correlacaoPaciente) {
         super(id,nome, idade,email,telefoneContato,senha, aceitarTermo);
         this.correlacaoPaciente = correlacaoPaciente;
     }

    public Cuidador() {
    }

    @Column(name = "CPF_CUIDADOR",  unique = true, length = 14)
    private String cpfCuidador;

//    @Column(name = "CPF_PACIENTE", unique = true, length = 14)
//    private String cpfPaciente;

    @Column(name = "CORRELACAOPACIENTE", length = 50)
    private String correlacaoPaciente;

    @OneToOne(mappedBy = "cuidadorAssociado", fetch = FetchType.LAZY)
    @JsonBackReference("paciente-cuidador")
    private Paciente paciente;

    public String getCpfCuidador() {
        return cpfCuidador;
    }

    public void setCpfCuidador(String cpfCuidador) {
        this.cpfCuidador = cpfCuidador;
    }

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
