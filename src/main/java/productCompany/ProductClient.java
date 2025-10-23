package productCompany;

import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import java.net.URL;
import java.util.List;

public class ProductClient {
    public static void main(String[] args) throws Exception {
        URL wsdlUrl = new URL("http://localhost:8080/ws/cinema?wsdl");
        QName SERVICE_NAME = new QName("http://cinema/", "CinemaServiceService");

        Service service = Service.create(wsdlUrl, SERVICE_NAME);

        // Use the PORT INTERFACE, not the service class
        cinema.CinemaServicePortType cinemaPort = service.getPort(cinema.CinemaServicePortType.class);
        
        String movie = "Space Odyssey";
        List<String> nominees = cinemaPort.getNominations(movie);

        System.out.println("Nominations for '" + movie + "':");
        for (String nominee : nominees) {
            System.out.println(" - " + nominee);
        }
    }
}