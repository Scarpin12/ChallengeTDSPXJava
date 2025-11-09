package conectecare.controller;

import conectecare.model.DTO.LoginDto;
import conectecare.model.Entity.Cuidador;
import conectecare.model.Entity.Paciente;
import conectecare.service.CuidadorService;
import conectecare.service.PacienteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginController {

        @Inject
        PacienteService pacienteService;

        @Inject
        CuidadorService cuidadorService;


        @POST
        public Response login(LoginDto loginDto) {

                Paciente paciente = pacienteService.buscarPorEmail(loginDto.getEmail());
                if (paciente != null && paciente.getSenha().equals(loginDto.getSenha())) {
                    return Response.ok(paciente).build();
                }

                Cuidador cuidador = cuidadorService.buscarPorEmail(loginDto.getEmail());
                if (cuidador != null && cuidador.getSenha().equals(loginDto.getSenha())) {
                    return Response.ok(cuidador).build();
                }

                return Response.status(Response.Status.UNAUTHORIZED)
                        .build();

        }
    }

