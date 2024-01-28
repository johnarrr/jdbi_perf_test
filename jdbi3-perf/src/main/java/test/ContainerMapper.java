package test;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContainerMapper implements RowMapper<Container> {
    @Override
    public Container map(ResultSet r, StatementContext ctx) throws SQLException {
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
