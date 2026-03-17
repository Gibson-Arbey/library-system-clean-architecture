package co.clean_architecture.model.role;

public class Role {

    private final Long id;

    private final String name;

    private Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Role restore(Long id, String name) {
        return new Role(id, name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
