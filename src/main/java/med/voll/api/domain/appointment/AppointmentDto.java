package med.voll.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Specialty;

import java.time.LocalDateTime;

public record AppointmentDto(
        Long idMedic,

        @NotNull
        Long idPatient,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,

        @NotNull
        Specialty especialidade
) {
}
