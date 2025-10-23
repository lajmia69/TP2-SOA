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
        switch (filmTitle.toLowerCase()) {
            case "space odyssey":
                nominations.add("John Star");
                nominations.add("Luna Bright");
                nominations.add("Tom Cosmo");
                break;
            case "love in paris":
                nominations.add("Marie Dupont");
                nominations.add("Antoine Leclerc");
                break;
            default:
                nominations.add("No nominations available for this film");
        }
        return nominations;
    }
}
