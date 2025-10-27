package conectecare.model.vo;

public class PatologiaVo {
    private int idPatologia;
    private String nomePatologia;

    public PatologiaVo(int idPatologia,String nomePatologia) {
        this.idPatologia = idPatologia;
        this.nomePatologia = nomePatologia;
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

}
