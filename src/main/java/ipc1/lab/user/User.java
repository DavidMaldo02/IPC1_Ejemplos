package ipc1.lab.user;

public class User {
    private String code;
    private String password;

    public User(String code, String password) {
        this.code = code;
        this.password = password;
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

    @Override
    public String toString() {
        return this.code + " - " + this.password;
    }
}
