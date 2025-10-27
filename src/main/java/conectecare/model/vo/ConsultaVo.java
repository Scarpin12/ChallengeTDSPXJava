package conectecare.model.vo;

public class ConsultaVo {
    private int id;
    private String nomePaciente;
    private String patologiaPaciente;
    private String nomeMedico;
    private String especialidadeMedico;
    private String dataHora;
    private String status;
    private String linkTelemedicina;

    public ConsultaVo(String nomePaciente, String patologiaPaciente, String nomeMedico,
                    String especialidadeMedico, String dataHora, String status, String linkTelemedicina)
    {
        this.nomePaciente = nomePaciente;
        this.patologiaPaciente = patologiaPaciente;
        this.nomeMedico = nomeMedico;
        this.especialidadeMedico = especialidadeMedico;
        this.dataHora = dataHora;
        this.status = status;
        this.linkTelemedicina = linkTelemedicina;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getPatologiaPaciente() {
        return patologiaPaciente;
    }

    public void setPatologiaPaciente(String patologiaPaciente) {
        this.patologiaPaciente = patologiaPaciente;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getEspecialidadeMedico() {
        return especialidadeMedico;
    }

    public void setEspecialidadeMedico(String especialidadeMedico) {
        this.especialidadeMedico = especialidadeMedico;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLinkTelemedicina() {
        return linkTelemedicina;
    }

    public void setLinkTelemedicina(String linkTelemedicina) {
        this.linkTelemedicina = linkTelemedicina;
    }
}
