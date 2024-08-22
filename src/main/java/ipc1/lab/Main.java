package ipc1.lab;

import ipc1.lab.common.State;
import ipc1.lab.common.Util;
import ipc1.lab.ui.LoginFrame;


public class Main {
    public static void main(String[] args) {
        State.users.add(State.admin);
        Util.loadUsers("./src/main/resources/investigadores.csv");

        Util.loginFrame.setVisible(true);
    }
}