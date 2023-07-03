package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.AgendaDeConsultas;
import med.voll.api.domain.appointment.AppointmentDto;
import med.voll.api.domain.appointment.AppointmentDetailDTO;
import med.voll.api.domain.appointment.CancelamentoDeConsultasDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class AppointmentController {
   @Autowired
    private AgendaDeConsultas agenda;
    @PostMapping
    @Transactional
    public ResponseEntity book(@RequestBody @Valid AppointmentDto data){
        agenda.agendar(data);
        return ResponseEntity.ok(new AppointmentDetailDTO(null, null, null, null));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid CancelamentoDeConsultasDto dados){
    agenda.cancelar(dados);
    return ResponseEntity.noContent().build();
    }
}
