package pl.jaczewski.fundamentals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class StringToIntegerTest {

    @Test
    public void should_produce_0_when_zero_given() {
        assertEquals(0, StringToInteger.parseInt("zero"));
    }

    @Test
    public void should_produce_1000000_when_million_given() {
        assertEquals(1000000, StringToInteger.parseInt("million"));
    }

    @Test
    public void should_produce_1000000_when_UpperCaseMillion_given() {
        assertEquals(1000000, StringToInteger.parseInt("Million"));
    }

    @Test
    public void should_produce_1000000_when_One_Million_given() {
        assertEquals(1000000, StringToInteger.parseInt("One Million"));
    }

    @Test
    public void should_produce_20_when_twenty_given() {
        assertEquals(20, StringToInteger.parseInt("twenty"));
    }

    @Test
    public void should_produce_five_when_five_with_tab_given() {
        assertEquals(5, StringToInteger.parseInt("\tfive"));
    }

    @Test
    public void should_produce_2000_when_Two_Thousand_given() {
        assertEquals(2000, StringToInteger.parseInt("Two Thousand"));
    }

    @Test
    public void should_produce_28_when_twenty_eight_given() {
        assertEquals(28, StringToInteger.parseInt("twenty eight"));
    }

    @Test
    public void should_produce_246_when_two_hundred_forty_six_dashed_given() {
        assertEquals(246, StringToInteger
                .parseInt("two hundred forty-six"));
    }

    @Test
    public void should_produce_246_when_two_hundred_forty_six_with_and_given() {
        assertEquals(246, StringToInteger
                .parseInt("two hundred and forty six"));
    }

    @Test
    public void should_produce_783919_when_big_number_given() {
        assertEquals(783919, StringToInteger
                .parseInt("seven hundred eighty-three thousand nine hundred and nineteen"));
    }
}