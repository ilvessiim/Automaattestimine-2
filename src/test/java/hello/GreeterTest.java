package hello;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class GreeterTest {

    /*private Greeter greeter = new Greeter();
     */
    @Mock
    private Counter counter;

    @InjectMocks
    Greeter greeter = new Greeter();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void greeterWords() {
        //Given
        when(counter.countLetters(" ")).thenReturn(707);

        //When
        String result = greeter.sayHello("");

        //then
        assertEquals("Hi! Your name has 707 letters in it.", result);
    }
    @Test
    public void greeterSaysHello() {
        assertThat(greeter.sayHello(""), containsString("Hi"));
    }

}