package com.takagi.opcuademo.opcua;

import java.time.LocalDateTime;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.springframework.stereotype.Service;

import com.takagi.opcuademo.dto.PlcData;
import com.takagi.opcuademo.entity.TagConfig;

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceDescription;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class OpcUaClientService {

    private OpcUaClient client;

    @PostConstruct
    public void init() throws Exception {

        connect();

    }

    @PreDestroy
    public void close() throws Exception {

        disconnect();

    }

    public void connect() throws Exception {

        String endpoint = "opc.tcp://127.0.0.1:12686/milo";

        client = OpcUaClient.create(endpoint);

        client.connect();

        System.out.println("OPC UA 接続成功");
    }

    public void disconnect() throws Exception {
        if (client != null) {
            client.disconnect();
        }
    }

    public void browse(NodeId nodeId) throws Exception {

        UaNode node = client.getAddressSpace().getNode(nodeId);

        System.out.println("ノード名：" + node.getBrowseName().getName());

        System.out.println("===== 子ノード一覧 =====");

        for (ReferenceDescription ref : node.browse()) {
            System.out.println(ref.getBrowseName().getName());
            System.out.println(ref);
        }
    }

    public PlcData read(TagConfig tagConfig) throws Exception {

        NodeId nodeId = tagConfig.toNodeId();
        DataValue dataValue = client.readValue(
                0.0,
                TimestampsToReturn.Both,
                nodeId);

        Object value = dataValue.getValue().getValue();

        PlcData plcData = new PlcData();

        plcData.setMachineId(tagConfig.getMachineId());
        plcData.setTagName(tagConfig.getTagName());
        plcData.setValue(value);
        plcData.setDataType(value.getClass().getSimpleName());
        plcData.setReadTime(LocalDateTime.now());

        return plcData;
    }
}