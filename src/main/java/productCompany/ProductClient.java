package productCompany;

import jakarta.xml.namespace.QName;
import jakarta.xml.ws.Service;
import java.net.URL;
import java.util.List;

public class ProductClient {
    public static void main(String[] args) throws Exception {
        // URL to the WSDL published by the Cinema web service
        URL wsdlUrl = new URL("http://localhost:8080/ws/cinema?wsdl");

        // Namespace URI and Service name (check your generated WSDL for the correct names)
        QName SERVICE_NAME = new QName("http://cinema/", "CinemaServiceService");

        // Create the SOAP service and retrieve the port
        Service service = Service.create(wsdlUrl, SERVICE_NAME);
        cinema.CinemaService cinemaPort = service.getPort(cinema.CinemaService.class);

        System.out.println("Requesting nominations for 'Space Odyssey'...");
        List<String> nominations = cinemaPort.getNominations("Space Odyssey");

        System.out.println("🎬 Recommended Actors:");
        for (String actor : nominations) {
            System.out.println(" - " + actor);
        }
    }
}
