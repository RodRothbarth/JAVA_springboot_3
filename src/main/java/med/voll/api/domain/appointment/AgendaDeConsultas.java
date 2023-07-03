package med.voll.api.domain.appointment;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.patient.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {
    @Autowired
    private AppointmentRepository consultaRepository;
    @Autowired
    private MedicoRepository medicRepository;
    @Autowired
    private PacienteRepository patientRepository;
    public void agendar(AppointmentDto dados){
        if(!patientRepository.existsById(dados.idPatient())){
            throw new ValidacaoException("Id do paciente não encontrado!");
        }
        if(dados.idMedic() != null && !medicRepository.existsById(dados.idMedic())){
            throw new ValidacaoException("Id do médico não encontrado!");
        }


        var paciente = patientRepository.findById(dados.idPatient()).get();
        var medico = chooseMedic(dados);

        var consulta = new Appointment(null, medico, paciente, dados.data(), null);

        consultaRepository.save(consulta);
    }

    private Medico chooseMedic(AppointmentDto dados) {
        if(dados.idMedic()!= null){
            return medicRepository.getReferenceById(dados.idMedic());
        }

        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade não definida");
        }

        return medicRepository.chooseRandomMedicByDate(dados.especialidade(), dados.data());
    }

    public void cancelar(CancelamentoDeConsultasDto dados){
        if(!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Consulta não existente");
        }

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.reason());
    }


}
