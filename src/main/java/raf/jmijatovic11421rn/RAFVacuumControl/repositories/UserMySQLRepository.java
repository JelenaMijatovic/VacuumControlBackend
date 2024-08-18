package raf.jmijatovic11421rn.RAFVacuumControl.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import raf.jmijatovic11421rn.RAFVacuumControl.model.User;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Singleton
@Repository("userRepository")
public class UserMySQLRepository extends SimpleJpaRepository<User, Long> implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private final Object LOCK = new Object();

    @Autowired
    public UserMySQLRepository(EntityManager em) {
        super(User.class, em);
        this.entityManager = em;
    }

    public UserMySQLRepository(Class<User> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }

    @Override
    public User findUserByEmail(String email) {
        synchronized (LOCK) {
            return entityManager.find(this.getDomainClass(), email);
        }
    }

    @Override
    @Transactional
    public void deleteByEmail(String email) {
        synchronized (LOCK) {
            entityManager.remove(entityManager.find(this.getDomainClass(), email));
        }
    }

    @Override
    public List<User> findAll() {
        synchronized (LOCK) {
            return (List<User>) entityManager.createQuery("SELECT u FROM User u").getResultList();
        }
    }

    @Override
    public Page<User> findAll(Pageable page) {
        List<User> users;
        synchronized (LOCK) {
             users = entityManager.createQuery("SELECT u FROM User u")
                    .setFirstResult(page.getPageNumber() * page.getPageSize())
                    .setMaxResults(page.getPageSize()).getResultList();
        }
        return new PageImpl<>(users);
    }

    @Override
    public List<User> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    @Transactional
    public <S extends User> S save(S entity) {
        if (!entityManager.contains(entity)) {
            entityManager.persist(entity);
        }
        return entity;
    }

    @Override
    @Transactional
    public <S extends User> S update(S entity) {
        return entityManager.merge(entity);
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

}
