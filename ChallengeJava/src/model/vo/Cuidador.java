package model.vo;

import java.time.LocalDate;

public class Cuidador extends Pessoa {
    private String correlacaoPaciente;
    public Cuidador(int id, String nome, String cpf, int idade, String email, String telefoneContato, String correlacaoPaciente) {
        super(id,  nome, cpf, idade, email, telefoneContato);
        this.correlacaoPaciente = correlacaoPaciente;
    }

    public String getCorrelacaoPaciente() {
        return correlacaoPaciente;
    }

    public void setCorrelacaoPaciente(String correlacaoPaciente) {
        this.correlacaoPaciente = correlacaoPaciente;
    }
}
