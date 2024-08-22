package ipc1.lab.user;

public class User {
    private String code;
    private String password;
    private String role;

    public User(String code, String password, String role) {
        this.code = code;
        this.password = password;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.code + " - " + this.password;
    }
}
