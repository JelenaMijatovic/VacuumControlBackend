package raf.jmijatovic11421rn.RAFVacuumControl.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import raf.jmijatovic11421rn.RAFVacuumControl.model.ErrorMessage;

import javax.ejb.Singleton;

@Singleton
@Repository
public interface ErrorMessageRepository extends CrudRepository<ErrorMessage, Long> {

}