package ipc1.lab.user;

import java.io.Serializable;

public class User implements Serializable {
    private String code;
    private String password;
    private String type;

    public User(String code, String password, String type) {
        this.code = code;
        this.password = password;
        this.type = type;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.code + " - " + this.password;
    }
}
