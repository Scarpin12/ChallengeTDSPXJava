package model.vo;

public class Patologia {
    private int idPatologia;
    private String nomePatologia;
    private String cid10;

    public Patologia(int idPatologia,String nomePatologia, String cid10) {
        this.idPatologia = idPatologia;
        this.nomePatologia = nomePatologia;
        this.cid10 = cid10;
    }

    public Patologia() {
    }

    public int getidPatologia() {
        return idPatologia;
    }

    public void setIdPatologia(int id) {
        this.idPatologia = idPatologia;
    }

    public String getnomePatologia() {
        return nomePatologia;
    }

    public void setnomePatologia(String nome) {
        this.nomePatologia = nomePatologia;
    }

    public String getCid10() {
        return cid10;
    }

    public void setCid10(String cid10) {
        this.cid10 = cid10;
    }
}
