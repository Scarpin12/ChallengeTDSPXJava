package model.vo;

public class Paciente extends Pessoa {
    private Patologia patologia;
    private Cuidador cuidador;
    private Patologia patologiaEscolhida;

    public Paciente(String nome, String cpf, int idade, String email, String telefoneContato,  Patologia patologiaEscolhida, Patologia patologia, Cuidador cuidador) {
        super(nome, cpf, idade, email, telefoneContato);
        this.patologiaEscolhida = patologiaEscolhida;
        this.patologia = patologia;
        this.cuidador = cuidador;

    }
    public Paciente(){
    }

    public Paciente(String nome, String cpf, int idade, String email, String telefone, Patologia patologiaEscolhida) {
        this.patologiaEscolhida = patologiaEscolhida;
    }

    public Patologia getPatologiaEscolhida() {
        return patologiaEscolhida;
    }

    public void setPatologiaEscolhida(Patologia patologiaEscolhida) {
        this.patologiaEscolhida = patologiaEscolhida;
    }

    public Patologia getPatologia() {
        return patologia;
    }

    public void setPatologia(Patologia patologia) {
        this.patologia = patologia;
    }

    public Cuidador getCuidador() {
        return cuidador;
    }

    public void setCuidador(Cuidador cuidador) {
        this.cuidador = cuidador;
    }
}
