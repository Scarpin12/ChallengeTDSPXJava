package conectecare.model.vo;

public class CuidadorVo extends PessoaVo{
    private String correlacaoPaciente;

    public CuidadorVo(int id, String nome, String cpf, int idade, String email, String telefoneContato, String correlacaoPaciente) {
        super(id,  nome, cpf, idade, email, telefoneContato);
        this.correlacaoPaciente = correlacaoPaciente;
    }

    public CuidadorVo(String nome, String cpf, int idade, String email, String telefoneContato, String correlacaoPaciente) {
        super( nome, cpf, idade, email, telefoneContato);
        this.correlacaoPaciente = correlacaoPaciente;
    }


    public String getCorrelacaoPaciente() {
        return correlacaoPaciente;
    }

    public void setCorrelacaoPaciente(String correlacaoPaciente) {
        this.correlacaoPaciente = correlacaoPaciente;
    }

}
