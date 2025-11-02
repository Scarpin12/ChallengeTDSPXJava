package conectecare.model.DTO;

import java.time.LocalDateTime;

public class ConsultaDto {
    private LocalDateTime dataHora;
    private String status;
    private String observacoes;
    private Long pacienteId;
    private Long cuidadorId;

    // Construtor vazio
    public ConsultaDto() {
    }

    // Construtor com parâmetros
    public ConsultaDto(LocalDateTime dataHora, String status, String observacoes, Long pacienteId, Long cuidadorId) {
        this.dataHora = dataHora;
        this.status = status;
        this.observacoes = observacoes;
        this.pacienteId = pacienteId;
        this.cuidadorId = cuidadorId;
    }

    // Getters e Setters
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getCuidadorId() {
        return cuidadorId;
    }

    public void setCuidadorId(Long cuidadorId) {
        this.cuidadorId = cuidadorId;
    }
}