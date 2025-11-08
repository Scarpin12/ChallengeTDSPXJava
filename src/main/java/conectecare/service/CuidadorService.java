package conectecare.service;
import conectecare.model.DTO.CuidadorDto;
import conectecare.model.DTO.ViaCepDto;
import conectecare.model.Entity.Cuidador;
import conectecare.model.Entity.Enderecos;
import conectecare.model.Entity.Paciente;
import conectecare.model.Entity.Pessoa;
import conectecare.repository.CuidadorRepository;
import conectecare.repository.PacienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class CuidadorService {

    @Inject
    CuidadorRepository cuidadorRepository;

    @Inject
    PacienteRepository pacienteRepository;

    @Inject
    ViaCepService viaCepService;

    @Transactional
    public Cuidador cadastrarEVincularCuidador(CuidadorDto cuidadorDto) {
        Cuidador cuidador = new Cuidador();
        ViaCepDto viaCepDto = new ViaCepDto();

        Optional<Cuidador> cuidadorExistente = cuidadorRepository.findByCpf(cuidadorDto.getcpfCuidador());

        if (cuidadorExistente.isPresent()) {
            cuidador = cuidadorExistente.get();

            System.out.println("Usando cuidador existente: " + cuidador.getNome());
        } else {

            cuidador.setNome(cuidadorDto.getNome());
            cuidador.setCpfCuidador(cuidadorDto.getCpfPaciente());
            cuidador.setIdade(cuidadorDto.getIdade());
            cuidador.setEmail(cuidadorDto.getEmail());
            cuidador.setTelefoneContato(cuidadorDto.getTelefoneContato());
            cuidador.setSenha(cuidadorDto.getSenha());
            cuidador.setAceitarTermo(cuidadorDto.getAceitarTermo());
            cuidador.setCorrelacaoPaciente(cuidadorDto.getCorrelacaoPaciente());

            cuidadorRepository.persist(cuidador);

            if (cuidadorDto.getCpfPaciente() != null) {
                Paciente paciente = pacienteRepository.findByCpf(cuidadorDto.getCpfPaciente())
                        .orElseThrow(() -> new NotFoundException("Paciente não encontrado com CPF: " + cuidadorDto.getCpfPaciente()));

                // Verifica se paciente já tem cuidador
                if (paciente.getCuidadorAssociado() != null) {
                    throw new BadRequestException("Paciente já possui um cuidador vinculado");
                }

                paciente.setCuidadorAssociado(cuidador);
                pacienteRepository.persist(paciente);

                if (viaCepDto.getCep() != null && !viaCepDto.getCep().isEmpty()) {
                    try {
                        ViaCepDto endereco = viaCepService.buscarEnderecoPorCep(viaCepDto.getCep());
                        System.out.println("Endereço encontrado: " + endereco.getLogradouro() + ", " + endereco.getLocalidade() + "-" + endereco.getLocalidade());

                        // Aqui você pode salvar o endereço em outra tabela se quiser
                        // ou apenas usar as informações para validação

                    } catch (Exception e) {
                        System.out.println("CEP inválido ou não encontrado: " + viaCepDto.getCep());
                        // Você pode lançar uma exceção ou apenas ignorar, dependendo da regra de negócio
                    }

                }
            }
        }
        return cuidador;
    }

    @Transactional
    public Cuidador atualizacaoCadastro(CuidadorDto cuidadorDto, String cpf) {

        Cuidador cuidadorAtualizado = new Cuidador();
        cuidadorAtualizado.setNome(cuidadorDto.getNome());
        cuidadorAtualizado.setIdade(cuidadorDto.getIdade());
        cuidadorAtualizado.setEmail(cuidadorDto.getEmail());
        cuidadorAtualizado.setTelefoneContato(cuidadorDto.getTelefoneContato());
        cuidadorAtualizado.setSenha(cuidadorDto.getSenha());
        cuidadorAtualizado.setCorrelacaoPaciente(cuidadorDto.getCorrelacaoPaciente());

        cuidadorRepository.atualizaCuidador(cuidadorAtualizado, cpf);

        System.out.println("Cuidador atualizado com sucesso!");
        return cuidadorAtualizado;
    }

    public List<CuidadorDto> listarTodosCuidadores() {
        List<Cuidador> cuidadoresEntities = cuidadorRepository.listarCuidadoresPacientes();

        return cuidadoresEntities.stream().map(CuidadorDto::new).collect(Collectors.toList());
    }

    @Transactional
    public boolean excluirCuidador(String cpf) {
        return cuidadorRepository.excluirCuidador(cpf);
    }
}