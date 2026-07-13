package com.takagi.opcuademo.scheduler;

import java.util.List;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.takagi.opcuademo.dto.PlcData;
import com.takagi.opcuademo.entity.TagConfig;
import com.takagi.opcuademo.enums.MachineStatus;
import com.takagi.opcuademo.opcua.OpcUaClientService;
import com.takagi.opcuademo.repository.MachineRepository;
import com.takagi.opcuademo.repository.TagConfigRepository;
import com.takagi.opcuademo.entity.Machine;
import com.takagi.opcuademo.service.MachineService;
import com.takagi.opcuademo.service.OpcUaClientManager;
import com.takagi.opcuademo.service.ProductionHistoryService;

@Component
public class OpcUaScheduler {

    private final OpcUaClientService opcUaClientService;
    private final TagConfigRepository tagConfigRepository;
    private final ProductionHistoryService productionHistoryService;
    private final MachineService machineService;
    private final MachineRepository machineRepository;
    private final OpcUaClientManager clientManager;

    public OpcUaScheduler(
            OpcUaClientService opcUaClientService,
            TagConfigRepository tagConfigRepository,
            ProductionHistoryService productionHistoryService,
            MachineService machineService,
            MachineRepository machineRepository,
            OpcUaClientManager clientManager) {

        this.opcUaClientService = opcUaClientService;
        this.tagConfigRepository = tagConfigRepository;
        this.productionHistoryService = productionHistoryService;
        this.machineService = machineService;
        this.machineRepository = machineRepository;
        this.clientManager = clientManager;
    }

    @Scheduled(fixedRate = 5000)
    public void collect() throws Exception {
        // List<Machine> machines = machineRepository.findByEnabledTrue();
        List<Machine> machines = machineRepository.findAllEnabledWithTags();
        for (Machine machine : machines) {
            OpcUaClient client = clientManager.getClient(machine);

            List<TagConfig> tags = machine.getTags();
            for (TagConfig tag : tags) {

                PlcData plcData = opcUaClientService.read(client, tag);

                productionHistoryService.save(plcData);
                
            }
        }
    }

}