package cinema.client;

import java.util.List;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(name = "CinemaService", targetNamespace = "http://cinema/")
public interface CinemaServiceInterface {
    
    @WebMethod
    List<String> getNominations(@WebParam(name = "filmTitle") String filmTitle);
    
    @WebMethod
    List<String> getNominateActors(
        @WebParam(name = "genres") String genres,
        @WebParam(name = "movieName") String movieName,
        @WebParam(name = "budget") long budget
    );
}