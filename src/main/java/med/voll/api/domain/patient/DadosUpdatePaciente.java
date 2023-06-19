package med.voll.api.domain.patient;

import med.voll.api.domain.address.Address;

public record DadosUpdatePaciente(Long id, String nome, String email, String telefone, String cpf, Address address) {
    public DadosUpdatePaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getAddress());
    }
}
