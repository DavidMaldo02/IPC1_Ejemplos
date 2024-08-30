package ipc1.lab.common;

import ipc1.lab.experiment.Sample;
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
                String[] infoMuestras = list.get(i).split(",");
                Researcher newResearcher = new Researcher(
                        infoMuestras[0],
                        infoMuestras[4],
                        infoMuestras[1],
                        infoMuestras[2].charAt(0),
                        Integer.parseInt(infoMuestras[3])
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

    public static void loadSamples(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            ArrayList<String> list = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            for (int i = 1; i < list.size(); i++) {
                String[] infoMuestras = list.get(i).split(",");
                // Transformar la muestra a un arreglo de enteros 1;0;0;1
                String[] muestra = infoMuestras[2].split(";");
                int longitudMatriz = (int) Math.sqrt(muestra.length);
                int[][] matrizMuestra = new int[longitudMatriz][longitudMatriz];
                for (int j = 0; j < longitudMatriz; j++) {
                    for (int k = 0; k < longitudMatriz; k++) {
                        matrizMuestra[j][k] = Integer.parseInt(muestra[j * longitudMatriz + k]);
                    }
                }
                Sample sample = new Sample(infoMuestras[0], infoMuestras[1], matrizMuestra);
                State.samples.add(sample);
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        } finally {
            for (Sample sample: State.samples)
                System.out.println(sample);
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
