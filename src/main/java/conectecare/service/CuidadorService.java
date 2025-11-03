package conectecare.service;
import conectecare.model.DTO.CuidadorDto;
import conectecare.model.Entity.Cuidador;
import conectecare.repository.CuidadorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class CuidadorService {

    @Inject
    CuidadorRepository cuidadorRepository;

    @Transactional
    public Cuidador cadastrarEVincularCuidador(CuidadorDto cuidadorDto) {

        // 1. Cria e preenche a entidade Cuidador a partir do DTO
        Cuidador cuidador = new Cuidador();
        cuidador.setNome(cuidadorDto.getNome());
        cuidador.setCpf(cuidadorDto.getCpf());
        cuidador.setIdade(cuidadorDto.getIdade());
        cuidador.setEmail(cuidadorDto.getEmail());
        cuidador.setTelefoneContato(cuidadorDto.getTelefone());
        cuidador.setSenha(cuidadorDto.getSenha());
        cuidador.setCorrelacaoPaciente(cuidadorDto.getCorrelacaoPaciente());

        // 2. Persiste o cuidador no banco de dados PRIMEIRO
        cuidadorRepository.persist(cuidador);

        // 3. Se um CPF de paciente foi fornecido, faz a vinculação
        if (cuidadorDto.getCpfPaciente() != null && !cuidadorDto.getCpfPaciente().isEmpty()) {
            boolean vinculado = cuidadorRepository.vincularCuidador(cuidador.getId(), cuidadorDto.getCpfPaciente());
            if (!vinculado) {
                // Lança uma exceção se o CPF do paciente não for encontrado
                throw new RuntimeException("Não foi possível vincular cuidador ao paciente. Verifique se o CPF do paciente está correto.");
            }
        }

        // 4. Retorna o cuidador criado (agora com ID)
        return cuidador;
    }


    @Transactional
    public Cuidador atualizacaoCadastro(CuidadorDto cuidadorDto, String cpf) {

        // Cria objeto cuidador com novos dados
        Cuidador cuidadorAtualizado = new Cuidador();
        cuidadorAtualizado.setNome(cuidadorDto.getNome());
        cuidadorAtualizado.setIdade(cuidadorDto.getIdade());
        cuidadorAtualizado.setEmail(cuidadorDto.getEmail());
        cuidadorAtualizado.setTelefoneContato(cuidadorDto.getTelefone());
        cuidadorAtualizado.setSenha(cuidadorDto.getSenha());
        cuidadorAtualizado.setCorrelacaoPaciente(cuidadorDto.getCorrelacaoPaciente());

        // Atualiza no banco (equivalente ao cuidadorDAO.atualizaCuidador)
        cuidadorRepository.atualizaCuidador(cuidadorAtualizado, cpf);

        System.out.println("Cuidador atualizado com sucesso!");
        return cuidadorAtualizado;
    }


    public List<Cuidador> listarTodosCuidadores() {
        return cuidadorRepository.listarCuidadoresPacientes();
    }


    public Cuidador buscarPorCpf(String cpf) {
        return cuidadorRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("Cuidador não encontrado"));
    }

    @Transactional
    public boolean excluirCuidador(String cpf) {
        return cuidadorRepository.excluirCuidador(cpf);
    }
}