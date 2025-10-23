// CinemaPublisher.java
package cinema;
import jakarta.xml.ws.Endpoint;

public class CinemaPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/ws/cinema", new CinemaService());
        System.out.println("Cinema SOAP Web Service is running at http://localhost:8080/ws/cinema?wsdl");
    }
}
