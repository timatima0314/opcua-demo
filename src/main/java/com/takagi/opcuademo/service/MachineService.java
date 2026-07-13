package com.takagi.opcuademo.service;

import org.springframework.stereotype.Service;

import com.takagi.opcuademo.entity.Machine;
import com.takagi.opcuademo.enums.MachineStatus;
import com.takagi.opcuademo.repository.MachineRepository;

@Service
public class MachineService {

    private final MachineRepository machineRepository;

    public MachineService(
            MachineRepository machineRepository) {

        this.machineRepository = machineRepository;
    }

    public void updateStatus(
            Machine machine,
            MachineStatus status) {

        machine.setStatus(status);

        machineRepository.save(machine);
    }
}