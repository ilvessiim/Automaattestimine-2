package data;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.PublicHolidayService;


import java.time.ZonedDateTime;

public class CourseTest {
    ZonedDateTime startDate = ZonedDateTime.parse("2020-01-05T00:00:00.000+00:00[UTC]");
    ZonedDateTime endDate = ZonedDateTime.parse("2020-01-30T00:00:00.000+00:00[UTC]");
    ZonedDateTime dateOfBirth = ZonedDateTime.parse("1930-01-01T00:00:00.000+00:00[UTC]");
    Teacher teacher = new Teacher("Kaanalind","Kaanalind String","Kann",dateOfBirth,90 );

    @Mock
    private PublicHolidayService publicHolidayService;

    @InjectMocks
    Course course = new Course("Lendamine",2,startDate,endDate,teacher);

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void daysLength() {
        ZonedDateTime startDate = ZonedDateTime.parse("2020-01-05T00:00:00.000+00:00[UTC]");
        //Given
        long expetedResult = 19L;

        //When
        long result = course.getLength();

        //then
        assertEquals(expetedResult, result);
    }

    @Test
    public void daysNegative() {
        startDate = ZonedDateTime.parse("2020-02-05T00:00:00.000+00:00[UTC]");
        //Given
        Course course = new Course("Lendamine",2,startDate,endDate,teacher);
        when(publicHolidayService.getPublicHolidaysOnWorkdays(startDate,endDate)).thenReturn(1);
        //When
        String expectedResult = "oih";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> course.getWorkingDays());
        //then
        assertEquals(expectedResult, exception.getMessage());
    }

    //@Test(expected = IllegalArgumentException.class)
    //public void daysZero() {
        //Given
        //when(course.getLength()).thenReturn((long) 0);
        //course.getLength();
        //Course course = new Course("Lendamine",2,startDate,endDate,teacher);
        //long expetedResult = 0;

        //When
        // long result = course.getLength();

        //then
        // assertEquals(expetedResult, result);


    @Test(expected = NullPointerException.class)
    public void nullIntegerTest() {
        Course course = new Course();
        Long result = course.getLength();

        }

}