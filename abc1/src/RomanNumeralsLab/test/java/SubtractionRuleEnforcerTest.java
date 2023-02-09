import org.junit.jupiter.api.Test;

public class SubtractionRuleEnforcerTest extends RuleEnforcerTestCase{
    public SubtractionRuleEnforcerTest() {
        enforcer = new SubtractionRuleEnforcer();
    }

    @Test
    public void onlyPowerOfTensPreceedHigherValueDigit() throws Exception {
        enforcer.scan(romanDigits("XCVIII"));
        enforcer.scan(romanDigits("CMXLVI"));
        assertRuleViolationException("V cannot be subtracted from X, since V is not a power of ten. This violates the Subtraction Rule for Roman Numerals", "VX");
        assertRuleViolationException("L cannot be subtracted from C, since L is not a power of ten. This violates the Subtraction Rule for Roman Numerals", "LCXVIII");
    }

    @Test
    public void preceedingDigitMustBeOneTenthOrOneFifthOfFollowingHigherValueDigit() throws Exception {
        enforcer.scan(romanDigits("IX"));
        enforcer.scan(romanDigits("IV"));
        enforcer.scan(romanDigits("XL"));
        enforcer.scan(romanDigits("XC"));
        enforcer.scan(romanDigits("CM"));
        enforcer.scan(romanDigits("CD"));
        assertRuleViolationException("I cannot be subtracted from L, since I is not one ten or one fifth of L This violates the Subtraction Rule for Roman Numerals", "IL");
    }

    @Test
    public void preceedingDigitMustBeLargerThanDigitFollowingHigherValueDigit() throws Exception {
        enforcer.scan(romanDigits("XLV"));
        assertRuleViolationException("X cannot be subtracted from L, since X is smaller than or equal to X This violates the Subtraction Rule for Roman Numerals", "XLX");
    }

    @Test
    public void onlyOneLowerValueDigitCanPreceedHigherValueDigit() throws Exception {
        enforcer.scan(romanDigits("IX"));
        assertRuleViolationException("II cannot be subtracted from X, this violates the Subtraction Rule for Roman Numerals", "IIX");
        enforcer.scan(romanDigits("XL"));
        assertRuleViolationException("XX cannot be subtracted from L, this violates the Subtraction Rule for Roman Numerals", "XXL");
        enforcer.scan(romanDigits("CM"));
        assertRuleViolationException("CC cannot be subtracted from M, this violates the Subtraction Rule for Roman Numerals", "CCM");
    }
}
