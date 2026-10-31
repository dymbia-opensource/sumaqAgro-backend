package com.dymbia.sumaqagro.platform.fieldmanagement.interfaces.rest.transform;

import com.dymbia.sumaqagro.platform.fieldmanagement.domain.model.commands.RegisterFieldPlotCommand;
import com.dymbia.sumaqagro.platform.fieldmanagement.interfaces.rest.resources.RegisterFieldPlotResource;

import java.util.Objects;

public final class RegisterFieldPlotCommandFromResourceAssembler {
    private RegisterFieldPlotCommandFromResourceAssembler() {
    }
    public static RegisterFieldPlotCommand toCommandFromResource(RegisterFieldPlotResource resource) {
        Objects.requireNonNull(resource, "Register field plot request body is required.");
        return new RegisterFieldPlotCommand(
                resource.ownerUserId(),
                resource.name(),
                resource.region(),
                resource.areaHectares());
    }
}