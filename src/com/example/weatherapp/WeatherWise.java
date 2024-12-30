package src.com.example.weatherapp;
import org.json.JSONObject;
import java.util.Scanner;

public class WeatherWise {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the city name: ");
            String city = scanner.nextLine();
            WeatherService weatherService = new WeatherService();

            JSONObject weather = weatherService.getWeather(city);
            // System.out.println(weather.toString(2));
            System.out.println("Weather in " + city + ":");
            System.out.println("Temperature: " + weather.getJSONObject("main").getDouble("temp") + "°C");
            System.out.println("Humidity: " + weather.getJSONObject("main").getDouble("humidity") + "%");
            System.out.println("Description: " + weather.getJSONArray("weather").getJSONObject(0).getString("description"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
