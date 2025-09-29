package controler;
import model.dao.MedicoDAO;
import model.vo.Especialidades;
import model.vo.Medico;

public class MedicoController {
    private MedicoDAO medicoDAO;

    public MedicoController(MedicoDAO medicoDAO) {
        this.medicoDAO = medicoDAO;
    }
}
