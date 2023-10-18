import java.util.AbstractList;

class CodesContainer extends AbstractList<Code>
    implements ProgressBarDataProvider {

    private final Code[] a;
    public int averageHeatResistance = 0;
    public int averageColdResistance = 0;
    public int averageMaxEnergy = 0;
    public int averageMaxDamage = 0;

    public CodesContainer(Code[] array) {
        a = array;
        updateAverages();
    }

    public Code get(int index) {
        return a[index];
    }

    public Code set(int index, Code element) {
        Code oldValue = a[index];
        a[index] = element;
        updateAverages();
        return oldValue;
    }

    void updateAverages() {
        int totalHeatResistance = 0;
        int totalColdResistance = 0;
        int totalMaxEnergy = 0;
        int totalMaxDamage = 0;
        for (Code code : a) {
            totalHeatResistance += code.heatResistance;
            totalColdResistance += code.coldResistance;
            totalMaxEnergy += code.maxEnergy;
            totalMaxDamage += code.maxDamage;
        }
        averageHeatResistance = totalHeatResistance / a.length;
        averageColdResistance = totalColdResistance / a.length;
        averageMaxEnergy = totalMaxEnergy / a.length;
        averageMaxDamage = totalMaxDamage / a.length;
    }

    public ProgressBarData[] getProgressBarData() {
        return new ProgressBarData[] {
            new ProgressBarData("Heat resistance", 0, Code.maxValue,
                averageHeatResistance, "°C"),
            new ProgressBarData("Cold resistance", 0, Code.maxValue,
                averageColdResistance, "°C"),
            new ProgressBarData("Max energy", 0, Code.maxValue,
                averageMaxEnergy),
            new ProgressBarData("Max damage", 0, Code.maxValue,
                averageMaxDamage),
        };
    }

    public int size() {
        return a.length;
    }
}
