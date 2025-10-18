package model.vo;

public class Especialidades {
    private int idEspecialidade;
    private String especialidadeMedico;

    public Especialidades(int idEspecialidade, String especialidadeMedico) {
        this.idEspecialidade = idEspecialidade;
        this.especialidadeMedico = especialidadeMedico;
    }

    public Especialidades() {

    }

    public int getId() {
        return idEspecialidade;
    }

    public void setId(int id) {
        this.idEspecialidade = idEspecialidade;
    }

    public String getEspecialidade() {
        return especialidadeMedico;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidadeMedico = especialidadeMedico;
    }
}
