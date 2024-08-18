package raf.jmijatovic11421rn.RAFVacuumControl.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import raf.jmijatovic11421rn.RAFVacuumControl.model.User;

import java.util.List;

@NoRepositoryBean
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByEmail(String email);

    void deleteByEmail(String email);

    <S extends User> S update(S entity);

    List<User> findAll();

    Page<User> findAll(Pageable pageable);

}
