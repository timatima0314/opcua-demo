package com.takagi.opcuademo.config;

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class TagConfig {

    private String machineId;
    private String tagName;
    private NodeId nodeId;
    private boolean enabled;

    public TagConfig() {
    }

    public TagConfig(
            String machineId,
            String tagName,
            NodeId nodeId,
            boolean enabled) {

        this.machineId = machineId;
        this.tagName = tagName;
        this.nodeId = nodeId;
        this.enabled = enabled;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public NodeId getNodeId() {
        return nodeId;
    }

    public void setNodeId(NodeId nodeId) {
        this.nodeId = nodeId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
