package productCompany;

import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import java.net.URL;
import java.util.List;

public class ProductClient {
    public static void main(String[] args) throws Exception {
        // Match these values to your WSDL!
        URL wsdlUrl = new URL("http://localhost:8080/ws/cinema?wsdl"); // Must match published server/WSDL
        QName SERVICE_NAME = new QName("http://cinema/", "CinemaServiceService");

        // Create the SOAP service
        Service service = Service.create(wsdlUrl, SERVICE_NAME);

        // NOTE: cinema.CinemaService must match what is generated (if using wsimport, for example)
        cinema.CinemaService cinemaPort = service.getPort(cinema.CinemaService.class);

        // Request nominations for a movie
        String movie = "Space Odyssey";
        List<String> nominees = cinemaPort.getNominations(movie);

        System.out.println("Nominations for '" + movie + "':");
        for (String nominee : nominees) {
            System.out.println(" - " + nominee);
        }
    }
}
