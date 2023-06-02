package med.voll.api.domain.medico;

public record ListMedicoDTO(String nome, String email, String crm, Specialty specialty, Long id ) {

    public ListMedicoDTO(Medico medico){
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getSpecialty(), medico.getId());
    }
}
