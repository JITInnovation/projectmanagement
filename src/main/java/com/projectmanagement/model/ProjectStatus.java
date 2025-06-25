package com.projectmanagement.model;

public enum ProjectStatus {
    PLANNED("Planned"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed"),
    ON_HOLD("On Hold"),
    HEALTHY("Healthy"),
    UNHEALTHY("Unhealthy"),
    CANCELLED("Cancelled");


    private final String displayName;

    ProjectStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
