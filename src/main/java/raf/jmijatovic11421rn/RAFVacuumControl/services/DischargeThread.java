package raf.jmijatovic11421rn.RAFVacuumControl.services;

import raf.jmijatovic11421rn.RAFVacuumControl.model.Status;
import raf.jmijatovic11421rn.RAFVacuumControl.model.Vacuum;
import raf.jmijatovic11421rn.RAFVacuumControl.repositories.VacuumRepository;

public class DischargeThread extends Thread {

    Vacuum vacuum;
    VacuumRepository vacuumRepository;

    public DischargeThread(Vacuum vacuum, VacuumRepository vacuumRepository) {
        super();
        this.vacuum = vacuum;
        this.vacuumRepository = vacuumRepository;
    }

    public void run() {
        try {
            Thread.sleep(15000);
            vacuum.setStatus(Status.DISCHARGING);
            vacuumRepository.save(vacuum);
            Thread.sleep(15000);
            vacuum.setStatus(Status.OFF);
            vacuumRepository.save(vacuum);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
