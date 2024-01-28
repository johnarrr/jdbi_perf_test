package test;

import org.jdbi.v3.core.Jdbi;

public class ContainerStoreFluent {

    private final Jdbi jdbi;

    public ContainerStoreFluent(Jdbi jdbi) {
        this.jdbi = jdbi;
        jdbi.registerRowMapper(new ContainerMapper());
    }

    public long insert(Container container) {
        return jdbi.withHandle(handle -> {
            return handle.createUpdate(
                            "INSERT INTO container (type_value, text_value, varchar_value, int_value, long_value, " +
                                    "boolean_value, datetime_value) " +
                                    "VALUES (:typeValue, :textValue, :varcharValue, :intValue, :longValue, " +
                                    ":booleanValue, :datetimeValue)"
                    )
                    .bind("typeValue", container.typeValue)
                    .bind("textValue", container.textValue)
                    .bind("varcharValue", container.varcharValue)
                    .bind("intValue", container.intValue)
                    .bind("longValue", container.longValue)
                    .bind("booleanValue", container.booleanValue)
                    .bind("datetimeValue", container.datetimeValue)
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(long.class)
                    .one();
        });
    }

    public void update(Container container) {
        jdbi.withHandle(handle -> {
            return handle.createUpdate(
                            "UPDATE container SET type_value = :typeValue, text_value = :textValue, varchar_value = :varcharValue, " +
                                    "int_value = :intValue, long_value = :longValue, boolean_value = :booleanValue, " +
                                    "datetime_value = :datetimeValue WHERE id = :id"
                    )
                    .bind("id", container.id)
                    .bind("typeValue", container.typeValue)
                    .bind("textValue", container.textValue)
                    .bind("varcharValue", container.varcharValue)
                    .bind("intValue", container.intValue)
                    .bind("longValue", container.longValue)
                    .bind("booleanValue", container.booleanValue)
                    .bind("datetimeValue", container.datetimeValue);
        });
    }

    public void updateTypeValue(Container container) {
        jdbi.withHandle(handle -> {
            return handle.createUpdate("UPDATE container SET type_value = :typeValue WHERE id = :id")
                    .bind("id", container.id)
                    .bind("typeValue", container.typeValue);
        });
    }

    public void updateTextValue(Container container) {
        jdbi.withHandle(handle -> {
            return handle.createUpdate("UPDATE container SET text_value = :textValue  WHERE id = :id")
                    .bind("id", container.id)
                    .bind("textValue", container.textValue);
        });
    }

    public void updateVarcharValue(Container container) {
        jdbi.withHandle(handle -> {
            return handle.createUpdate("UPDATE container SET varchar_value = :varcharValue WHERE id = :id")
                    .bind("id", container.id)
                    .bind("varcharValue", container.varcharValue);
        });
    }

    public void updateIntValue(Container container) {
        jdbi.withHandle(handle -> {
            return handle.createUpdate("UPDATE container SET int_value = :intValue WHERE id = :id")
                    .bind("id", container.id)
                    .bind("intValue", container.intValue);
        });
    }

    public void updateLongValue(Container container) {
        jdbi.withHandle(handle -> {
            return handle.createUpdate("UPDATE container SET long_value = :longValue WHERE id = :id")
                    .bind("id", container.id)
                    .bind("longValue", container.longValue);
        });
    }

    public void updateBooleanValue(Container container) {
        jdbi.withHandle(handle -> {
            return handle.createUpdate("UPDATE container SET boolean_value = :booleanValue WHERE id = :id")
                    .bind("id", container.id)
                    .bind("booleanValue", container.booleanValue);
        });
    }

    public void updateDatetimeValue(Container container) {
        jdbi.withHandle(handle -> {
            return handle.createUpdate("UPDATE container SET datetime_value = :datetimeValue WHERE id = :id")
                    .bind("id", container.id)
                    .bind("datetimeValue", container.datetimeValue);
        });
    }

    public Container get(long ContainerId) {
        return jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM container WHERE id = :id")
                    .bind("id", ContainerId)
                    .mapTo(Container.class)
                    .one();
        });
    }
}
