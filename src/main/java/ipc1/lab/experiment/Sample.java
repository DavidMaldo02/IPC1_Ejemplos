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

    @Override
    public String toString() {
        String[] muestra = new String[this.pattern.length * this.pattern.length];
        for (int i = 0; i < this.pattern.length; i++) {
            for (int j = 0; j < this.pattern.length; j++) {
                muestra[i * this.pattern.length + j] = String.valueOf(this.pattern[i][j]);
            }
        }
        return String.format("%s\n%s", this.code, String.join(",", muestra));
    }
}
