package propeller;

public enum Const {
    LOGIN_PAGE_URL("http://192.168.99.100:8088/index.html"),
    PROFILE_PAGE_URL("http://192.168.99.100:8088/profile.html"),
    MAIN_PAGE_URL("http://192.168.99.100:8088/main.html"),
    LOGIN_ERROR_URL("http://192.168.99.100:8088/loginError.html"),

    LOGIN_PAGE_TITLE("Welcome to Propeller Automated Testing Championship"),

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
