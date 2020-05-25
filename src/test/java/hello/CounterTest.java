package hello;

import hello.Counter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CounterTest {

    private Counter counter = new Counter();

    @Test
    public void counterInt() {
        int expectedResult = 9;
        assertEquals(counter.countLetters("Ann Mirell"), expectedResult );
    }

    @Test
    public void counterZero() {
        int expectedResult = 707;
        assertEquals(counter.countLetters(""), expectedResult);
    }

}