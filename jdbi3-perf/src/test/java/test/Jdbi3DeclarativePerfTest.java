package test;

import org.assertj.core.api.Assertions;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class Jdbi3DeclarativePerfTest {

    private static Jdbi jdbi;
    private static ContainerStoreDeclarative store;

    private int perfTestCount = 25000;

    @BeforeAll
    public static void beforeAll() {
        jdbi = JdbiFactory.getJdbi();
        assertThat(jdbi).isNotNull();

        store = new ContainerStoreDeclarative(jdbi);
    }

    @Test
    public void testAConnection() {
        // trigger H2 db setup to separate the one-time cost from other tests
    }

    @Test
    public void testGet() {
        Container container = createContainer();

        for (int i = 0; i < perfTestCount; ++i) {
            store.get(container.id);
        }
    }

    @Test
    public void testInsert() {
        for (int i = 0; i < perfTestCount; ++i) {
            createContainer();
        }
    }

    @Test
    public void testUpdate() {
        Container container = createContainer();
        for (int i = 0; i < perfTestCount; ++i) {
            store.update(container);
        }
    }

    @Test
    public void testTypeValue() {
        Container container = createContainer();
        for (int i = 0; i < perfTestCount; ++i) {
            store.updateTypeValue(container);
        }
    }

    @Test
    public void testUpdateTextValue() {
        Container container = createContainer();
        for (int i = 0; i < perfTestCount; ++i) {
            store.updateTextValue(container);
        }
    }

    @Test
    public void testUpdateVarcharValue() {
        Container container = createContainer();
        for (int i = 0; i < perfTestCount; ++i) {
            store.updateVarcharValue(container);
        }
    }

    @Test
    public void testUpdateIntValue() {
        Container container = createContainer();
        for (int i = 0; i < perfTestCount; ++i) {
            store.updateIntValue(container);
        }
    }

    @Test
    public void testUpdateLongValue() {
        Container container = createContainer();
        for (int i = 0; i < perfTestCount; ++i) {
            store.updateLongValue(container);
        }
    }

    @Test
    public void testUpdateBooleanValue() {
        Container container = createContainer();
        for (int i = 0; i < perfTestCount; ++i) {
            store.updateBooleanValue(container);
        }
    }

    @Test
    public void testUpdateDatetimeValue() {
        Container container = createContainer();
        for (int i = 0; i < perfTestCount; ++i) {
            store.updateDatetimeValue(container);
        }
    }

    private Container createContainer() {
        Container unsaved = buildContainer();
        long id = store.insert(unsaved);

        Container container = store.get(id);
        Assertions.assertThat(container).isNotNull();
        return container;
    }

    private Container buildContainer() {
        Instant now = Instant.now();
        Container container = new Container();
        container.id = 1111;
        container.typeValue = Container.Type.type2;
        container.textValue = "text-value";
        container.varcharValue = "varchar-value";
        container.intValue = 2222;
        container.longValue = 3333L;
        container.booleanValue = true;
        container.datetimeValue = now;
        return container;
    }
}
