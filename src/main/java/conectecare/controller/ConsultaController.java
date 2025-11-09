package conectecare.controller;
import conectecare.model.DTO.ConsultaDto;
import conectecare.model.Entity.Consulta;
import conectecare.service.ConsultaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
@Path("/consultas")
public class ConsultaController {

    @Inject
    ConsultaService consultaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/paciente/{pacienteId}")
    public List<Consulta> getConsultasPorPaciente(@PathParam("pacienteId") Integer pacienteId) {
        return consultaService.listarPorPaciente(pacienteId);
    }


}
