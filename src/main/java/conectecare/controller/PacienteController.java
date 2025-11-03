package conectecare.controller;

import conectecare.model.DTO.PacienteDto;
import conectecare.model.Entity.Paciente;
import conectecare.repository.PacienteRepository;
import conectecare.service.PacienteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.core.*;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

@Path("/pacientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacienteController {

    @Inject
    PacienteService pacienteService;


    //buscar
    @GET
    public Response listarPacientes() {

            List<Paciente> pacientes = pacienteService.listarTodosPacientes();
            return Response.ok(pacientes).build();

    }

    @POST
    public Response inserirPaciente(PacienteDto pacienteDTO, @Context UriInfo uriInfo) {
        try {
            Paciente paciente = pacienteService.cadastrarNovoPaciente(pacienteDTO);

            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Long.toString(paciente.getId()));

            return Response.created(builder.build())
                    .entity(paciente)
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao criar paciente: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{cpf}")
    public Response atualizarPacientePorCpf(@PathParam("cpf") String cpf, PacienteDto pacienteDto) {
        try {
            Paciente pacienteAtualizado = pacienteService.atualizacaoCadastro(pacienteDto, cpf);
            return Response.ok(pacienteAtualizado).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar paciente: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{cpf}")
    public Response deletarPaciente(@PathParam("cpf") String cpf) {
        try {
            pacienteService.excluirPaciente(cpf);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar paciente: " + e.getMessage()).build();
        }
    }

}
