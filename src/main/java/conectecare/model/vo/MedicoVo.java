package conectecare.model.vo;

public class MedicoVo extends PessoaVo{
    private String crm;
    EspecialidadesVo especialidades;

    public MedicoVo(int id, String nome, String cpf, int idade, String email, String telefoneContato, String crm, EspecialidadesVo especialidade) {
        super( id, nome, cpf, idade, email, telefoneContato);
        this.crm = crm;
        this.especialidades = especialidade;
    }

    public MedicoVo() {

    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public EspecialidadesVo getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(EspecialidadesVo especialidades) {
        this.especialidades = especialidades;
    }
}
