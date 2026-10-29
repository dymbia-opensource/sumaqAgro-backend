package com.dymbia.sumaqagro.platform.crophealth.domain.model.aggregates;

import com.dymbia.sumaqagro.platform.crophealth.domain.model.valueobjects.Percentage;
import com.dymbia.sumaqagro.platform.crophealth.infrastructure.persistence.jpa.converters.PercentageAttributeConverter;
import com.dymbia.sumaqagro.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

/**
 * Weather forecast for a specific region and forecast time.
 * Temperature is expressed in degrees Celsius.
 */
@Getter
@Entity
@Table(name = "climate_forecasts")
public class ClimateForecast
        extends AuditableAbstractAggregateRoot<ClimateForecast> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private double temperature;

    @Convert(converter = PercentageAttributeConverter.class)
    @Column(nullable = false)
    private Percentage rainChance;

    @Column(nullable = false)
    private Instant forecastDate;

    @Column(nullable = false)
    private String source;

    protected ClimateForecast() {
        // Required by JPA.
    }

    public ClimateForecast(
            String region,
            double temperature,
            Percentage rainChance,
            Instant forecastDate,
            String source
    ) {
        if (region == null || region.isBlank()) {
            throw new IllegalArgumentException(
                    "Forecast region is required"
            );
        }

        if (!Double.isFinite(temperature) || temperature < -273.15) {
            throw new IllegalArgumentException(
                    "Temperature must be finite and at least -273.15 degrees Celsius"
            );
        }

        if (source == null || source.isBlank()) {
            throw new IllegalArgumentException(
                    "Forecast source is required"
            );
        }

        this.region = region.trim();
        this.temperature = temperature;
        this.rainChance = Objects.requireNonNull(
                rainChance, "Rain probability is required"
        );
        this.forecastDate = Objects.requireNonNull(
                forecastDate, "Forecast time is required"
        );
        this.source = source.trim();
    }
}