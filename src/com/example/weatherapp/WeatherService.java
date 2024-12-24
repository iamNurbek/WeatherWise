package src.com.example.weatherapp;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class WeatherService {

    private static final String API_KEY = System.getenv("OPENWEATHERMAP_API_KEY");

    public JSONObject getWeather(String city) throws Exception {
        if (API_KEY == null || API_KEY.isEmpty()) {
            throw new RuntimeException("SET OPENWEATHER API");
        }

        URI uri = new URI("https", "api.openweathermap.org", "/data/2.5/weather", "q=" + city + "&appid=" + API_KEY + "&units=metric", null);
        URL url = uri.toURL();

        HttpURLConnection connection = null;
        BufferedReader reader = null;

    }
}
