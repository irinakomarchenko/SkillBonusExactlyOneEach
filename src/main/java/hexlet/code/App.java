package hexlet.code;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.io.File;
import java.util.*;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        System.out.println("Input number of participants:");
        int n = scanner.nextInt();

        List<Double> skills = new ArrayList<>();
        System.out.println("Enter skill values for " + n + " participants:");
        for (int i = 0; i < n; i++) {
            skills.add(scanner.nextDouble());
        }

        System.out.print("Enter the fixed bonus amount: ");
        double absBonus = scanner.nextDouble();

        System.out.print("Enter the percentage bonus amount: ");
        double percBonus = scanner.nextDouble();

        List<BonusResult> results = Calculator.calculateBonuses(skills, absBonus, percBonus);

        double total = 0;

        String date = LocalDate.now().toString();
        String reportsDir = "reports";
        String fileName = reportsDir + File.separator + "result-" + date + ".txt";


        File directory = new File(reportsDir);
        if (!directory.exists()) {
            directory.mkdir();
        }


        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8))) {
            writer.println("Results for each participant:");
            System.out.println("\nResults for each participant:");

            for (BonusResult r : results) {
                String line = String.format("Participant %d: base = %.2f, bonus = %s, result = %.2f",
                        r.index() + 1, r.base(), r.type(), r.finalValue());
                System.out.println(line);
                writer.println(line);
                total += r.finalValue();
            }

            String totalLine = String.format("\nTotal Skills after Bonuses: %.2f", total);
            System.out.println(totalLine);
            writer.println(totalLine);

            System.out.printf("\nThe results are saved to a file.: %s%n", fileName);

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
