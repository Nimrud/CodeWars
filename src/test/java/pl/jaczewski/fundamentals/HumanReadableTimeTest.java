package pl.jaczewski.fundamentals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class HumanReadableTimeTest {

    @Test
    public void should_produce_00_00_00_when_0_given() {
        assertEquals("00:00:00", HumanReadableTime.makeReadable(0));
    }

    @Test
    public void should_produce_00_00_29_when_29_given() {
        assertEquals("00:00:29", HumanReadableTime.makeReadable(29));
    }

    @Test
    public void should_produce_00_01_00_when_60_given() {
        assertEquals("00:01:00", HumanReadableTime.makeReadable(60));
    }

    @Test
    public void should_produce_23_59_59_when_86399_given() {
        assertEquals("23:59:59", HumanReadableTime.makeReadable(86399));
    }

    @Test
    public void should_produce_99_59_59_when_359999_given() {
        assertEquals("99:59:59", HumanReadableTime.makeReadable(359999));
    }
}