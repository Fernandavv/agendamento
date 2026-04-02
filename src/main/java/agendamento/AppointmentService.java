package agendamento;

import agendamento.model.Appointment;
import agendamento.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public List<Appointment> listar() {
        return repository.findAll();
    }

    public Appointment criar(Appointment appointment) {
        if (appointment.getStatus() == null || appointment.getStatus().isBlank()) {
            appointment.setStatus("AGENDADO");
        }
        return repository.save(appointment);
    }

    public Appointment atualizar(Long id, Appointment novo) {
        return repository.findById(id)
                .map(appointment -> {
                    appointment.setNome(novo.getNome());
                    appointment.setData(novo.getData());
                    appointment.setHorario(novo.getHorario());
                    appointment.setDescricao(novo.getDescricao());

                    if (novo.getStatus() == null || novo.getStatus().isBlank()) {
                        appointment.setStatus("AGENDADO");
                    } else {
                        appointment.setStatus(novo.getStatus());
                    }

                    return repository.save(appointment);
                })
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}