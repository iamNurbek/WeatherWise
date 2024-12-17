import java.util.Scanner;

public class WeatherWise {
    public static void main(String[] args) {
        System.out.println("WeatherWise App Initialized!");
        System.out.println("More features coming soon...");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a city name: ");
        String city = scanner.nextLine();

        System.out.println("You entered: " + city);
        System.out.println("Weather data fetching coming soon...");
        scanner.close();
    }
}
