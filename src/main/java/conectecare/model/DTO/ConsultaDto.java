package conectecare.model.DTO;

import conectecare.model.Entity.Consulta;

import java.time.LocalDateTime;

public class ConsultaDto {
    private String paciente;
    private String medico;
    private String status;
    private String patologia;
    private String especialidade;
    private LocalDateTime dataHora;

    public ConsultaDto(Consulta consulta) {
        this.paciente = consulta.getPaciente().getNome();
        this.medico = consulta.getMedico().getNome();
        this.status = consulta.getStatus();
        this.dataHora = consulta.getDataHora();
    }
    public ConsultaDto() {
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        paciente = paciente;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        medico = medico;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPatologia() {
        return patologia;
    }

    public void setPatologia(String patologia) {
        this.patologia = patologia;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}