package com.takagi.opcuademo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@Entity
public class TagConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "machine_id", nullable = false)
    private Machine machine;

    private String tagName;

    private Integer namespaceIndex;

    private String identifier;

    private boolean enabled;

    public TagConfig() {
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public String getTagName() {
        return tagName;
    }

    public Integer getNamespaceIndex() {
        return namespaceIndex;
    }

    public String getIdentifier() {
        return identifier;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setNamespaceIndex(Integer namespaceIndex) {
        this.namespaceIndex = namespaceIndex;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public NodeId toNodeId() {
        return new NodeId(namespaceIndex, identifier);
    }
}