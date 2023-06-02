package med.voll.api.domain.medico;

import med.voll.api.domain.address.Address;

public record DadosUpdateMedico(Long id, String nome,String email, String telefone, String crm, Specialty specialty, Address address ) {
    public DadosUpdateMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getSpecialty(), medico.getAddress());
    }
}
