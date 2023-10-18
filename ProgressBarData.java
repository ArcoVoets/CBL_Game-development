class ProgressBarData {
    public String key;
    public int min;
    public int max;
    public int value;
    public String unit;

    public ProgressBarData(String key, int min, int max, int value,
        String unit) {
        this.min = min;
        this.max = max;
        this.value = value;
        this.unit = unit;
    }

    public ProgressBarData(String key, int min, int max, int value) {
        this(key, min, max, value, null);
    }
}

interface ProgressBarDataProvider {
    public ProgressBarData[] getProgressBarData();
}
