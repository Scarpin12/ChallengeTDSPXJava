package conectecare.model.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ESPECIALIDADES")
public class Especialidade {

    public Especialidade() {
    }

    public Especialidade(int id, String especialidadeMedico) {
        this.id = id;
        this.especialidadeMedico = especialidadeMedico;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_especialidades")
    @SequenceGenerator(name = "seq_especialidades", sequenceName = "SEQ_ESPECIALIDADES")
    @Column(name = "ID_ESPECIALIDADE")
    private int id;

    @Column(name = "ESPECIALIDADEMEDICO", length = 100, nullable = false, unique = false)
    private String especialidadeMedico;

    @ManyToMany
    @JoinTable(
            name = "patologias_especialidades",
            joinColumns = @JoinColumn(name = "id_especialidade"),
            inverseJoinColumns = @JoinColumn(name = "id_patologia")
    )
    private List<Patologia> patologias;

    @OneToMany(mappedBy = "especialidade", fetch = FetchType.LAZY)
    private List<Medico> medicos;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspecialidadeMedico() {
        return especialidadeMedico;
    }

    public void setEspecialidadeMedico(String especialidadeMedico) {
        this.especialidadeMedico = especialidadeMedico;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

    public List<Patologia> getPatologias() {
        return patologias;
    }

    public void setPatologias(List<Patologia> patologias) {
        this.patologias = patologias;
    }
}
