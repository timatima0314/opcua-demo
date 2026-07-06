package com.takagi.opcuademo.scheduler;

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.takagi.opcuademo.config.TagConfig;
import com.takagi.opcuademo.opcua.OpcUaClientService;

@Component
public class OpcUaScheduler {

    private final OpcUaClientService opcUaClientService;

    public OpcUaScheduler(OpcUaClientService opcUaClientService) {
        this.opcUaClientService = opcUaClientService;
    }

    @Scheduled(fixedRate = 5000)
    public void collect() throws Exception {

        TagConfig tag = new TagConfig(
                "PLC1",
                "Int32",
                new NodeId(2, "HelloWorld/ScalarTypes/Int32"),
                true
        );

        opcUaClientService.read(tag);

        System.out.println("収集完了");
    }

}