package cinema;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            File inputFile = new File("C:\\Users\\lajmi\\OneDrive\\Desktop\\SOA\\cinema\\src\\main\\resources\\cinema.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            System.out.println("=====================================");

            NodeList filmList = doc.getElementsByTagName("film");

            for (int i = 0; i < filmList.getLength(); i++) {
                Node filmNode = filmList.item(i);

                if (filmNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element filmElement = (Element) filmNode;

                    String id = filmElement.getAttribute("id");
                    String type = filmElement.getAttribute("type");

                    String titre = filmElement.getElementsByTagName("titre").item(0).getTextContent();
                    String annee = filmElement.getElementsByTagName("annee").item(0).getTextContent();

                    System.out.println("Film ID: " + id);
                    System.out.println("Type: " + type);
                    System.out.println("Titre: " + titre);
                    System.out.println("Année: " + annee);
                    System.out.println("Rôles:");

                    NodeList roleList = filmElement.getElementsByTagName("role");
                    for (int j = 0; j < roleList.getLength(); j++) {
                        Node roleNode = roleList.item(j);
                        if (roleNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element roleElement = (Element) roleNode;
                            String nom = roleElement.getElementsByTagName("nom").item(0).getTextContent();
                            String acteur = roleElement.getElementsByTagName("acteur").item(0).getTextContent();
                            System.out.println("  - " + nom + " joué par " + acteur);
                        }
                    }
                    System.out.println("-------------------------------------");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
