package conectecare.model.Entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("PACIENTE")
public class Paciente extends Pessoa {

    public Paciente(int id, String nome, int idade, String email, String telefoneContato, String senha, Boolean aceitarTermo, Patologia patologia) {
        super(id, nome, idade, email, telefoneContato, senha, aceitarTermo);
        this.patologia = patologia;
    }

    public Paciente() {
    }

    @Column(name = "CPF_PACIENTE", unique = true, length = 14)
    private String cpfPaciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PATOLOGIA")
    @JsonManagedReference("patologia-paciente")
    private Patologia patologia;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonManagedReference("paciente-cuidador")
    @JoinColumn(name = "ID_CUIDADOR_ASSOCIADO")
    private Cuidador cuidadorAssociado;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference("paciente-consulta")
     private List<Consulta> consultas;



    public Patologia getPatologia() {
        return patologia;
    }

    public void setPatologia(Patologia patologia) {
        this.patologia = patologia;
    }

    public Cuidador getCuidadorAssociado() {
        return cuidadorAssociado;
    }

    public void setCuidadorAssociado(Cuidador cuidadorAssociado) {
        this.cuidadorAssociado = cuidadorAssociado;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    //    public Enderecos getEndereco() {
//        return endereco;
//    }
//
//    public void setEndereco(Enderecos endereco) {
//        this.endereco = endereco;
//    }
}
