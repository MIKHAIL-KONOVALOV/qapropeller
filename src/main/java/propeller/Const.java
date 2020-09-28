package propeller;

public enum Const {
    LOGINPAGE_URL("http://192.168.99.100:8088/index.html"),
    PROFILEPAGE_URL("http://192.168.99.100:8088/profile.html"),
    MAINPAGE_URL("http://192.168.99.100:8088/main.html"),
    LOGIN_ERROR_URL("http://192.168.99.100:8088/loginError.html"),

    LOGIN_CORRECT("test"),
    PASSWORD_CORRECT("test"),
    LOGIN_WRONG("Asdfgh"),
    PASSWORD_WRONG("uiojkl");

    private final String constant;

    Const(String constant) {
        this.constant = constant;
    }
    public String getValue() {
        return constant;
    }
}
