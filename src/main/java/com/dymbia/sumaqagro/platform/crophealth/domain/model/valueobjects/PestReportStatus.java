package com.dymbia.sumaqagro.platform.crophealth.domain.model.valueobjects;

/**
 * Lifecycle states of a pest report.
 * Transition rules are enforced by the report's domain behavior.
 */
public enum PestReportStatus {

    PENDING,
    INSPECTED,
    RESOLVED
}