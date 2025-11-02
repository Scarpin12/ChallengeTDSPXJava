package conectecare.model.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CONSULTAS")
public class Consulta {

    public Consulta(int id, Paciente paciente, Medico medico, String dataHora, String status, String linkTelemedicina) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
        this.status = status;
        this.linkTelemedicina = linkTelemedicina;
    }

    public Consulta() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_consultas")
    @SequenceGenerator(name = "seq_consultas", sequenceName = "SEQ_CONSULTAS", allocationSize = 1)
    @Column(name = "ID")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PACIENTE", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MEDICO", nullable = false)
    private Medico medico;

    @Column(name = "DATA_HORA", nullable = false)
    private String dataHora;

    @Column(name = "LINK_TELEMEDICINA", length = 255)
    private String linkTelemedicina;

    @Column(name = "STATUS", length = 20, nullable = false)
    private String status = "AGENDADA";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getLinkTelemedicina() {
        return linkTelemedicina;
    }

    public void setLinkTelemedicina(String linkTelemedicina) {
        this.linkTelemedicina = linkTelemedicina;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
