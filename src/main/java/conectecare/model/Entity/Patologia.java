package conectecare.model.Entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Patologia")
public class Patologia {

    public Patologia() {
    }

    public Patologia(String nomePatologia, Integer id) {
        this.nomePatologia = nomePatologia;
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_patologia")
    @SequenceGenerator(name = "seq_patologia", sequenceName = "SEQ_PATOLOGIA", allocationSize = 1)
    @Column(name = "ID_PATOLOGIA")
    private Integer id;

    @Column(name = "NOMEPATOLOGIA", nullable = true, unique = true, length = 255)
    private String nomePatologia;

    @ManyToMany(mappedBy = "patologias")
    private List<Especialidade> especialidades;

    @OneToMany(mappedBy = "patologia", fetch = FetchType.LAZY)
    private List<Paciente> pacientes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public List<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }

    public String getNomePatologia() {
        return nomePatologia;
    }

    public void setNomePatologia(String nomePatologia) {
        this.nomePatologia = nomePatologia;
    }
}
