package com.takagi.opcuademo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.takagi.opcuademo.entity.Machine;

public interface MachineRepository
        extends JpaRepository<Machine, Long> {

    List<Machine> findByEnabledTrue();

    @Query("""
            select distinct m
            from Machine m
            left join fetch m.tags
            where m.enabled = true
            """)
    List<Machine> findAllEnabledWithTags();
}