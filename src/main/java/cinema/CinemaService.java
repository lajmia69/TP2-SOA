package cinema;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class CinemaService {
    @WebMethod
    public List<String> getNominations(String filmTitle) {
        List<String> nominations = new ArrayList<>();
        // Example logic: return nominees based on recent roles
        if ("Space Odyssey".equalsIgnoreCase(filmTitle)) {
            nominations.add("John Star"); nominations.add("Luna Bright");
        } else if ("Love in Paris".equalsIgnoreCase(filmTitle)) {
            nominations.add("Marie Dupont"); nominations.add("Antoine Leclerc");
        } else {
            nominations.add("No nominations found for " + filmTitle);
        }
        return nominations;
    }
}
