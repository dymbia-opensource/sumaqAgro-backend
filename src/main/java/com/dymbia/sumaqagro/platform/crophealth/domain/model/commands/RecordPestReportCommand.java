package com.dymbia.sumaqagro.platform.crophealth.domain.model.commands;

import com.dymbia.sumaqagro.platform.crophealth.domain.model.valueobjects.PestReportSeverity;

/**
 * Command to record an agricultural incident for a specific plot.
 */
public record RecordPestReportCommand(
        Long plotId,
        Long reporterId,
        String description,
        PestReportSeverity severity
) {
}