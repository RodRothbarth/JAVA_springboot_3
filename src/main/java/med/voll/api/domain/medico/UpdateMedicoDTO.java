package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressData;

public record UpdateMedicoDTO(
        @NotNull
        Long id,
        String telefone,
        String nome,
        AddressData address) {
}
