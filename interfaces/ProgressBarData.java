package interfaces;

/**
 * Data for progress bars.
 */
public class ProgressBarData {
    public String key;
    public int min;
    public int max;
    public int value;
    public String unit;

    /**
     * Constructor.
     * 
     * @param key The key for the progress bar
     * @param min The minimum value
     * @param max The maximum value
     * @param value The current value
     * @param unit The unit to show after the value
     */
    public ProgressBarData(String key, int min, int max, int value,
        String unit) {
        this.key = key;
        this.min = min;
        this.max = max;
        this.value = value;
        this.unit = unit;
    }

    public ProgressBarData(String key, int min, int max, int value) {
        this(key, min, max, value, null);
    }
}
