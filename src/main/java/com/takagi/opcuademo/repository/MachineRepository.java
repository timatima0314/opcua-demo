package com.takagi.opcuademo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.takagi.opcuademo.entity.Machine;

public interface MachineRepository
        extends JpaRepository<Machine, Long> {

    List<Machine> findByEnabledTrue();

}