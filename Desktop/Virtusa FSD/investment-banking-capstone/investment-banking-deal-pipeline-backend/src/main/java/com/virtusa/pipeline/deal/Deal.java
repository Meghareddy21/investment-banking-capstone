package com.virtusa.pipeline.deal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document("deals")
public class Deal {

    @Id
    private String id;

    private String clientName;
    private String dealType;
    private String sector;
    private Long dealValue;
    private String currentStage;
    private String summary;

    private Instant createdAt;

    private List<String> notes = new ArrayList<>();

    public Deal() {
        this.createdAt = Instant.now();
    }

    // ===== GETTERS =====

    public String getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public String getDealType() {
        return dealType;
    }

    public String getSector() {
        return sector;
    }

    public Long getDealValue() {
        return dealValue;
    }

    public String getCurrentStage() {
        return currentStage;
    }

    public String getSummary() {
        return summary;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public List<String> getNotes() {
        return notes;
    }

    // ===== SETTERS =====

    public void setId(String id) {
        this.id = id;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public void setDealValue(Long dealValue) {
        this.dealValue = dealValue;
    }

    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }
}
