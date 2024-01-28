package test;

import java.time.Instant;

public class Container {
    public enum Type {
        type1,
        type2,
        type3
    }

    public long id;
    public Type typeValue;
    public String textValue;
    public String varcharValue;
    public int intValue;
    public long longValue;
    public boolean booleanValue;
    public Instant datetimeValue;
}
