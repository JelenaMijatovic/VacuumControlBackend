package raf.jmijatovic11421rn.RAFVacuumControl.services;

public interface WaitingThreadListener {
    void notify(final Thread thread, Long id, String action);
}