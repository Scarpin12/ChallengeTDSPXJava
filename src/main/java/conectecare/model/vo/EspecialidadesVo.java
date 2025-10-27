package conectecare.model.vo;

public class EspecialidadesVo {
    private int idEspecialidade;
    private String especialidadeMedico;

    public EspecialidadesVo(int idEspecialidade, String especialidadeMedico) {
        this.idEspecialidade = idEspecialidade;
        this.especialidadeMedico = especialidadeMedico;
    }

    public EspecialidadesVo() {

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
