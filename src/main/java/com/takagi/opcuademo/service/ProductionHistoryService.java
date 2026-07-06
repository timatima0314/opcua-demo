package com.takagi.opcuademo.service;

import org.springframework.stereotype.Service;

import com.takagi.opcuademo.dto.PlcData;
import com.takagi.opcuademo.entity.ProductionHistory;
import com.takagi.opcuademo.repository.ProductionHistoryRepository;

@Service
public class ProductionHistoryService {

    private final ProductionHistoryRepository repository;

    public ProductionHistoryService(
            ProductionHistoryRepository repository) {

        this.repository = repository;
    }

    public void save(PlcData plcData) {

        ProductionHistory history = new ProductionHistory();

        history.setMachineId(plcData.getMachineId());
        history.setTagName(plcData.getTagName());
        history.setValue(plcData.getValue().toString());
        history.setReadTime(plcData.getReadTime());

        repository.save(history);

    }

}