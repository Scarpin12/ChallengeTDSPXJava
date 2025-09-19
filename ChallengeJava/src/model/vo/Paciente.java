package model.vo;

public class Paciente extends Pessoa {
    private String endereco;
    private Patologia patologia;

    public Paciente( String nome, String cpf, int idade, String email, String telefoneContato /*String patologia*/, String endereco){
        super(nome, cpf, idade, email, telefoneContato);
        //this.patologia = patologia;
        this.endereco = endereco;
    }


    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Patologia getPatologia() {
        return patologia;
    }

    public void setPatologia(Patologia patologia) {
        this.patologia = patologia;
    }
}
