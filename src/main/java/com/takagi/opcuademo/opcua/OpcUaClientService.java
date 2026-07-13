package com.takagi.opcuademo.opcua;

import java.time.LocalDateTime;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.springframework.stereotype.Service;

import com.takagi.opcuademo.dto.PlcData;
import com.takagi.opcuademo.entity.TagConfig;
import com.takagi.opcuademo.service.OpcUaClientManager;

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceDescription;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import com.takagi.opcuademo.entity.Machine;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class OpcUaClientService {

    private final OpcUaClientManager clientManager;

    public OpcUaClientService(OpcUaClientManager clientManager) {
        this.clientManager = clientManager;
    }

    public PlcData read(OpcUaClient client, TagConfig tagConfig) throws Exception {

        // Machine machine = tagConfig.getMachine();
        // OpcUaClient client = clientManager.getClient(machine);
        NodeId nodeId = tagConfig.toNodeId();
        DataValue dataValue = client.readValue(
                0.0,
                TimestampsToReturn.Both,
                nodeId);

        Object value = dataValue.getValue().getValue();

        PlcData plcData = new PlcData();

        // plcData.setMachineId(tagConfig.getMachineId());
        plcData.setTagName(tagConfig.getTagName());
        plcData.setValue(value);
        plcData.setDataType(value.getClass().getSimpleName());
        plcData.setReadTime(LocalDateTime.now());

        return plcData;
    }
}