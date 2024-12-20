package com.example.weatherapp;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {

    // Base URL for the OpenWeatherMap API
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    // Public placeholder for the API key
    public static final String API_KEY = "YOUR_API_KEY_HERE";

    public JSONObject getWeather(String city) throws Exception {
        // Ensure the API key is provided
        if (API_KEY.equals("YOUR_API_KEY_HERE")) {
            throw new RuntimeException("Please set your API key in the WeatherService class");
        }

        // Build the full API request URL
        String urlString = BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric";
        URL url = new URL(urlString);

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            // Open the HTTP connection
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Check the HTTP response code
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + responseCode);
            }

            // Read the response
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Parse and return the JSON response
            return new JSONObject(response.toString());

        } finally {

            if (reader != null) {
                reader.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
