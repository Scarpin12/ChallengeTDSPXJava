package model.vo;

public class Paciente extends Pessoa {
    private Patologia patologiaEscolhida;

    public Paciente(String nome, String cpf, int idade, String email, String telefoneContato,  Patologia patologiaEscolhida) {
        super(nome, cpf, idade, email, telefoneContato);
        this.patologiaEscolhida = patologiaEscolhida;

    }

    public Patologia getPatologiaEscolhida() {
        return patologiaEscolhida;
    }

    public void setPatologiaEscolhida(Patologia patologiaEscolhida) {
        this.patologiaEscolhida = patologiaEscolhida;
    }


}
