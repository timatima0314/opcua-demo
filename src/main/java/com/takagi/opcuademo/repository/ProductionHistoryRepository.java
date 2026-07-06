package com.takagi.opcuademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.takagi.opcuademo.entity.ProductionHistory;

public interface ProductionHistoryRepository
        extends JpaRepository<ProductionHistory, Long> {

}
