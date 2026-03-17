package co.clean_architecture.model.user;

public class Mail {

    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    private final String value;

    public Mail(String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidEmailException("Email must not be empty");
        }
        if (!value.matches(EMAIL_REGEX)) {
            throw new InvalidEmailException("Invalid email format");
        }
        this.value = value;
    }

    public String value() {
        return value;
    }

}
