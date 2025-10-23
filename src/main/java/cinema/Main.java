package cinema;

import jakarta.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        String url = "http://localhost:8080/cinema";
        System.out.println("Publishing Cinema SOAP Service at: " + url);
        Endpoint.publish(url, new CinemaService());
        System.out.println("Service published successfully!");
    }
}
