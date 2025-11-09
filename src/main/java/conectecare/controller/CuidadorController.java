package conectecare.controller;
import conectecare.model.DTO.CuidadorDto;
import conectecare.model.Entity.Cuidador;
import conectecare.service.CuidadorService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;


import java.util.List;
@Path("/cuidadores")
public class CuidadorController {

    @Inject
    CuidadorService cuidadorService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarCuidadores() {
        List<CuidadorDto> cuidadores = cuidadorService.listarTodosCuidadores();
        return Response.ok(cuidadores).build();
    }



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirCuidador(CuidadorDto cuidadorDto, @Context UriInfo uriInfo) {
        try {
            Cuidador cuidador = cuidadorService.cadastrarEVincularCuidador(cuidadorDto);

            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Long.toString(cuidador.getId()));

            return Response.created(builder.build()).entity(cuidador).build();
        }catch (Exception e) {
            return Response.status(400)
                    .entity("Erro de validação: " + e.getMessage()) // <--- Isto é útil!
                    .build();
        }
        }


    @PUT
    @Path("/{cpf}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarCuidador(@PathParam("cpf")  String cpf, CuidadorDto cuidadorDto){
        try {
            Cuidador Cuidadoratualizado = cuidadorService.atualizacaoCadastro(cuidadorDto, cpf);
            return Response.ok(Cuidadoratualizado).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar paciente: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{cpfCuidador}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarCuidador(@PathParam("cpfCuidador") String cpf) {
        try {
            cuidadorService.excluirCuidador(cpf);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar paciente: " + e.getMessage()).build();
        }
    }
    }
