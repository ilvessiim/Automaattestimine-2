package services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class PublicHolidayService {

    private static final String COUNTRY_CODE = "EE";
    private static final String API_URL = "https://date.nager.at/api/v2/PublicHolidays/";
    private String baseUrl;

    public PublicHolidayService() {
        this.baseUrl = API_URL;
    }

    public PublicHolidayService(String url) {
        this.baseUrl = url;
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<ZonedDateTime> getPublicHolidays(String year) throws IOException, JSONException {
        return getPublicHolidays(year, baseUrl);
    }

    public List<ZonedDateTime> getPublicHolidays(String year, String apiUrl) throws IOException, JSONException {
        List<ZonedDateTime> result = new ArrayList<>();
        String composedUrl = apiUrl + "/" + year + "/" + COUNTRY_CODE;

        URL url = new URL(composedUrl);
        URLConnection yc = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;
        String dateString;

        while ((inputLine = in.readLine()) != null) {

            System.out.println(inputLine);

            JSONArray jsonArray = new JSONArray(inputLine);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                dateString = jsonObject.getString("date");
                LocalDate date = LocalDate.parse(dateString, formatter);
                ZonedDateTime zonedResult = date.atStartOfDay(ZoneId.of("UTC"));

                result.add(zonedResult);

                System.out.println(zonedResult);
            }
        }
        in.close();

        return result;

    }


    public int getPublicHolidaysOnWorkdays(ZonedDateTime startDate, ZonedDateTime endDate){
        List<ZonedDateTime>result = new ArrayList<>();
        String composedUrl = API_URL + String.valueOf(endDate.getYear()) + "/" + COUNTRY_CODE;
        int countOfHolidays = 0;
        try {
            URL url = new URL(composedUrl);
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            String dateString;

            while ((inputLine = in.readLine()) != null) {
                JSONArray arr = new JSONArray(inputLine);
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject jsonObject = arr.getJSONObject(i);
                    dateString = jsonObject.getString("date");
                    LocalDate date = LocalDate.parse(dateString);
                    ZonedDateTime zonedResult = date.atStartOfDay(ZoneId.of("UTC"));
                    result.add(zonedResult);
                }
            }

            in.close();
            for (ZonedDateTime zonedDateTime : result) {

                if((zonedDateTime.isAfter(startDate) || zonedDateTime.isEqual(startDate))
                        && zonedDateTime.isBefore(endDate)) {
                    int dayOfWeek = zonedDateTime.getDayOfWeek().getValue();
                    if ((dayOfWeek > 1) && (dayOfWeek < 7)) {
                        countOfHolidays += 1;


                    }
                }
            }
        }
        catch (Exception ex){
        }
        return countOfHolidays;
    }
}