package raf.jmijatovic11421rn.RAFVacuumControl.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import raf.jmijatovic11421rn.RAFVacuumControl.model.Vacuum;

@NoRepositoryBean
public interface VacuumRepository extends CrudRepository<Vacuum, Long> {

}
