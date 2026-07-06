package com.takagi.opcuademo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

import com.takagi.opcuademo.dto.PlcData;
import com.takagi.opcuademo.entity.TagConfig;
import com.takagi.opcuademo.opcua.OpcUaClientService;

@RestController
public class OpcUaController {

    private final OpcUaClientService opcUaClientService;

    public OpcUaController(OpcUaClientService opcUaClientService) {
        this.opcUaClientService = opcUaClientService;
    }

    // 起動時に自動接続するため現在はテスト用
    @GetMapping("/opcua/connect")
    public String connect() {
        try {
            opcUaClientService.connect();
            return "OPC UA 接続成功";
        } catch (Exception e) {
            return "接続失敗: " + e.getMessage();
        }
    }

    @GetMapping("/opcua/browse")
    public String browse() {
        try {
            // opcUaClientService.browse(NodeIds.ObjectsFolder);
            // opcUaClientService.browse(new NodeId(2, "HelloWorld"));
            opcUaClientService.browse(new NodeId(2, "HelloWorld/ScalarTypes/Int32"));
            return "Browse成功";
        } catch (Exception e) {
            return "Browse失敗: " + e.getMessage();
        }
    }

    @GetMapping("/opcua/read")
    public PlcData read() {
        TagConfig tagConfig = new TagConfig();
        tagConfig.setMachineId("PLC1");
        tagConfig.setTagName("Int32");
        tagConfig.setNamespaceIndex(2);
        tagConfig.setIdentifier("HelloWorld/ScalarTypes/Int32");
        tagConfig.setEnabled(true);
        try {
            PlcData plcData = opcUaClientService.read(tagConfig);

            return plcData;
        } catch (Exception e) {
            return null;
        }
    }
}