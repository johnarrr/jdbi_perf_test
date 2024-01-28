package test;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcConnectionPool;
import org.skife.jdbi.v2.DBI;

import javax.sql.DataSource;

public class JdbiFactory {
    public static DBI getJdbi() {
        DataSource dataSource = JdbcConnectionPool.create("jdbc:h2:mem:test", null, null);
        DBI dbi = new DBI(dataSource);

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();

        return dbi;
    }
}
