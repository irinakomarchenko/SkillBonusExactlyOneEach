package hexlet.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {

    public static List<BonusResult> calculateBonuses(List<Double> skills, double absBonus, double percBonus) {
        int totalCount = skills.size();

        List<Skill> skillList = new ArrayList<>();
        for (int i = 0; i < totalCount; i++) {
            double skill = skills.get(i);
            double absValue = skill + absBonus;
            double percValue = skill * (1 + percBonus / 100);
            double diff = absValue - percValue; // Difference to determine preference
            skillList.add(new Skill(i, skill, diff));
        }

        skillList.sort((a, b) -> Double.compare(b.diff(), a.diff()));

        BonusResult[] results = new BonusResult[totalCount];
        int absCount = 0;
        int percCount = 0;

        for (Skill s : skillList) {
            double absValue = s.base() + absBonus;
            double percValue = s.base() * (1 + percBonus / 100);

            if (absValue > percValue) {
                results[s.index()] = new BonusResult(s.index(), s.base(), "Fixed", absValue);
                absCount++;
            } else {
                results[s.index()] = new BonusResult(s.index(), s.base(), "Percentage", percValue);
                percCount++;
            }
        }

        System.out.printf("Fixed bonuses: %d, Percentage bonuses: %d%n", absCount, percCount);

        return Arrays.asList(results);
    }
}