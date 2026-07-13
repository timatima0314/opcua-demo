package com.takagi.opcuademo.entity;

import com.takagi.opcuademo.enums.MachineStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "machine")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String machineCode;

    @Column(nullable = false)
    private String machineName;

    @Column(nullable = false)
    private String endpoint;

    @Column(nullable = false)
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private MachineStatus status;

    @OneToMany(mappedBy = "machine")
    private List<TagConfig> tags = new ArrayList<>();

    public Machine() {
    }

    public List<TagConfig> getTags() {
        return tags;
    }

    public void setTags(List<TagConfig> tags) {
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public MachineStatus getStatus() {
        return status;
    }

    public void setStatus(MachineStatus status) {
        this.status = status;
    }
}