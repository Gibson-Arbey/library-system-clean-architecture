package co.clean_architecture.model.user.criteria;

import lombok.Getter;

import java.util.List;

@Getter
public class UserCriteria {

    private final String  username;
    private final String mail;

    private final Boolean applyFilterStatus;
    private final List<String> statuses;

    private final Boolean applyFilterRole;
    private final List<String> roleIds;

    private final int limit;
    private final int offset;


    private UserCriteria(
            String username,
            String mail,
            Boolean applyFilterStatus,
            List<String> statuses,
            Boolean applyFilterRole,
            List<String> roleIds,
            int limit,
            int offset) {
        this.username = username;
        this.mail = mail;
        this.applyFilterStatus = applyFilterStatus;
        this.statuses = statuses;
        this.applyFilterRole = applyFilterRole;
        this.roleIds = roleIds;
        this.limit = limit;
        this.offset = offset;
    }

    public static UserCriteria of(
            String username,
            String mail,
            List<String> statuses,
            List<String> roleIds,
            Integer limit,
            Integer offset
    ) {
        boolean applyStatus = statuses != null && !statuses.isEmpty();
        boolean applyRole = roleIds != null && !roleIds.isEmpty();

        return new UserCriteria(
                username,
                mail,
                applyStatus,
                statuses != null ? statuses : List.of(),
                applyRole,
                roleIds != null ? roleIds : List.of(),
                limit != null ? limit : 10,
                offset != null ? offset : 0
        );
    }
}
