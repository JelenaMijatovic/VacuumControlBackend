package raf.jmijatovic11421rn.RAFVacuumControl.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import raf.jmijatovic11421rn.RAFVacuumControl.model.Vacuum;

import java.util.List;

@NoRepositoryBean
public interface VacuumRepository extends CrudRepository<Vacuum, Long> {

    List<Vacuum> findAll(Specification<Vacuum> spec);
    List<Vacuum> findAllByAddedBy(String email);
    Vacuum findByVacuumId(long id);

    <S extends Vacuum> void update(S entity);
    void lock(Vacuum v);
}
