package raf.jmijatovic11421rn.RAFVacuumControl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import raf.jmijatovic11421rn.RAFVacuumControl.model.Status;
import raf.jmijatovic11421rn.RAFVacuumControl.model.Vacuum;
import raf.jmijatovic11421rn.RAFVacuumControl.repositories.VacuumRepository;
import raf.jmijatovic11421rn.RAFVacuumControl.repositories.VacuumSpec;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VacuumService {

    private VacuumRepository vacuumRepository;

    @Autowired
    public VacuumService(VacuumRepository vacuumRepository) {
        this.vacuumRepository = vacuumRepository;
    }

    public List<Vacuum> searchVacuums(String email, String name, List<String> status, Date dateFrom, Date dateTo) {
        Specification<Vacuum> spec1 = VacuumSpec.vacuumNameLike(name);
        Specification<Vacuum> spec2 = VacuumSpec.vacuumStatusIn(status);
        Specification<Vacuum> spec3 = VacuumSpec.vacuumAddedByEquals(email);
        Specification<Vacuum> spec4 = VacuumSpec.vacuumCreationDateBetween(dateFrom, dateTo);
        Specification<Vacuum> spec5 = VacuumSpec.vacuumActiveEquals(true);
        Specification<Vacuum> spec = Specification.where(spec1).or(spec2).or(spec3).or(spec4).or(spec5);
        return vacuumRepository.findAll(spec);
    }

    public void startVacuum(Long id) {
        Optional<Vacuum> v = vacuumRepository.findById(id);
        if (v.isPresent()) {
            Vacuum vacuum = v.get();
            if (vacuum.getStatus().equals(Status.OFF)) {
                vacuum.setStatus(Status.ON);
                vacuumRepository.save(vacuum);
            }
        }
    }

    public boolean stopVacuum(Long id) {
        return false;
    }

    public boolean dischargeVacuum(Long id) {
        return false;
    }

    public Vacuum addVacuum(Vacuum vacuum) {
        return vacuumRepository.save(vacuum);
    }

    public void removeVacuum(Long vacuum) {
        vacuumRepository.deleteById(vacuum);
    }
}
