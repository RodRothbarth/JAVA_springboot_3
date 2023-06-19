package med.voll.api.domain.patient;

import med.voll.api.domain.address.Address;

public record ListPacienteDTO(String nome, String telefone, String email, Address address, Long id) {
    public ListPacienteDTO(Paciente paciente){
        this(paciente.getNome(), paciente.getTelefone(), paciente.getTelefone(), paciente.getAddress(), paciente.getId());
    }
}
