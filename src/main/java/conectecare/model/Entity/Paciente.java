package conectecare.model.Entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("PACIENTE")
public class Paciente extends Pessoa {

    public Paciente(int id, String nome, String cpf, int idade, String email, String telefoneContato, String senha, Patologia patologia) {
        super(id, nome, cpf, idade, email, telefoneContato, senha);
        this.patologia = patologia;
    }

    public Paciente() {
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CUIDADOR_ASSOCIADO")
    private Cuidador cuidadorAssociado;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
     private List<Consulta> consultas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PATOLOGIA")
    private Patologia patologia;


    public Patologia getPatologia() {
        return patologia;
    }

    public void setPatologia(Patologia patologia) {
        this.patologia = patologia;
    }

    public Cuidador getCuidadorAssociado() {
        return cuidadorAssociado;
    }

    public void setCuidadorAssociado(Cuidador cuidadorAssociado) {
        this.cuidadorAssociado = cuidadorAssociado;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
}



//package conectecare.model.Entity;
//
//import jakarta.persistence.*;
//        import java.util.List;
//
//@Entity
//@DiscriminatorValue("PACIENTE")
//public class Paciente extends Pessoa {
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ID_PATOLOGIA")
//    private Patologia patologia;
//
//    // ✅ RELACIONAMENTO 1-para-1 com Cuidador
//    @OneToOne(fetch = FetchType.LAZY)  // ← IMPORTANTE: @OneToOne, não @ManyToOne
//    @JoinColumn(name = "ID_CUIDADOR_ASSOCIADO")  // ← Coluna FK na tabela PESSOAS
//    private Cuidador cuidadorAssociado;
//
//    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Consulta> consultas;
//
//    // Construtores
//    public Paciente() {
//        super();
//    }
//
//    public Paciente(String nome, String cpf, Integer idade, String email, String telefoneContato, String senha) {
//        super(nome, cpf, idade, email, telefoneContato, senha);
//    }
//
//    // Getters e Setters
//    public Patologia getPatologia() {
//        return patologia;
//    }
//
//    public void setPatologia(Patologia patologia) {
//        this.patologia = patologia;
//    }
//
//    public Cuidador getCuidadorAssociado() {
//        return cuidadorAssociado;
//    }
//
//    public void setCuidadorAssociado(Cuidador cuidadorAssociado) {
//        this.cuidadorAssociado = cuidadorAssociado;
//    }
//
//    public List<Consulta> getConsultas() {
//        return consultas;
//    }
//
//    public void setConsultas(List<Consulta> consultas) {
//        this.consultas = consultas;
//    }
//}