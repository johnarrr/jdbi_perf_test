package test;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.*;
import java.time.Instant;

public class ContainerStore {

    private ContainerDAO dao;

    public ContainerStore(DBI dbi) {
        dbi.registerMapper(new ContainerMapper());
        dao = dbi.onDemand(ContainerDAO.class);
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

    public static class ContainerMapper implements ResultSetMapper<Container> {
        @Override
        public Container map(int index, ResultSet r, StatementContext ctx) throws SQLException {
            Container container = new Container();
            container.id = r.getLong("id");
            container.typeValue = Container.Type.valueOf(r.getString("type_value"));
            container.textValue = r.getString("text_value");
            container.varcharValue = r.getString("varchar_value");
            container.intValue = r.getInt("int_value");
            container.longValue = r.getLong("long_value");
            container.booleanValue = r.getBoolean("boolean_value");
            container.datetimeValue = r.getTimestamp("datetime_value").toInstant();
            return container;
        }
    }

    public interface ContainerDAO {
        @SqlUpdate("INSERT INTO container (type_value, text_value, varchar_value, int_value, long_value, " +
                "boolean_value, datetime_value) " +
                "VALUES (:typeValue, :textValue, :varcharValue, :intValue, :longValue, " +
                ":booleanValue, :datetimeValue)")
        @GetGeneratedKeys(columnName = "id")
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
