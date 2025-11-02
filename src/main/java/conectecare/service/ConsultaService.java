package conectecare.service;

import conectecare.model.Entity.Consulta;
import conectecare.model.Entity.Medico;
import conectecare.model.Entity.Paciente;
import conectecare.repository.ConsultaRepository;
import conectecare.repository.MedicoRepository;
import conectecare.repository.PacienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class ConsultaService {

    @Inject
    ConsultaRepository consultaRepository;

    @Inject
    MedicoRepository medicoRepository;

    @Inject
    PacienteRepository pacienteRepository;

    public List<Consulta> listarProximasConsultas() {
        return consultaRepository.listarProximasConsultas();
    }

    @Transactional
    public void cadastrarConsultaAutomatica(Paciente paciente, Integer idPatologia) {
        // Busca médico para a patologia (equivalente ao MedicoPatologia no BO)
        Medico medico = buscarMedicoPorPatologia(idPatologia);

        if (medico != null) {
            // Cria a consulta (equivalente ao INSERT no BO)
            Consulta consulta = new Consulta();
            consulta.setPaciente(paciente);
            consulta.setMedico(medico);
            consulta.setDataHora(String.valueOf(LocalDateTime.now().plusDays(7))); // 7 dias depois
            consulta.setStatus("AGENDADA");
            consulta.setLinkTelemedicina("https://meet.google.com/abc-defg-hij");

            // Salva a consulta
            consultaRepository.persist(consulta);

            System.out.println("Consulta agendada para o paciente!");
        } else {
            System.out.println("Nenhum médico encontrado para patologia " + idPatologia);
        }
    }

    private Medico buscarMedicoPorPatologia(Integer idPatologia) {
        // Busca médico por patologia (usando o repository)
        return medicoRepository.findByPatologiaId(idPatologia)
                .orElse(null); // Retorna null se não encontrar, igual ao BO original
    }

    public List<Consulta> listarPorPaciente(Integer pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId);
    }

    public List<Consulta> listarPorMedico(Integer medicoId) {
        return consultaRepository.findByMedicoId(medicoId);
    }
}