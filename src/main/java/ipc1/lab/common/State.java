package ipc1.lab.common;

import ipc1.lab.experiment.Experiment;
import ipc1.lab.user.User;
import ipc1.lab.experiment.Sample;

import java.util.ArrayList;

public class State {
    public static User admin = new User("admin", "admin");
    public static ArrayList<User> users = new ArrayList<>();

    public static ArrayList<Sample> samples = new ArrayList<>();
    public static ArrayList<Experiment> experiments = new ArrayList<>();
}
