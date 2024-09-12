package ipc1.lab.controllers;

import ipc1.lab.models.Researcher;
import ipc1.lab.models.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UserController {
    public static User admin = new User("admin", "admin", "admin");
    public static User currentUser = null;
    public static ArrayList<User> users = new ArrayList<>();

    public static void loadUsers(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            ArrayList<String> list = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            for (int i = 1; i < list.size(); i++) {
                String[] infoMuestras = list.get(i).split(",");
                Researcher newResearcher = new Researcher(
                        infoMuestras[0],
                        infoMuestras[4],
                        infoMuestras[1],
                        infoMuestras[2].charAt(0),
                        Integer.parseInt(infoMuestras[3])
                );
                users.add(newResearcher);
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        } finally {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    public static User[] bubbleSortResearchers() {
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
