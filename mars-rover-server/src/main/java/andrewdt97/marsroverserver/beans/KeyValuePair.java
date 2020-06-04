package andrewdt97.marsroverserver.beans;

/**
 * @author andrewdt97
 *
 */
public class KeyValuePair {
    private String key;
    private String value;

    public KeyValuePair( String k, String v ) {
        this.key = k;
        this.value = v;
    }

    public String getKey() {
        return key;
    }
    public void setKey( String newKey ) {
        this.key = newKey;
    }

    public String getValue() {
        return value;
    }
    public void setValue( String newValue ) {
        this.value = newValue;
    }
}