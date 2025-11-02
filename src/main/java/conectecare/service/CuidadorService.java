package conectecare.service;
import conectecare.model.Entity.Cuidador;
import conectecare.repository.CuidadorRepository;
import conectecare.repository.PacienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class CuidadorService {

    @Inject
    CuidadorRepository cuidadorRepository;

    @Inject
    PacienteRepository pacienteRepository;


    @Transactional
    public void cadastrarEVincularCuidador(String nome, String cpfCuidador, Integer idade, String email,
                                           String telefoneContato, String correlacaoPaciente, String cpfPaciente) {

        // 1. Cria o cuidador (equivalente ao CuidadorVo no DAO)
        Cuidador cuidador = new Cuidador();
        cuidador.setNome(nome);
        cuidador.setCpf(cpfCuidador);
        cuidador.setIdade(idade);
        cuidador.setEmail(email);
        cuidador.setTelefoneContato(telefoneContato);
        cuidador.setCorrelacaoPaciente(correlacaoPaciente);

        // 2. Salva o cuidador (equivalente ao cuidadorDAO.inserirCuidador)
        cuidadorRepository.persist(cuidador);

        // 3. Vincula ao paciente (equivalente ao cuidadorDAO.vincularCuidador)
        boolean vinculado = cuidadorRepository.vincularCuidador(cuidador.getId(), cpfPaciente);

        if (!vinculado) {
            throw new RuntimeException("Não foi possível vincular cuidador ao paciente");
        }

        System.out.println("Cuidador cadastrado e vinculado com sucesso!");
    }


    @Transactional
    public void atualizacaoCadastro(String nome, String cpf, Integer idade, String email,
                                    String telefone, String correlacaoPaciente, String cpfValidacao) {

        // Cria objeto cuidador com novos dados
        Cuidador cuidadorAtualizado = new Cuidador();
        cuidadorAtualizado.setNome(nome);
        cuidadorAtualizado.setCpf(cpf);
        cuidadorAtualizado.setIdade(idade);
        cuidadorAtualizado.setEmail(email);
        cuidadorAtualizado.setTelefoneContato(telefone);
        cuidadorAtualizado.setCorrelacaoPaciente(correlacaoPaciente);

        // Atualiza no banco (equivalente ao cuidadorDAO.atualizaCuidador)
        cuidadorRepository.atualizaCuidador(cuidadorAtualizado, cpfValidacao);

        System.out.println("Cuidador atualizado com sucesso!");
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