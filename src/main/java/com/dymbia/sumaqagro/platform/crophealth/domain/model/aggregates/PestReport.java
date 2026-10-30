package com.dymbia.sumaqagro.platform.crophealth.domain.model.aggregates;

import com.dymbia.sumaqagro.platform.crophealth.domain.model.valueobjects.PestReportSeverity;
import com.dymbia.sumaqagro.platform.crophealth.domain.model.valueobjects.PestReportStatus;
import com.dymbia.sumaqagro.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

/**
 * Represents a pest report submitted by a user for a specific plot.
 */

@Getter
@Entity
@Table(name = "pest_reports")
public class PestReport extends AuditableAbstractAggregateRoot<PestReport> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long plotId;

    @Column(nullable = false)
    private Long reporterId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PestReportSeverity severity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PestReportStatus status;

    protected PestReport() {
        // Required by JPA.
    }

    public PestReport(
            Long plotId,
            Long reporterId,
            String description,
            PestReportSeverity severity
    ) {
        if (plotId == null || plotId <= 0) {
            throw new IllegalArgumentException(
                    "Plot ID must be greater than zero"
            );
        }

        if (reporterId == null || reporterId <= 0) {
            throw new IllegalArgumentException(
                    "Reporter ID must be greater than zero"
            );
        }

        if (description == null || description.trim().length() < 10) {
            throw new IllegalArgumentException(
                    "Description must contain at least 10 characters"
            );
        }

        this.plotId = plotId;
        this.reporterId = reporterId;
        this.description = description.trim();
        this.severity = Objects.requireNonNull(
                severity, "Severity is required"
        );
        this.status = PestReportStatus.PENDING;
    }

    public void markInspected() {
        if (status != PestReportStatus.PENDING) {
            throw new IllegalStateException(
                    "Only pending reports can be marked as inspected"
            );
        }

        status = PestReportStatus.INSPECTED;
    }

    public void resolve() {
        if (status != PestReportStatus.INSPECTED) {
            throw new IllegalStateException(
                    "Only inspected reports can be resolved"
            );
        }

        status = PestReportStatus.RESOLVED;
    }
}