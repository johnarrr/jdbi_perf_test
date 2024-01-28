CREATE TABLE unit_bean_test
(
    id   BIGSERIAL PRIMARY KEY,
    unit TEXT
);

CREATE TABLE container
(
    id             BIGSERIAL PRIMARY KEY,
    type_value     TEXT,
    text_value     TEXT,
    varchar_value  VARCHAR(50),
    int_value      INTEGER,
    long_value     BIGINT,
    boolean_value  BOOLEAN,
    datetime_value timestamp with time zone
);
