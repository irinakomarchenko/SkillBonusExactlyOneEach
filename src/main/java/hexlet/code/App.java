package hexlet.code;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Double> skills = Arrays.asList(100.0, 200.0, 300.0, 400.0, 500.0);

        int absCount = 2;
        double absBonus = 50.0;
        int percCount = 3;
        double percBonus = 10.0;

        double total = Optimizer.maximize(skills, absCount, absBonus, percCount, percBonus);
        System.out.printf("Maximum skill sum: %.2f%n", total);
    }
}
