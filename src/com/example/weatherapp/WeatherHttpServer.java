package com.example.weatherapp;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.InetSocketAddress;

public class WeatherHttpServer {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        System.out.println("Server started at http://localhost:8080");

        WeatherService weatherService = new WeatherService();

        server.createContext("/weather", exchange -> {
            try {
                if ("OPTIONS".equals(exchange.getRequestMethod())) {
                    exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
                    exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
                    exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");
                    exchange.sendResponseHeaders(204, -1);
                    return;
                }
        
                if ("GET".equals(exchange.getRequestMethod())) {
                    String query = exchange.getRequestURI().getQuery();
                    String city = null;

                    if (query != null && query.contains("city=")) {
                        city = query.split("=")[1];
                    }
        
                    if (city == null || city.isEmpty()) {
                        sendResponse(exchange, 400, "{\"error\":\"City parameter is required\"}");
                        return;
                    }
        
                    try {
                        double[] coordinates = weatherService.getCoordinates(city);
                        JSONObject weather = weatherService.getWeather(coordinates[0], coordinates[1]);
        
                        JSONObject result = new JSONObject();
                        result.put("city", city);
                        result.put("latitude", coordinates[0]);
                        result.put("longitude", coordinates[1]);
                        result.put("temperature", weather.getJSONObject("main").getDouble("temp"));
                        result.put("humidity", weather.getJSONObject("main").getDouble("humidity"));
                        result.put("description", weather.getJSONArray("weather").getJSONObject(0).getString("description"));
        
                        sendResponse(exchange, 200, result.toString());
                    } catch (Exception e) {
                        sendResponse(exchange, 500, "{\"error\":\"" + e.getMessage() + "\"}");
                    }
                } else {
                    sendResponse(exchange, 405, "{\"error\":\"Method not allowed\"}");
                }
            } catch (Exception e) {
                e.printStackTrace();
                sendResponse(exchange, 500, "{\"error\":\"Internal server error\"}");
            }
        });
        

        server.setExecutor(null);
        server.start();
    }

    private static void sendResponse(HttpExchange exchange, int statusCode, String response) {
        try {
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().set("Content-Type", "application/json");
    
            exchange.sendResponseHeaders(statusCode, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
