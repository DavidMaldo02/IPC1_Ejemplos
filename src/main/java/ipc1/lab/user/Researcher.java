package ipc1.lab.user;

public class Researcher extends User {
    private String name;
    private char genre;
    private int experimentsCount;

    public Researcher(String code, String password, String name, char genre, int experimentsCount) {
        super(code, password);
        this.name = name;
        this.genre = genre;
        this.experimentsCount = experimentsCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGenre() {
        return genre;
    }

    public void setGenre(char genre) {
        this.genre = genre;
    }

    public int getExperimentsCount() {
        return experimentsCount;
    }

    public void incrementExperimentsCount() {
        this.experimentsCount++;
    }
}
