// ProductClient.java
package productCompany;

import jakarta.xml.namespace.QName;
import jakarta.xml.ws.Service;
import java.net.URL;
import java.util.List;

public class ProductClient {
    public static void main(String[] args) throws Exception {
        URL wsdlUrl = new URL("http://localhost:8080/ws/cinema?wsdl");
        QName SERVICE_NAME = new QName("http://cinema/", "CinemaServiceService"); // Check WSDL for correct name!
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
