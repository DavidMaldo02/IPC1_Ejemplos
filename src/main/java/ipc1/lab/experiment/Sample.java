package ipc1.lab.experiment;

public class Sample {
    private String code;
    private String description;
    private int[][] pattern;

    public Sample(String code, String description, int[][] pattern) {
        this.code = code;
        this.description = description;
        this.pattern = pattern;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int[][] getPattern() {
        return pattern;
    }

    public void setPattern(int[][] pattern) {
        this.pattern = pattern;
    }
}
