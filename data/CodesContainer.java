package data;

import interfaces.ProgressBarData;
import interfaces.ProgressBarDataProvider;
import java.util.AbstractList;

class CodesContainer extends AbstractList<Code>
    implements ProgressBarDataProvider {

    private final Code[] a;
    public int averageHeatResistance = 0;
    public int averageColdResistance = 0;
    public int averageLightSensitivity = 0;
    public int averageMaxEnergy = 0;

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
     */
    public Code set(int index, Code element) {
        Code oldValue = a[index];
        a[index] = element;
        updateAverages();
        return oldValue;
    }

    /**
     * Updates the averages.
     */
    void updateAverages() {
        int totalHeatResistance = 0;
        int totalColdResistance = 0;
        int totalLightSensitivity = 0;
        int totalMaxEnergy = 0;
        for (Code code : a) {
            totalHeatResistance += code.heatResistance;
            totalColdResistance += code.coldResistance;
            totalLightSensitivity += code.lightSensitivity;
            totalMaxEnergy += code.maxEnergy;
        }
        averageHeatResistance = totalHeatResistance / a.length;
        averageColdResistance = totalColdResistance / a.length;
        averageLightSensitivity = totalLightSensitivity / a.length;
        averageMaxEnergy = totalMaxEnergy / a.length;
    }

    /**
     * Calculates the total score of the codes.
     * 
     * @return The total score of the codes
     */
    public int calculateCodesScore() {
        int score = 0;
        for (Code code : a) {
            score += code.heatResistance;
            score += code.coldResistance;
            score += code.lightSensitivity;
            score += code.maxEnergy;
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
            new ProgressBarData("Heat resistance", 0, Code.MAX_VALUE,
                averageHeatResistance, "°C"),
            new ProgressBarData("Cold resistance", 0, Code.MAX_VALUE,
                averageColdResistance, "°C"),
            new ProgressBarData("Light sensitivity", 0, Code.MAX_VALUE,
                averageLightSensitivity),
            new ProgressBarData("Max energy", 0, Code.MAX_VALUE,
                averageMaxEnergy)
        };
    }

    public int size() {
        return a.length;
    }
}
