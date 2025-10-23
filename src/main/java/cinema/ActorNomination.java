package cinema;

public class ActorNomination {
    private String actorName;
    private String roleName;
    private String filmReference;
    private String genre;
    private int salary;

    public ActorNomination() {
    }

    public ActorNomination(String actorName, String roleName, String filmReference, String genre, int salary) {
        this.actorName = actorName;
        this.roleName = roleName;
        this.filmReference = filmReference;
        this.genre = genre;
        this.salary = salary;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getFilmReference() {
        return filmReference;
    }

    public void setFilmReference(String filmReference) {
        this.filmReference = filmReference;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return actorName + " (" + filmReference + ") (" + roleName + ") [" + genre + "] - $" + String.format("%,d", salary);
    }
}