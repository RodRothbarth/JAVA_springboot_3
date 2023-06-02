package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/medicos")
public class MedicController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity createMedic(@RequestBody @Valid MedicoDTO medicInfo, UriComponentsBuilder uriBuilt){
       var newMedic = new Medico(medicInfo);
               repository.save(newMedic);
        var uri =  uriBuilt.path("/medicos/{id}").buildAndExpand(newMedic.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosUpdateMedico(newMedic));
    }

    @GetMapping("/{id}")
    public ResponseEntity detailMedic(@PathVariable Long id){
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosUpdateMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<ListMedicoDTO>> listDocs(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao){
        var page = repository.findAllByActiveTrue(paginacao).map(ListMedicoDTO::new);

        return ResponseEntity.ok(page);
    }
//    @GetMapping
//    public Page<ListMedicoDTO> listDocs(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao){
//        return repository.findAll(paginacao).map(ListMedicoDTO::new);
//    }

    @PutMapping
    @Transactional
    public ResponseEntity updateMedic(@RequestBody @Valid UpdateMedicoDTO medicInfo){
        var medico = repository.getReferenceById(medicInfo.id());
            medico.updateData(medicInfo);

            return ResponseEntity.ok(new DadosUpdateMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteMedic(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.softDelete();

        return ResponseEntity.noContent().build();
    }

//    @PatchMapping("/{id}")
//    @Transactional
//    public void deleteMedic(@PathVariable Long id){
//        repository.deleteById(id);
//    }
}
