import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RomanNumeralParser{
    private final Set<RuleEnforcer> enforcers = new HashSet<RuleEnforcer>() {
        {
            add(new RepetitionRuleEnforcer());
            add(new SubtractionRuleEnforcer());
        }
    };

    RomanNumeral compose (String romanDigits) {
        validateInput(romanDigits);
        String cleanRomanDigits = removeAllWhiteSpaces(romanDigits);
        List<RomanDigit> validDigits = validateDigits(cleanRomanDigits);
        for (RuleEnforcer enforcer:enforcers) {
            enforcer.scan(validDigits);
        }
        return  new RomanNumeral(validDigits);
    }

    private void validateInput(String romanDigits) {
        if(romanDigits==null) {
            throw new NumberFormatException("null is not a valid Roman Numeral");
        }
        if(romanDigits.trim().length()==0) {
            throw new NumberFormatException("Empty String is not a valid Roman Numeral");
        }
    }

    private String removeAllWhiteSpaces(String romanDigits) {
        return  romanDigits.replaceAll("\\s+","");
    }

    private List<RomanDigit> validateDigits(String romanDigits) {
        List<RomanDigit> validDigits = new ArrayList<>(romanDigits.length());
        for (int i=0; i<romanDigits.length(); i++) {
            validDigits.add(RomanDigit.parse(romanDigits.charAt(i)));
        }
        return validDigits;
    }
}
