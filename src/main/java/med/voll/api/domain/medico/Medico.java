package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    private Boolean active;

    @Embedded
    private Address address;

    public Medico(MedicoDTO medicInfo) {
        this.nome = medicInfo.nome();
        this.crm = medicInfo.crm();
        this.email = medicInfo.email();
        this.telefone = medicInfo.telefone();
        this.specialty = medicInfo.specialty();
        this.active = true;
        this.address = new Address(medicInfo.address());
    }

    public void updateData(UpdateMedicoDTO medicInfo) {

        if(medicInfo.nome() != null){
            this.nome = medicInfo.nome();
        };
        if(medicInfo.telefone() != null){

            this.telefone = medicInfo.telefone();
        };
        if(medicInfo.address() != null){
            this.address.updateAddress(medicInfo.address());
        };

    }

    public void softDelete() {
        this.active = false;
    }
}
