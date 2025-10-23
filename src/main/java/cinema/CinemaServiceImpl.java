package cinema;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebService(
    name = "CinemaService",
    serviceName = "CinemaServiceService",
    portName = "CinemaServicePort",
    targetNamespace = "http://cinema/"
)
public class CinemaServiceImpl {
    
    private Document cinemaDocument;
    
    public CinemaServiceImpl() {
        loadCinemaXML();
    }
    
    private void loadCinemaXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream is = getClass().getClassLoader().getResourceAsStream("cinema.xml");
            if (is == null) {
                throw new RuntimeException("cinema.xml not found");
            }
            cinemaDocument = builder.parse(is);
            cinemaDocument.getDocumentElement().normalize();
            System.out.println("Cinema XML loaded successfully!");
        } catch (Exception e) {
            System.err.println("Error loading cinema.xml: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @WebMethod
    public List<String> getNominateActors(
            @WebParam(name = "genres") String genres,
            @WebParam(name = "movieName") String movieName,
            @WebParam(name = "budget") long budget) {
        
        List<String> nominations = new ArrayList<>();
        
        if (cinemaDocument == null) {
            nominations.add("Error: Cinema database not loaded");
            return nominations;
        }
        
        try {
            // Split genres by comma, space, or "and"
            String[] genreArray = genres.split("[,\\s]+and[,\\s]+|[,\\s]+");
            List<String> requestedGenres = new ArrayList<>();
            for (String genre : genreArray) {
                String cleaned = genre.trim();
                if (!cleaned.isEmpty()) {
                    requestedGenres.add(cleaned);
                }
            }
            
            if (requestedGenres.isEmpty()) {
                nominations.add("Error: No valid genres provided");
                return nominations;
            }
            
            // Get all films matching any of the genres
            NodeList films = cinemaDocument.getElementsByTagName("film");
            List<ActorNomination> availableActors = new ArrayList<>();
            
            for (int i = 0; i < films.getLength(); i++) {
                Element film = (Element) films.item(i);
                String filmType = film.getAttribute("type");
                
                // Check if this film matches any requested genre
                boolean matchesAnyGenre = false;
                for (String requestedGenre : requestedGenres) {
                    if (matchesGenre(filmType, requestedGenre)) {
                        matchesAnyGenre = true;
                        break;
                    }
                }
                
                if (matchesAnyGenre) {
                    String filmTitle = getElementText(film, "titre");
                    
                    // Get all roles/actors from this film
                    NodeList roles = film.getElementsByTagName("role");
                    for (int j = 0; j < roles.getLength(); j++) {
                        Element role = (Element) roles.item(j);
                        String actorName = getElementText(role, "acteur");
                        String roleName = getElementText(role, "nom");
                        String salaryStr = getElementText(role, "salaire");
                        
                        int salary = 0;
                        if (salaryStr != null && !salaryStr.isEmpty()) {
                            salary = Integer.parseInt(salaryStr);
                        }
                        
                        availableActors.add(new ActorNomination(actorName, roleName, filmTitle, salary));
                    }
                }
            }
            
            if (availableActors.isEmpty()) {
                nominations.add("No actors found for genres: " + String.join(", ", requestedGenres));
                return nominations;
            }
            
            // Remove duplicate actors (keep the cheapest option if actor appears multiple times)
            List<ActorNomination> uniqueActors = new ArrayList<>();
            for (ActorNomination actor : availableActors) {
                boolean found = false;
                for (int i = 0; i < uniqueActors.size(); i++) {
                    if (uniqueActors.get(i).getActorName().equals(actor.getActorName())) {
                        // Keep the cheaper one
                        if (actor.getSalary() < uniqueActors.get(i).getSalary()) {
                            uniqueActors.set(i, actor);
                        }
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    uniqueActors.add(actor);
                }
            }
            
            // Sort actors by salary (ascending) to maximize the number of actors within budget
            uniqueActors.sort((a, b) -> Integer.compare(a.getSalary(), b.getSalary()));
            
            // Select actors within budget
            long remainingBudget = budget;
            List<ActorNomination> selectedActors = new ArrayList<>();
            
            for (ActorNomination actor : uniqueActors) {
                if (actor.getSalary() <= remainingBudget) {
                    selectedActors.add(actor);
                    remainingBudget -= actor.getSalary();
                }
            }
            
            // Format the response
            if (selectedActors.isEmpty()) {
                nominations.add("Budget too low! Minimum required: $" + 
                    String.format("%,d", uniqueActors.get(0).getSalary()));
                nominations.add("Total actors available: " + uniqueActors.size());
            } else {
                nominations.add("╔════════════════════════════════════════════════════════╗");
                nominations.add("║  ACTOR NOMINATIONS FOR: " + movieName);
                nominations.add("╚════════════════════════════════════════════════════════╝");
                nominations.add("");
                nominations.add("Genres: " + String.join(", ", requestedGenres));
                nominations.add("Total Budget: $" + String.format("%,d", budget));
                nominations.add("Budget Used: $" + String.format("%,d", (budget - remainingBudget)));
                nominations.add("Budget Remaining: $" + String.format("%,d", remainingBudget));
                nominations.add("Total actors available: " + uniqueActors.size());
                nominations.add("");
                nominations.add("═══════════════ NOMINATED ACTORS ═══════════════");
                nominations.add("");
                
                int actorNum = 1;
                for (ActorNomination actor : selectedActors) {
                    nominations.add(String.format("%2d. %s", actorNum, actor.toString()));
                    actorNum++;
                }
                
                nominations.add("");
                nominations.add("════════════════════════════════════════════════");
                nominations.add("✓ Total actors nominated: " + selectedActors.size());
                nominations.add("════════════════════════════════════════════════");
            }
            
        } catch (Exception e) {
            nominations.add("Error processing nomination: " + e.getMessage());
            e.printStackTrace();
        }
        
        return nominations;
    }
    
    private boolean matchesGenre(String filmGenre, String requestedGenre) {
        if (filmGenre == null || requestedGenre == null) return false;
        
        String fg = filmGenre.toLowerCase().trim();
        String rg = requestedGenre.toLowerCase().trim();
        
        // Direct match
        if (fg.equals(rg)) return true;
        
        // English to French genre mapping
        if (rg.equals("comedy") && (fg.equals("comédie") || fg.equals("comedie"))) return true;
        if (rg.equals("drama") && fg.equals("drame")) return true;
        if (rg.equals("horror") && fg.equals("horreur")) return true;
        if ((rg.equals("sci-fi") || rg.equals("science fiction")) && 
            (fg.equals("science-fiction") || fg.contains("science"))) return true;
        
        // French to English (in case XML changes)
        if ((fg.equals("comédie") || fg.equals("comedie")) && rg.equals("comedy")) return true;
        if (fg.equals("drame") && rg.equals("drama")) return true;
        if (fg.equals("horreur") && rg.equals("horror")) return true;
        
        // Handle partial matches for flexibility
        if ((fg.contains("comedie") || fg.contains("comedy")) && 
            (rg.contains("comedie") || rg.contains("comedy"))) return true;
            
        if ((fg.contains("science") || fg.contains("sci-fi")) && 
            (rg.contains("science") || rg.contains("sci-fi"))) return true;
            
        return fg.contains(rg) || rg.contains(fg);
    }
    
    private String getElementText(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            return node.getTextContent().trim();
        }
        return "";
    }
    
    // Keep the old method for backward compatibility
    @WebMethod
    public List<String> getNominations(@WebParam(name = "filmTitle") String filmTitle) {
        List<String> nominations = new ArrayList<>();
        nominations.add("Note: This method is deprecated. Please use getNominateActors() instead.");
        nominations.add("For '" + filmTitle + "': Please specify genre and budget.");
        return nominations;
    }
}