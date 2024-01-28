package test;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcConnectionPool;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import javax.sql.DataSource;

public class JdbiFactory {
    public static Jdbi getJdbi() {
        DataSource dataSource = JdbcConnectionPool.create("jdbc:h2:mem:test", null, null);
        Jdbi jdbi = Jdbi.create(dataSource);
        jdbi.installPlugin(new SqlObjectPlugin());

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();

        return jdbi;
    }
}
