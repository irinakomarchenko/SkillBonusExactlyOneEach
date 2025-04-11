package hexlet.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Optimizer {

    public static double maximize(List<Double> skills, int absCount, double absBonus, int percCount, double percBonus) {
        int totalCount = skills.size();
        if (absCount + percCount != totalCount) {
            throw new IllegalArgumentException("Bonus count must match number of skills.");
        }

        List<Skill> skillList = new ArrayList<>();

        for (int i = 0; i < totalCount; i++) {
            double skill = skills.get(i);
            double absValue = skill + absBonus;
            double percValue = skill * (1 + percBonus / 100);
            double diff = absValue - percValue;
            skillList.add(new Skill(i, skill, diff));
        }

        skillList.sort((a, b) -> Double.compare(b.diff(), a.diff()));

        double[] result = new double[totalCount];

        for (int i = 0; i < absCount; i++) {
            Skill s = skillList.get(i);
            result[s.index()] = s.base() + absBonus;
        }

        for (int i = absCount; i < totalCount; i++) {
            Skill s = skillList.get(i);
            result[s.index()] = s.base() * (1 + percBonus / 100);
        }

        double total = 0;
        for (double val : result) {
            total += val;
        }

        return total;
    }
}
