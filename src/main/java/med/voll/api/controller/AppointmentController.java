package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.AppointmentDto;
import med.voll.api.domain.appointment.AppointmentDetailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class AppointmentController {
    @PostMapping
    @Transactional
    public ResponseEntity book(@RequestBody @Valid AppointmentDto data){
        return ResponseEntity.ok(new AppointmentDetailDTO(null, null, null, null));
    }
}
