import org.junit.jupiter.api.Test;

public class RepetitionRuleEnforcerTest extends  RuleEnforcerTestCase{

        public RepetitionRuleEnforcerTest() {
            enforcer = new RepetitionRuleEnforcer();
        }

        @Test
        public void powersOfTenCanRepeatUpToThreeTimes() throws Exception {
            enforcer.scan(romanDigits("XXXVIII"));
            assertRuleViolationException("X cannot repeat more than thrice, this violates the Repetition Rule for Roman Numerals","XXXX");
            assertRuleViolationException("I cannot repeat more than thrice, this violates the Repetition Rule for Roman Numerals","IIII");
        }

        @Test
        public void powersOfFiveCannotRepeat() throws Exception {
            assertRuleViolationException("V cannot repeat itself, this violates the Repetition Rule for Roman Numerals","VV");
            assertRuleViolationException("V cannot repeat itself, this violates the Repetition Rule for Roman Numerals","VVV");
            assertRuleViolationException("L cannot repeat itself, this violates the Repetition Rule for Roman Numerals","LLX");
            assertRuleViolationException("D cannot repeat itself, this violates the Repetition Rule for Roman Numerals","DD");
        }


}
