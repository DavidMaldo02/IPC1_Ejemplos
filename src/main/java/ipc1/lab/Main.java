package ipc1.lab;

import ipc1.lab.common.State;
import ipc1.lab.user.Researcher;
import ipc1.lab.user.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        State.users.add(State.admin);
        File investigadoresFile = new File("./src/main/resources/investigadores.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(investigadoresFile))) {
            String line;
            ArrayList<String> list = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            for (int i = 1; i < list.size(); i++) {
                String[] infoInvestigadores = list.get(i).split(",");
                Researcher newResearcher = new Researcher(
                        infoInvestigadores[0],
                        infoInvestigadores[4],
                        infoInvestigadores[1],
                        infoInvestigadores[2].charAt(0),
                        Integer.parseInt(infoInvestigadores[3])
                );
                State.users.add(newResearcher);
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        } finally {
            for (User user: State.users) {
                System.out.println(user);
            }
        }
    }
}