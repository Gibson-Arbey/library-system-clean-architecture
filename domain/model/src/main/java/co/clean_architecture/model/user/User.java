package co.clean_architecture.model.user;

import java.time.LocalDateTime;

public class User {

    private final Long id;
    private final String username;
    private final Password password;
    private final Mail mail;
    private final String status;
    private final Long roleId;
    private final LocalDateTime createdDate;

    private User(Long id, String username, Password password, Mail mail, String status, Long roleId, LocalDateTime createdDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.status = status;
        this.roleId = roleId;
        this.createdDate = createdDate;
    }

    public static User create(String username, Password password, Mail mail, Long roleId) {
        return new User(
                null,
                username,
                password,
                mail,
                UserStatus.ACTIVE.name(),
                roleId,
                LocalDateTime.now()
        );

    }

    public static User restore(Long id, String username, Password password, Mail mail, String status, Long roleId, LocalDateTime createdDate) {
        return new User(
                id,
                username,
                password,
                mail,
                status,
                roleId,
                createdDate
        );
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }

    public Mail getMail() {
        return mail;
    }

    public String getStatus() {
        return status;
    }

    public Long getRoleId() {
        return roleId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
