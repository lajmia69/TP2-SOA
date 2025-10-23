package productCompany;

public class Main_Client {
    public static void main(String[] args) {
        ProductClient client = new ProductClient();
        String movieTitle = "Some Movie"; // Example request
        String nominees = client.getNominees(movieTitle);
        System.out.println("Nominees for " + movieTitle + ": " + nominees);
    }
}
