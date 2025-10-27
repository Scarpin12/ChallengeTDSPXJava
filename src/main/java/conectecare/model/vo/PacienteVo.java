package conectecare.model.vo;

public class PacienteVo extends PessoaVo{
    private PatologiaVo patologiaEscolhida;

    public PacienteVo(String nome, String cpf, int idade, String email, String telefoneContato,  PatologiaVo patologiaEscolhida) {
        super(nome, cpf, idade, email, telefoneContato);
        this.patologiaEscolhida = patologiaEscolhida;

    }

    public PatologiaVo getPatologiaEscolhida() {
        return patologiaEscolhida;
    }

    public void setPatologiaEscolhida(PatologiaVo patologiaEscolhida) {
        this.patologiaEscolhida = patologiaEscolhida;
    }


}

