package com.takagi.opcuademo.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.takagi.opcuademo.entity.TagConfig;
import com.takagi.opcuademo.opcua.OpcUaClientService;
import com.takagi.opcuademo.repository.TagConfigRepository;

@Component
public class OpcUaScheduler {

    private final OpcUaClientService opcUaClientService;
    private final TagConfigRepository tagConfigRepository;

    public OpcUaScheduler(
            OpcUaClientService opcUaClientService,
            TagConfigRepository tagConfigRepository) {

        this.opcUaClientService = opcUaClientService;
        this.tagConfigRepository = tagConfigRepository;
    }

    @Scheduled(fixedRate = 5000)
    public void collect() throws Exception {

        List<TagConfig> tags = tagConfigRepository.findByEnabledTrue();

        for (TagConfig tag : tags) {

            opcUaClientService.read(tag);

        }

        System.out.println("収集完了");
    }

}