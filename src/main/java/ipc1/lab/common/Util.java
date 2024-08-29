package ipc1.lab.common;

import ipc1.lab.ui.LoginFrame;
import ipc1.lab.user.Researcher;
import ipc1.lab.user.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Util {
    public static LoginFrame loginFrame = new LoginFrame();

    public static void loadUsers(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
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
            for (User user : State.users) {
                System.out.println(user);
            }
        }
    }

    public static User[] bubbleSortResearchers(ArrayList<User> users) {
        User[] sortedUsers = new User[users.size() - 1];
        for (int i = 1; i < users.size(); i++)
            sortedUsers[i - 1] = users.get(i);
        for (int i = 0; i < sortedUsers.length - 1; i++) {
            for (int j = 0; j < sortedUsers.length - 1 - i; j++) {
                if (sortedUsers[j] instanceof Researcher researcher1 && sortedUsers[j + 1] instanceof Researcher researcher2) {
                    if (researcher1.getExperimentsCount() < researcher2.getExperimentsCount()) {
                        Researcher temp = (Researcher) sortedUsers[j];
                        sortedUsers[j] = sortedUsers[j + 1];
                        sortedUsers[j + 1] = temp;
                    }
                }
            }
        }
        return sortedUsers;
    }
}
