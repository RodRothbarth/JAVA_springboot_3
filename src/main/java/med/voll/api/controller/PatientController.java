package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pecientes")
public class PatientController {
    @Autowired
    PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity createPatient(@RequestBody @Valid PacienteDTO patientInfo, UriComponentsBuilder uriBuilt){
        var newPatient = new Paciente(patientInfo);
        repository.save(newPatient);
        var uri= uriBuilt.path("/pacientes/{id}").buildAndExpand(newPatient.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosUpdatePaciente(newPatient));
    }

    @GetMapping("/{id}")
    public ResponseEntity detailPatient(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosUpdatePaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<ListPacienteDTO>> listPatients(@PageableDefault(size=10, page = 0, sort = {"nome"})Pageable paginacao){
        var page = repository.findAllByActiveTrue(paginacao).map(ListPacienteDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updatePatient(@RequestBody @Valid UpdatePacienteDTO patientInfo){
        var paciente = repository.getReferenceById(patientInfo.id());
        paciente.updateData(patientInfo);

        return ResponseEntity.ok(new DadosUpdatePaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePatient(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.softDelete();

        return ResponseEntity.noContent().build();
    }
}
