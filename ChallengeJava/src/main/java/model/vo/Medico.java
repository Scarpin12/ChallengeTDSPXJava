package model.vo;

public class Medico extends Pessoa{
    private String crm;
    Especialidades especialidades;

    public Medico(int id, String nome, String cpf, int idade, String email, String telefoneContato, String crm, Especialidades especialidade) {
        super( id, nome, cpf, idade, email, telefoneContato);
        this.crm = crm;
        this.especialidades = especialidade;
    }

    public Medico() {

    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Especialidades getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(Especialidades especialidades) {
        this.especialidades = especialidades;
    }
}


