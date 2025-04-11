import hexlet.code.BonusResult;
import hexlet.code.Calculator;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @Test
    void testCalculateBonusesWithFixedPreference() {
        List<Double> skills = List.of(10.0, 20.0, 30.0);
        double absBonus = 5.0;
        double percBonus = 10.0;

        List<BonusResult> results = Calculator.calculateBonuses(skills, absBonus, percBonus);

        assertEquals(3, results.size());
        assertEquals("Fixed", results.get(0).type());
        assertEquals(15.0, results.get(0).value());
    }

    @Test
    void testCalculateBonusesWithPercentagePreference() {
        List<Double> skills = List.of(10.0, 20.0, 30.0);
        double absBonus = 2.0;
        double percBonus = 50.0;

        List<BonusResult> results = Calculator.calculateBonuses(skills, absBonus, percBonus);

        assertEquals(3, results.size());
        assertEquals("Percentage", results.get(0).type());
        assertEquals(15.0, results.get(0).value());
    }

    @Test
    void testCalculateBonusesMixedPreferences() {
        List<Double> skills = List.of(10.0, 20.0, 30.0);
        double absBonus = 5.0;
        double percBonus = 20.0;

        List<BonusResult> results = Calculator.calculateBonuses(skills, absBonus, percBonus);

        assertEquals(3, results.size());
        assertEquals("Fixed", results.get(0).type());
        assertEquals("Fixed", results.get(1).type());
        assertEquals("Percentage", results.get(2).type());
    }

}
