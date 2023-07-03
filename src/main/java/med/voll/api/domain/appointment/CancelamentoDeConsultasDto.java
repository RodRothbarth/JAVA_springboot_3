package med.voll.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CancelamentoDeConsultasDto(
        @NotNull
        Long idConsulta,
        @NotNull
        CancelMotive reason
) {
}
