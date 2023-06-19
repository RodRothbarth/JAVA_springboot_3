package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

@Table(name ="pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;

    private String cpf;
    @Embedded
    private Address address;

    private Boolean active;

    public Paciente(PacienteDTO pacienteInfo){
        this.address = new Address(pacienteInfo.address());
        this.email = pacienteInfo.email();
        this.cpf = pacienteInfo.cpf();
        this.telefone = pacienteInfo.telefone();
        this.nome = pacienteInfo.nome();
        this.active = true;
    }

    public void softDelete() {
        this.active = false;
    }
}
