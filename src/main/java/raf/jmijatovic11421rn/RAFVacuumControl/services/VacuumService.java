package raf.jmijatovic11421rn.RAFVacuumControl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raf.jmijatovic11421rn.RAFVacuumControl.model.Vacuum;
import raf.jmijatovic11421rn.RAFVacuumControl.repositories.VacuumRepository;

import java.util.Date;
import java.util.List;

@Service
public class VacuumService {

    private VacuumRepository vacuumRepository;

    @Autowired
    public VacuumService(VacuumRepository vacuumRepository) {
        this.vacuumRepository = vacuumRepository;
    }

    public List<Vacuum> searchVacuums(String name, List<String> status, Date dateFrom, Date dateTo) {
        return null;
    }

    public boolean startVacuum(Long id) {
        return false;
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
