package raf.jmijatovic11421rn.RAFVacuumControl.services;

import raf.jmijatovic11421rn.RAFVacuumControl.model.Status;
import raf.jmijatovic11421rn.RAFVacuumControl.model.Vacuum;
import raf.jmijatovic11421rn.RAFVacuumControl.repositories.VacuumCustomRepository;
import raf.jmijatovic11421rn.RAFVacuumControl.repositories.VacuumRepository;

public class StatusChangeThread extends Thread {

    Vacuum vacuum;
    VacuumRepository vacuumRepository;

    public StatusChangeThread(Vacuum vacuum, VacuumRepository vacuumRepository) {
        super();
        this.vacuum = vacuum;
        this.vacuumRepository = vacuumRepository;
    }

    public void run() {
        try {
            Thread.sleep(15000);
            if (vacuum.getStatus().equals(Status.OFF)) {
                vacuum.setStatus(Status.ON);
            } else if (vacuum.getStatus().equals(Status.ON)) {
                vacuum.setStatus(Status.OFF);
            }
            vacuumRepository.save(vacuum);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
