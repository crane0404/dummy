import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;


public class RomanDigitTest {
    @Test
    public void equalityIsBasedOnDecimalValue() {
        RomanDigit one = new RomanDigit('I', 1);
        assertEquals(one, one);
        assertEquals(new RomanDigit('I', 1), one);
        assertFalse("null should not be equal to valid RomanDigit", one.equals(null));
    }

    @Test
    public void isMapFriendly(){
        RomanDigit one=new RomanDigit('I',1);
        RomanDigit anotherOne=new RomanDigit('I',1);
        Map<RomanDigit,String>romanDigit=new HashMap<>();
        romanDigit.put(one,"One");
        romanDigit.put(anotherOne,"AnotherOne");
        assertEquals(1,romanDigit.size());
        assertEquals("AnotherOne",romanDigit.get(one));
        assertEquals("AnotherOne",romanDigit.get(anotherOne));
    }
    @Test
    public void onlySevenDigitsAreValid()  {
        for (char c : asList('I', 'V', 'X', 'L', 'C', 'D', 'M'))
            RomanDigit.parse(c);
    }

    @Test
    public void otherDigitsAreInvalid()  {
        assertDigitIsInvalid('A');
        assertDigitIsInvalid('Z');
    }

    @Test
    public void negativeNumbersAreInvalid()  {
        assertDigitIsInvalid('-');
    }

    @Test
    public void zeroIsInvalid()  {
        assertDigitIsInvalid('0');
    }

    @Test
    public void areCaseInsensitive()  {
        for (char c : asList('i', 'v', 'x', 'l', 'c', 'd', 'm')) {
            RomanDigit.parse(c);
            assertDigitIsInvalid('a');
            assertDigitIsInvalid('z');
        }
    }

    @Test
    public void appendsItsDecimalValueToGivenValue()  {
        assertAppendedValue(2,'I',1);
        assertAppendedValue(7,'V',2);
        assertAppendedValue(15,'X',5);
        assertAppendedValue(51,'L',1);
        assertAppendedValue(103,'C',3);
        assertAppendedValue(499,'D',-1);
        assertAppendedValue(1001,'M',1);
    }




    @Test
    public void addItsDecimalValueToGivenValueIfNextDigitSmallerThanOrEqualToItself()  {
        assertValueBasedOnNextDigit(2,'I','I');
        assertValueBasedOnNextDigit(6,'V','I');
        assertValueBasedOnNextDigit(11,'X','I');
        assertValueBasedOnNextDigit(11,'X','V');
    }

    @Test
    public void subtractItsDecimalValueToGivenValueIfNextDigitIsGreaterThanItself()  {
        assertValueBasedOnNextDigit(0,'I','V');
        assertValueBasedOnNextDigit(-4,'V','X');
        assertValueBasedOnNextDigit(-9,'X','L');
    }

    private void assertValueBasedOnNextDigit(final int expected, final char digit, final char next) {
        assertEquals(expected,romanDigit(digit).dependingOnNextDigitAppendValueTo(1,romanDigit(next)));
    }


    @Test
    public void onlyPowersOfTensRepeats()  {
        assertTrue("I is a power of Ten and can repeat",romanDigit('I').canRepeat());
        assertFalse("V is a not a power of Ten and should not repeat",romanDigit('V').canRepeat());
        assertTrue("X is a power of Ten and can repeat",romanDigit('X').canRepeat());
        assertFalse("L is a not a power of Ten and should not repeat",romanDigit('L').canRepeat());
    }

    private void assertDigitIsInvalid(final char testDigitValue) {
        try {
            romanDigit(testDigitValue);
            fail("should have thrown an exception");
        } catch (NumberFormatException e) {
            assertEquals(testDigitValue + " is not a valid Roman Numeral Digit", e.getMessage());
        }
    }
    private void assertAppendedValue(final int expected, final char digit, final int oldValue) {
        assertEquals(expected,romanDigit(digit).appendValueTo(oldValue));

    }

    private RomanDigit romanDigit(final char testDigit) {
        return RomanDigit.parse(testDigit);
    }


}
