package data;

/**
 * Property.
 */
class Property {
    private String key;
    private int value;
    private int minValue;
    private int maxValue;
    private String unit;

    /**
     * Constructor.
     * 
     * @param key Key (name) of the property
     * @param value Current value of the property
     * @param maxValue Maximum value of the property
     */
    Property(String key, int value, int maxValue) {
        this(key, value, 0, maxValue, null);
    }

    /**
     * Constructor.
     * 
     * @param key Key (name) of the property
     * @param value Current value of the property
     * @param minValue Minimum value of the property
     * @param maxValue Maximum value of the property
     * @param unit Unit of the property
     */
    Property(String key, int value, int minValue, int maxValue, String unit) {
        this.key = key;
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.unit = unit;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Adds a value to the property.
     * 
     * @param value The value to add
     */
    public void addValue(int value) {
        this.value += value;
        if (this.value > maxValue) {
            this.value = maxValue;
        }
    }

    /**
     * Subtracts a value from the property.
     * 
     * @param value The value to subtract
     */
    public void subtractValue(int value) {
        this.value -= value;
        if (this.value < minValue) {
            this.value = minValue;
        }
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public String getUnit() {
        return unit;
    }
}
