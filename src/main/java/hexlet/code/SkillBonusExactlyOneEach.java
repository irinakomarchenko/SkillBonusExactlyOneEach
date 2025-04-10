package hexlet.code;

import java.util.*;

public class SkillBonusExactlyOneEach {

    public static void main(String[] args) {
        List<Double> skills = new ArrayList<>(Arrays.asList(100.0, 200.0, 300.0, 400.0, 500.0));

        int aCount = 2;
        double aBonus = 50.0;
        int pCount = 3;
        double pBonus = 10.0;

        double total = maximizeSkillExactOnePerPerson(skills, aCount, aBonus, pCount, pBonus);
        System.out.printf("Maximum amount of skills: %.2f%n", total);
    }

    public static double maximizeSkillExactOnePerPerson(List<Double> skills, int aCount, double aBonus, int pCount, double pBonus) {
        int n = skills.size();
        if (aCount + pCount != n) {
            throw new IllegalArgumentException("The amount of bonuses must be equal to the number of participants.");
        }

        List<SkillWithDiff> diffs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            double skill = skills.get(i);
            double gainA = skill + aBonus;
            double gainP = skill * (1 + pBonus / 100);
            double diff = (gainA - gainP);
            diffs.add(new SkillWithDiff(i, skill, diff));
        }

        diffs.sort((a, b) -> Double.compare(b.diff, a.diff));

        double[] result = new double[n];


        for (int i = 0; i < aCount; i++) {
            SkillWithDiff s = diffs.get(i);
            result[s.index] = s.skill + aBonus;
        }


        for (int i = aCount; i < n; i++) {
            SkillWithDiff s = diffs.get(i);
            result[s.index] = s.skill * (1 + pBonus / 100);
        }

        double total = 0;
        for (double val : result) {
            total += val;
        }

        return total;
    }

    static class SkillWithDiff {
        int index;
        double skill;
        double diff;

        SkillWithDiff(int index, double skill, double diff) {
            this.index = index;
            this.skill = skill;
            this.diff = diff;
        }
    }
}
