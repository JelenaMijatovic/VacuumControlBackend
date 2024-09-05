package raf.jmijatovic11421rn.RAFVacuumControl.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import raf.jmijatovic11421rn.RAFVacuumControl.model.Vacuum;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Repository("vacuumRepository")
public class VacuumCustomRepository extends SimpleJpaRepository<Vacuum, Long> implements VacuumRepository{

    @PersistenceContext
    private EntityManager entityManager;
    private final Object LOCK = new Object();

    @Autowired
    public VacuumCustomRepository(EntityManager em) {
        super(Vacuum.class, em);
        this.entityManager = em;
    }

    public VacuumCustomRepository(Class<Vacuum> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }

    /*@Override
    public List<Vacuum> findAll(Specification<Vacuum> spec) {
        return null;
    }*/

    @Override
    public Vacuum findByVacuumId(long id) {
        return this.entityManager.find(Vacuum.class, id);
    }

    @Override
    @Transactional
    public <S extends Vacuum> S save(S entity) {
        if (!entityManager.contains(entity)) {
            entityManager.persist(entity);
        }
        return entity;
    }

}
