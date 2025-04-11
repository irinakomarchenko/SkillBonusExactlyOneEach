package hexlet.code;

public class BonusResult {
    private final int index;
    private final double base;
    private final String type;
    private final double value;

    public BonusResult(int index, double base, String type, double value) {
        this.index = index;
        this.base = base;
        this.type = type;
        this.value = value;
    }

    public int index() {
        return index;
    }

    public double base() {
        return base;
    }

    public String type() {
        return type;
    }

    public double value() {
        return value;
    }

    // Add the finalValue method
    public double finalValue() {
        return value;
    }
}