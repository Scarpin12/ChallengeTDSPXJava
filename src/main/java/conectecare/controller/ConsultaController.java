package conectecare.controller;

import conectecare.model.bo.ConsultaBo;
import conectecare.model.vo.ConsultaVo;

import java.util.List;

public class ConsultaController {
    private ConsultaBo consultaBO = new ConsultaBo();

    public List<ConsultaVo> listarProximasConsultas() {
        return consultaBO.listarProximasConsultas();
    }

}
