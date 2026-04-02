package agendamento.controller;

import agendamento.model.Appointment;
import agendamento.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Appointment> listar() {
        return service.listar();
    }

    @PostMapping
    public Appointment criar(@RequestBody Appointment appointment) {
        return service.criar(appointment);
    }

    @PutMapping("/{id}")
    public Appointment atualizar(@PathVariable Long id, @RequestBody Appointment appointment) {
        return service.atualizar(id, appointment);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}