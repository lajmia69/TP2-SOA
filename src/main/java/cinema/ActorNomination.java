package cinema;

public class ActorNomination {
    private String actorName;
    private String roleName;
    private String filmReference;
    private int salary;

    public ActorNomination() {
    }

    public ActorNomination(String actorName, String roleName, String filmReference, int salary) {
        this.actorName = actorName;
        this.roleName = roleName;
        this.filmReference = filmReference;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return actorName + " (" + roleName + " in " + filmReference + ") - $" + String.format("%,d", salary);
    }
}