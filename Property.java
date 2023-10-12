/**
 * Property.
 */
public class Property {
    public String key;
    public int value;
    public int maxValue;

    /**
     * Constructor.
     * 
     * @param key      Key (name) of the property
     * @param value    Current value of the property
     * @param maxValue Maximum value of the property
     */
    Property(String key, int value, int maxValue) {
        this.key = key;
        this.value = value;
        this.maxValue = maxValue;
    }
}

