package com.dymbia.sumaqagro.platform.fieldmanagement.interfaces.rest.resources;

import java.math.BigDecimal;

public record FieldPlotResource(
        Long id,
        Long ownerUserId,
        String name,
        String region,
        String status,
        BigDecimal areaHectares,
        String agroMonitoringPolygonId) {
}
