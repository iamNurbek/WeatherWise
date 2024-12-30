# WeatherWise

**WeatherWise** is a simple web-based weather application that allows users to search for the current weather in any city. It integrates Google Maps and the OpenWeather API to provide a seamless and visually appealing experience.

---

## Features

1. **City-Based Weather Search**
   - Enter the name of a city to get the current temperature, humidity, and a brief weather description.

2. **Interactive Map Integration**
   - Displays the searched city on an interactive Google Map using Google Maps JavaScript API.

3. **Dynamic and Modular Design**
   - A modular backend (using Java's `HttpServer` and `WeatherService`) that serves as the API layer for the frontend.

4. **Simple User Interface**
   - A clean and responsive design with form inputs, weather details, and the integrated map.

---

## Technologies Used

### Frontend
- **HTML5**
- **CSS3**: For styling and responsive design.
- **JavaScript**: For handling user interactions and fetching backend data.
- **Google Maps JavaScript API**: To display the searched city's location on an interactive map.

### Backend
- **Java (11 or higher)**: For server-side logic and API integration.
- **Built-in HttpServer**: A lightweight HTTP server provided by Java for handling requests.
- **JSON (org.json)**: For parsing and manipulating JSON responses from OpenWeather API.

### APIs
1. **OpenWeather Geocoding API**:
   - To fetch the latitude and longitude of a given city.
2. **OpenWeather Current Weather API**:
   - To fetch the current weather data for a given location (latitude and longitude).
3. **Google Maps JavaScript API**:
   - To render an interactive map and place a marker on the searched city.

---

## Getting Started

### Prerequisites

- **Java 11 or higher** installed on your system.
- **Python 3** (for serving the `index.html` file locally).
- API keys for:
  1. **OpenWeather API** (sign up at [OpenWeather](https://openweathermap.org)).
  2. **Google Maps API** (create a project and get a key from [Google Cloud Console](https://console.cloud.google.com)).

---

### Installation and Setup

1. **Replace API Keys**
   - Open `index.html` and replace `"REPLACE_WITH_API"` with your **Google Maps API Key**:
     ```html
     <script async src="https://maps.googleapis.com/maps/api/js?key=REPLACE_WITH_API&libraries=maps,marker&v=beta"></script>
     ```

   - Open `WeatherService.java` and replace `"REPLACE_WITH_YOUR_API_KEY"` with your **OpenWeather API Key**:
     ```java
     private static final String API_KEY = "REPLACE_WITH_YOUR_API_KEY";
     ```

2. **Compile Java Files**
   ```bash
   javac -d bin -cp lib/json-20240303.jar src/com/example/weatherapp/*.java

3. **Run the Backend Server**
    ```bash
    java -cp bin:lib/json-20240303.jar com.example.weatherapp.WeatherHttpServer
    - The server will start on http://localhost:8080.

4. **Serve the Frontend**
    - Use Python's HTTP server to serve the index.html file:
    ```bash
    python -m http.server 8081
    ```
    - Open your browser and navigate to http://localhost:8081

