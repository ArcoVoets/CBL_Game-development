package data;

import interfaces.ProgressBarData;
import interfaces.ProgressBarDataProvider;
import java.util.AbstractList;

class CodesContainer extends AbstractList<Code>
    implements ProgressBarDataProvider {

    private final Code[] a;
    public int averageColdResistance = 0;
    public int averageHeatResistance = 0;
    public int averageLightSensitivity = 0;

    /**
     * Generates a random codes container.
     * 
     * @param length The amount of codes to generate
     * @return The generated codes container
     */
    public static CodesContainer randomCodesContainer(int length) {
        Code[] codes = new Code[length];
        for (int i = 0; i < length; i++) {
            codes[i] = Code.generateRandomCode();
        }
        return new CodesContainer(codes);
    }

    /**
     * Constructor.
     * 
     * @param array The array of codes
     */
    public CodesContainer(Code[] array) {
        a = array;
        updateAverages();
    }

    public Code get(int index) {
        return a[index];
    }

    /**
     * Sets the code at the specified index.
     * 
     * @param index The index of the code
     * @param element The code
     */
    public Code set(int index, Code element) {
        Code oldValue = a[index];
        a[index] = element;
        updateAverages();
        return oldValue;
    }

    /**
     * Updates the code averages.
     */
    void updateAverages() {
        int totalHeatResistance = 0;
        int totalColdResistance = 0;
        int totalLightSensitivity = 0;
        for (Code code : a) {
            totalHeatResistance += code.heatResistance;
            totalColdResistance += code.coldResistance;
            totalLightSensitivity += code.lightSensitivity;
        }
        averageHeatResistance = totalHeatResistance / a.length;
        averageColdResistance = totalColdResistance / a.length;
        averageLightSensitivity = totalLightSensitivity / a.length;
    }

    /**
     * Calculates the total score of the codes.
     * 
     * Remark: A higher cold resistance, means that the creatures resistance
     * against cold is lower, therefore, the cold resistance is subtracted from
     * the score.
     *
     * @return The total score of the codes
     */
    public int calculateCodesScore() {
        int score = 0;
        for (Code code : a) {
            score -= code.coldResistance;
            score += code.heatResistance;
            score += code.lightSensitivity;
        }
        return score;
    }

    /**
     * Generates progress bar data for the codes.
     * 
     * @return The progress bar data for the codes
     */
    public ProgressBarData[] getProgressBarData() {
        return new ProgressBarData[] {
            new ProgressBarData("Heat resistance", 0, Code.MAX_CODE_VALUE,
                averageHeatResistance, "°C"),
            new ProgressBarData("Cold resistance", 0, Code.MAX_CODE_VALUE,
                averageColdResistance, "°C"),
            new ProgressBarData("Light sensitivity", 0, Code.MAX_CODE_VALUE,
                averageLightSensitivity)
        };
    }

    public int size() {
        return a.length;
    }
}
