package co.clean_architecture.model.role;

import java.time.LocalDateTime;

public class Role {

    private final Long id;

    private final String name;

    private final LocalDateTime createdDate;

    private Role(Long id, String name, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
    }

    public static Role restore(Long id, String name, LocalDateTime createdDate) {
        return new Role(id, name, createdDate);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedDate() { return createdDate; }
}
