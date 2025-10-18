package controler;
import model.bo.ConsultaBO;
import model.vo.Consulta;
import java.util.List;
public class ConsultaController {
    private ConsultaBO consultaBO = new ConsultaBO();

    public List<Consulta> listarProximasConsultas() {
        return consultaBO.listarProximasConsultas();
    }
}
