package controler;
import model.dao.CuidadorDAO;
import model.vo.Cuidador;

import java.util.List;

public class CuidadorController {
    private CuidadorDAO cuidadorDAO;

    public CuidadorController() {
        this.cuidadorDAO = new CuidadorDAO();
    }

    public void adicionarCuidador(Cuidador cuidador){
        cuidadorDAO.inserirCuidador(cuidador);
    }

    public void atualizarCuidador(Cuidador cuidador){
        cuidadorDAO.atualizaCuidador(cuidador);
    }

    public void excluirCuidador(int id){
        cuidadorDAO.excluirCuidador(id);
    }

    public List<Cuidador> listarCuidadors(){
        return cuidadorDAO.listarCuidador();
    }
}
