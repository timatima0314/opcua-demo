package com.takagi.opcuademo.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.takagi.opcuademo.dto.PlcData;
import com.takagi.opcuademo.entity.TagConfig;
import com.takagi.opcuademo.opcua.OpcUaClientService;
import com.takagi.opcuademo.repository.TagConfigRepository;
import com.takagi.opcuademo.service.ProductionHistoryService;

@Component
public class OpcUaScheduler {

    private final OpcUaClientService opcUaClientService;
    private final TagConfigRepository tagConfigRepository;
    private final ProductionHistoryService productionHistoryService;

    public OpcUaScheduler(
            OpcUaClientService opcUaClientService,
            TagConfigRepository tagConfigRepository,
            ProductionHistoryService productionHistoryService) {

        this.opcUaClientService = opcUaClientService;
        this.tagConfigRepository = tagConfigRepository;
        this.productionHistoryService = productionHistoryService;
    }

    @Scheduled(fixedRate = 5000)
    public void collect() throws Exception {

        List<TagConfig> tags = tagConfigRepository.findByEnabledTrue();

        for (TagConfig tag : tags) {

            PlcData plcData = opcUaClientService.read(tag);

            productionHistoryService.save(plcData);

        }

        System.out.println("収集完了");
    }

}