package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentDetailDTO(Long id, Long idMedic, Long idPatient, LocalDateTime data) {
}
