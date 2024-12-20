import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class WeatherService {

    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public static final String API_KEY = System.getenv("OPENWEATHER_API_KEY");

    public JSONObject getWeather(String city) throws Exception {
        if (API_KEY == null || API_KEY.isEmpty()) {
            throw new RuntimeException("Please set your API key in the environment variable OPENWEATHER_API_KEY");
        }

        String urlString = BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric";
        URI uri = new URI(urlString);
        URL url = uri.toURL();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return new JSONObject(response.toString());
        }
    }
}

