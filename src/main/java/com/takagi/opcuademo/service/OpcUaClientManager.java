package com.takagi.opcuademo.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.springframework.stereotype.Service;

import com.takagi.opcuademo.entity.Machine;

@Service
public class OpcUaClientManager {

    private final Map<String, OpcUaClient> clients = new ConcurrentHashMap<>();

    public OpcUaClient getClient(Machine machine) throws Exception {

        String endpoint = machine.getEndpoint();

        OpcUaClient client = clients.get(endpoint);

        if (client != null) {
            return client;
        }

        client = OpcUaClient.create(endpoint);
        client.connect();

        clients.put(endpoint, client);


        return client;
    }
}