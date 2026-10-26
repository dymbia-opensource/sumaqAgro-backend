
package com.dymbia.sumaqagro.platform.shared.infrastructure.persistence.jpa.configuration;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategySnakeCaseImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import static io.github.encryptorcode.pluralize.Pluralize.pluralize;

public class CustomPhysicalNamingStrategy
        extends PhysicalNamingStrategySnakeCaseImpl {

    @Override
    public Identifier toPhysicalTableName(
            Identifier logicalName,
            JdbcEnvironment context) {

        if (logicalName == null) {
            return null;
        }

        // Convert the table name to snake_case.
        Identifier snakeCaseName =
                super.toPhysicalTableName(logicalName, context);

        // Convert the table name to plural.
        String pluralName = pluralize(snakeCaseName.getText());

        return Identifier.toIdentifier(
                pluralName,
                snakeCaseName.isQuoted()
        );
    }

    @Override
    public Identifier toPhysicalColumnName(
            Identifier logicalName,
            JdbcEnvironment context) {

        // Convert column names to snake_case.
        return super.toPhysicalColumnName(logicalName, context);
    }
}
