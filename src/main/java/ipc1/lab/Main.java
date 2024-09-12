package ipc1.lab;

import ipc1.lab.controllers.SampleController;
import ipc1.lab.controllers.UserController;
import ipc1.lab.models.User;
import ipc1.lab.views.LoginFrame;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("./src/main/resources/users.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            UserController.users = (ArrayList<User>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
        if (UserController.users.isEmpty()) {
            UserController.users.add(UserController.admin);
        }

        SampleController.loadSamples("./src/main/resources/muestras.csv");

        new LoginFrame().setVisible(true);
    }
}