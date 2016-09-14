package me.billsays.restaurant.client.model.role;

/**
 * 9/9/16, 2016
 *
 * @author mkvitko
 */
public enum RoleEnum {
    OWNER("OWNER"),
    USER("USER");

    String roleName;

    RoleEnum(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
