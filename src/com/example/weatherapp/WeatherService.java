package com.example.weatherapp;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class WeatherService {

    private static final String API_KEY = "REPLACE_WITH_YOUR_API_KEY";

    public double[] getCoordinates(String city) throws Exception {
        if (API_KEY == null || API_KEY.isEmpty()) {
            throw new RuntimeException("API key not set.");
        }

        URI uri = new URI("http", "api.openweathermap.org", "/geo/1.0/direct",
                "q=" + city + "&limit=1&appid=" + API_KEY, null);
        URL url = uri.toURL();

        // System.out.println("Request URL: " + url);

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            // System.out.println("Response Code: " + responseCode);

            if (responseCode != 200) {
                BufferedReader errorStream = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                StringBuilder errorResponse = new StringBuilder();
                String errorLine;
                while ((errorLine = errorStream.readLine()) != null) {
                    errorResponse.append(errorLine);
                }
                // System.out.println("Error Response: " + errorResponse.toString());
                throw new RuntimeException("Failed to fetch coordinates. HTTP Response Code: " + responseCode);
            }

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // System.out.println("Geocoding Response: " + response.toString());

            JSONArray jsonResponse = new JSONArray(response.toString());
            if (jsonResponse.length() == 0) {
                throw new RuntimeException("Location not found.");
            }
            JSONObject locationData = jsonResponse.getJSONObject(0);
            double latitude = locationData.getDouble("lat");
            double longitude = locationData.getDouble("lon");

            return new double[]{latitude, longitude};

        } finally {
            if (reader != null) {
                reader.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public JSONObject getWeather(double latitude, double longitude) throws Exception {
        URI uri = new URI("http", "api.openweathermap.org", "/data/2.5/weather",
                "lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY + "&units=metric", null);
        URL url = uri.toURL();

        // System.out.println("Request URL: " + url);

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            // System.out.println("Response Code: " + responseCode);

            if (responseCode != 200) {
                BufferedReader errorStream = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                StringBuilder errorResponse = new StringBuilder();
                String errorLine;
                while ((errorLine = errorStream.readLine()) != null) {
                    errorResponse.append(errorLine);
                }
                // System.out.println("Error Response: " + errorResponse.toString());
                throw new RuntimeException("Failed to fetch weather data. HTTP Response Code: " + responseCode);
            }

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // System.out.println("Weather Response: " + response.toString());
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
