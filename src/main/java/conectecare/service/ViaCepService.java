package conectecare.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import conectecare.model.DTO.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.resource.spi.ConfigProperty;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@ApplicationScoped
public class ViaCepService{
    private static final String VIA_CEP_URL = "https://viacep.com.br/ws";

    public ViaCepDto buscarEnderecoPorCep(String cep) {
        try {
            cep = cep.replaceAll("[^0-9]", "");
            String url = String.format(cep);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(response.body(), ViaCepDto.class);
            } else {
                throw new RuntimeException("CEP não encontrado: " + cep);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar endereço: " + e.getMessage());
        }
    }

    }