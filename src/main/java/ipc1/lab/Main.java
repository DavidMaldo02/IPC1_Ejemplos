package ipc1.lab;

import ipc1.lab.common.State;
import ipc1.lab.common.Util;
import ipc1.lab.user.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("./src/main/resources/users.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            State.users = (ArrayList<User>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
        if (State.users.isEmpty()) {
            State.users.add(State.admin);
        }

        Util.loadSamples("./src/main/resources/muestras.csv");

        Util.loginFrame.setVisible(true);
    }
}