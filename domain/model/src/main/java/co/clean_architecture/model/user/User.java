package co.clean_architecture.model.user;

public class User {

    private final Long id;
    private final String username;
    private final Password password;
    private final Mail mail;
    private final String status;
    private final Set<Role> roles;
    private final LocalDateTime createdAt;

    private User(Long id, String username, Password password, Mail mail, String status, Set<Role> roles, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.status = status;
        this.roles = roles;
        this.createdAt = createdAt;
    }

    public static User create(String username, Password password, Mail mail, Set<Role> roles) {
        return new User(
                null,
                username,
                password,
                mail,
                UserStatus.ACTIVE.name(),
                roles,
                LocalDateTime.now()
        );

    }

    public static User restore(Long id, String username, Password password, Mail mail, String status, Set<Role> roles, LocalDateTime createdAt) {
        return new User(
                id,
                username,
                password,
                mail,
                status,
                roles,
                createdAt
        );
    }
}
