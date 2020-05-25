package services;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.json.JSONException;
import org.junit.Rule;
import org.junit.Test;
import services.PublicHolidayService;

import java.io.FileNotFoundException;
import java.time.ZonedDateTime;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class PublicHolidayServiceTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(80);

    PublicHolidayService service = new PublicHolidayService("http://localhost");


    @Test(expected = JSONException.class)
    public void throws_exception_on_invalid_api_response() throws Exception {
        stubFor(any(anyUrl())
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>Some content</response>")));


        //when
        List<ZonedDateTime> result = service.getPublicHolidays("2020");

        //then
        //assertEquals(10, result.size());

        verify(postRequestedFor(urlMatching("https://date.nager.at/api/v2/PublicHolidays/[0-9]+/[a-z]+"))
                .withRequestBody(matching(".*<message>1234</message>.*"))
                .withHeader("Content-Type", notMatching("application/json")));
    }

    @Test
    public void processes_api_response() throws Exception {
        stubFor(any(anyUrl())
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("[{\"date\":\"2020-01-01\",\"localName\":\"uusaasta\",\"name\":\"New Year's Day\",\"countryCode\":\"EE\",\"fixed\":true,\"global\":true,\"counties\":null,\"launchYear\":null,\"type\":\"Public\"}]")));


        //when
        List<ZonedDateTime> result = service.getPublicHolidays("2020");

        //then
        assertEquals(1, result.size());
        assertEquals(ZonedDateTime.parse("2020-01-01T00:00:00.000+00:00[UTC]"), result.get(0));

        verify(getRequestedFor(urlEqualTo("/2020/EE")));
    }

    @Test
    public void emptyJSONArray() throws Exception {
        stubFor(any(anyUrl())
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("[]")));
        //when
        List<ZonedDateTime> result = service.getPublicHolidays("2020");

        //then

        verify(getRequestedFor(urlEqualTo("/2020/EE")));
    }

    @Test(expected = JSONException.class)
    public void emptyJSONObject() throws Exception {
        stubFor(any(anyUrl())
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("{}")));
        //when
        List<ZonedDateTime> result = service.getPublicHolidays("2020");
        //then

        verify(getRequestedFor(urlEqualTo("/2020/EE")));
    }

    @Test(expected = FileNotFoundException.class)
    public void errorCode() throws Exception {
        stubFor(any(anyUrl())
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("[{\"date\":\"2020-01-01\",\"localName\":\"uusaasta\",\"name\":\"New Year's Day\",\"countryCode\":\"EE\",\"fixed\":true,\"global\":true,\"counties\":null,\"launchYear\":null,\"type\":\"Public\"}]")));
        //when
        List<ZonedDateTime> result = service.getPublicHolidays("2020");
        //then
        //assertEquals(10, result.size());

        verify(getRequestedFor(urlEqualTo("/2020/EE")));
    }

    @Test(expected = JSONException.class)
    public void dateFieldError() throws Exception {
        stubFor(any(anyUrl())
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("[{\"localName\":\"uusaasta\",\"name\":\"New Year's Day\",\"countryCode\":\"EE\",\"fixed\":true,\"global\":true,\"counties\":null,\"launchYear\":null,\"type\":\"Public\"}]")));


        //when
        List<ZonedDateTime> result = service.getPublicHolidays("2020");

        //then
        assertEquals(1, result.size());
        assertEquals(ZonedDateTime.parse("2020-01-01T00:00:00.000+00:00[UTC]"), result.get(0));

        verify(getRequestedFor(urlEqualTo("/2020/EE")));
    }



}