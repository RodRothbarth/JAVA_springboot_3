package med.voll.api.domain.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentDto(
        Long idMedic,

        @NotNull
        Long idPatient,

        @NotNull
        @Future LocalDateTime data
) {
}
