public class Code {
    static final int maxValue = 100;

    int heatResistance;
    int coldResistance;
    int maxEnergy;
    int maxDamage;

    public Code(int heatResistance, int coldResistance, int maxEnergy,
        int maxDamage) {
        this.heatResistance = heatResistance;
        this.coldResistance = coldResistance;
        this.maxEnergy = maxEnergy;
        this.maxDamage = maxDamage;
    }

    static Code generateRandomCode() {
        return new Code(
            (int) (Math.random() * Code.maxValue),
            (int) (Math.random() * Code.maxValue),
            (int) (Math.random() * Code.maxValue),
            (int) (Math.random() * Code.maxValue));
    }

    public int getHeatResistance() {
        return heatResistance;
    }

    public int getColdResistance() {
        return coldResistance;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getMaxDamage() {
        return maxDamage;
    }
}
