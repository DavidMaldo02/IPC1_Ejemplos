package ipc1.lab.experiment;

import ipc1.lab.user.Researcher;

public class Experiment {
    private int id = 0;
    private Researcher researcher;
    private Sample sample;

    public Experiment(Researcher researcher, Sample sample) {
        this.id++;
        this.researcher = researcher;
        this.sample = sample;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Researcher getResearcher() {
        return researcher;
    }

    public void setResearcher(Researcher researcher) {
        this.researcher = researcher;
    }

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }
}
