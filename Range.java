/**
 * A range of numbers.
 */
public class Range {
    private int low;
    private int high;

    /**
     * Create a new range.
     * 
     * @param low Lower border of the range (in percentages, inclusive)
     * @param high Upper border of the range (in percentages, inclusive)
     */
    public Range(int low, int high) {
        if (low > high) {
            throw new IllegalArgumentException("low must be less than or equal to high");
        }
        if (low < 0 || high > 100) {
            throw new IllegalArgumentException(
                "low and high must be percentages (between 0 and 100 (inclusive))");
        }
        this.low = low;
        this.high = high;
    }

    public boolean contains(int number) {
        return (low <= number && number <= high);
    }
}
