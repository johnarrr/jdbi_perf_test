package test;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.Instant;

public class ContainerStoreDeclarative {

    private final ContainerDAO dao;

    public ContainerStoreDeclarative(Jdbi jdbi) {
        this.dao = jdbi.onDemand(ContainerDAO.class);
        jdbi.registerRowMapper(new ContainerMapper());
    }

    public long insert(Container container) {
        return dao.insert(
                container.typeValue,
                container.textValue,
                container.varcharValue,
                container.intValue,
                container.longValue,
                container.booleanValue,
                container.datetimeValue
        );
    }

    public void update(Container container) {
        dao.update(
                container.id,
                container.typeValue,
                container.textValue,
                container.varcharValue,
                container.intValue,
                container.longValue,
                container.booleanValue,
                container.datetimeValue
        );
    }

    public void updateTypeValue(Container container) {
        dao.updateTypeValue(container.id, container.typeValue);
    }

    public void updateTextValue(Container container) {
        dao.updateTextValue(container.id, container.textValue);
    }

    public void updateVarcharValue(Container container) {
        dao.updateVarcharValue(container.id, container.varcharValue);
    }

    public void updateIntValue(Container container) {
        dao.updateIntValue(container.id, container.intValue);
    }

    public void updateLongValue(Container container) {
        dao.updateLongValue(container.id, container.longValue);
    }

    public void updateBooleanValue(Container container) {
        dao.updateBooleanValue(container.id, container.booleanValue);
    }

    public void updateDatetimeValue(Container container) {
        dao.updateDatetime(container.id, container.datetimeValue);
    }

    public Container get(long ContainerId) {
        return dao.getById(ContainerId);
    }

    public interface ContainerDAO {
        @SqlUpdate("INSERT INTO container (type_value, text_value, varchar_value, int_value, long_value, " +
                "boolean_value, datetime_value) " +
                "VALUES (:typeValue, :textValue, :varcharValue, :intValue, :longValue, " +
                ":booleanValue, :datetimeValue)")
        @GetGeneratedKeys(value = "id")
        long insert(@Bind("typeValue") Container.Type type,
                    @Bind("textValue") String textValue,
                    @Bind("varcharValue") String varcharValue,
                    @Bind("intValue") int intValue,
                    @Bind("longValue") long longValue,
                    @Bind("booleanValue") boolean booleanValue,
                    @Bind("datetimeValue") Instant datetimeValue);

        @SqlUpdate("UPDATE container SET type_value = :typeValue, text_value = :textValue, varchar_value = :varcharValue, " +
                "int_value = :intValue, long_value = :longValue, boolean_value = :booleanValue, " +
                "datetime_value = :datetimeValue WHERE id = :id")
        void update(@Bind("id") long id,
                    @Bind("typeValue") Container.Type type,
                    @Bind("textValue") String textValue,
                    @Bind("varcharValue") String varcharValue,
                    @Bind("intValue") int intValue,
                    @Bind("longValue") long longValue,
                    @Bind("booleanValue") boolean booleanValue,
                    @Bind("datetimeValue") Instant datetimeValue);

        @SqlUpdate("UPDATE container SET type_value = :typeValue WHERE id = :id")
        void updateTypeValue(@Bind("id") long id, @Bind("typeValue") Container.Type type);

        @SqlUpdate("UPDATE container SET text_value = :textValue  WHERE id = :id")
        void updateTextValue(@Bind("id") long id, @Bind("textValue") String textValue);

        @SqlUpdate("UPDATE container SET varchar_value = :varcharValue WHERE id = :id")
        void updateVarcharValue(@Bind("id") long id, @Bind("varcharValue") String varcharValue);

        @SqlUpdate("UPDATE container SET int_value = :intValue WHERE id = :id")
        void updateIntValue(@Bind("id") long id, @Bind("intValue") int intValue);

        @SqlUpdate("UPDATE container SET long_value = :longValue WHERE id = :id")
        void updateLongValue(@Bind("id") long id, @Bind("longValue") long longValue);

        @SqlUpdate("UPDATE container SET boolean_value = :booleanValue WHERE id = :id")
        void updateBooleanValue(@Bind("id") long id, @Bind("booleanValue") boolean booleanValue);

        @SqlUpdate("UPDATE container SET datetime_value = :datetimeValue WHERE id = :id")
        void updateDatetime(@Bind("id") long id, @Bind("datetimeValue") Instant datetimeValue);

        @SqlQuery("SELECT * FROM container WHERE id = :id")
        Container getById(@Bind("id") long id);
    }
}
