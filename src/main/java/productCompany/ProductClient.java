package productCompany;

import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import java.net.URI;
import java.util.List;
import java.util.Scanner;
import cinema.client.CinemaServiceInterface;

public class ProductClient {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("===========================================");
            System.out.println("   CINEMA ACTOR NOMINATION SYSTEM");
            System.out.println("===========================================\n");
            
            // Get movie details from user
            System.out.print("Enter your movie name: ");
            String movieName = scanner.nextLine();
            
            System.out.println("\nAvailable genres:");
            System.out.println("  1. action");
            System.out.println("  2. comedy");
            System.out.println("  3. drama");
            System.out.println("  4. romance");
            System.out.println("  5. sci-fi (science fiction)");
            System.out.println("  6. horror");
            System.out.println("\nTIP: You can enter multiple genres!");
            System.out.println("   Examples: 'action comedy' or 'drama, romance' or 'action and sci-fi'");
            System.out.print("\nEnter movie genre(s): ");
            String genres = scanner.nextLine().trim();
            
            System.out.println("\nBudget examples:");
            System.out.println("  Small indie film: $5,000,000");
            System.out.println("  Medium budget: $50,000,000");
            System.out.println("  Blockbuster: $200,000,000");
            System.out.print("\nEnter your budget (in USD, numbers only): $");
            long budget = 0;
            try {
                budget = scanner.nextLong();
                if (budget <= 0) {
                    System.out.println("Budget must be positive! Using default: $50,000,000");
                    budget = 50000000;
                }
                if (budget > 1000000000) {
                    System.out.println(" Budget capped at $1,000,000,000 (1 billion)");
                    budget = 1000000000;
                }
            } catch (Exception e) {
                System.out.println("Invalid budget! Using default: $50,000,000");
                budget = 50000000;
                scanner.nextLine(); // Clear buffer
            }
            
            System.out.println("\n===========================================");
            System.out.println("Connecting to Cinema Service...");
            System.out.println("===========================================\n");
            
            // Connect to SOAP service
            URI wsdlUri = new URI("http://localhost:8080/ws/cinema?wsdl");
            QName SERVICE_NAME = new QName("http://cinema/", "CinemaServiceService");
            QName PORT_NAME = new QName("http://cinema/", "CinemaServicePort");

            Service service = Service.create(wsdlUri.toURL(), SERVICE_NAME);
            CinemaServiceInterface cinemaPort = service.getPort(PORT_NAME, CinemaServiceInterface.class);
            
            // Call the service with genres (not genre)
            List<String> nominations = cinemaPort.getNominateActors(genres, movieName, budget);
            
            // Display results
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║         NOMINATION RESULTS                 ║");
            System.out.println("╚════════════════════════════════════════════╝\n");
            
            for (String line : nominations) {
                System.out.println(line);
            }
            
            System.out.println("\n===========================================");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}