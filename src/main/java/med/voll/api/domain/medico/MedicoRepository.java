package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByActiveTrue(Pageable paginacao);

    @Query("""
                select m from Medico m
                where
                m.active = true
                and
                m.specialty = :especialidade
                and
                m.id not in(
                        select c.medic.id from Consulta c
                        where
                        c.data = :data
                )
                order by rand()
                limit 1
                """)
    Medico chooseRandomMedicByDate(Specialty especialidade, LocalDateTime data);
}
