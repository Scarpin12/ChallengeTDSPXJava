package model.vo;

public class Patologia {
    private int id;
    private String nome;
    private int CID10;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCID10() {
        return CID10;
    }

    public void setCID10(int CID10) {
        this.CID10 = CID10;
    }
}
