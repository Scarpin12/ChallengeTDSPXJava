package conectecare.controller;

import conectecare.model.DTO.ViaCepDto;
import conectecare.service.ViaCepService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public class ViaCepController {

    @Inject
    ViaCepService viaCepService;

    @GET
    @Path("/{cep}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarEnderecoPorCep(@PathParam("cep") String cep) {
        try {
            ViaCepDto endereco = viaCepService.buscarEnderecoPorCep(cep);
            return Response.ok(endereco).build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao buscar endereço: " + e.getMessage())
                    .build();
        }
    }
}
