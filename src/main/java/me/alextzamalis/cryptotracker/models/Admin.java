package me.alextzamalis.cryptotracker.models;

/**
 * Administrator account with permissions to manage application data and users
 */
public class Admin extends Account {

    /**
     * Created a new Admin account
     * @param username the unique username for login
     * @param passwordHash the stored password hash
     * @param displayName the user-facing account name
     */
    public Admin(String username, String passwordHash, String displayName) {
        super(username, passwordHash, displayName, AccountRole.ADMIN);

    }

    /**
     * Recostructs an admin account from storage
     * @param id account ID
     * @param username the unique username for login
     * @param passwordHash the stored password hash
     * @param displayName the user-facing account name
     */
    public Admin(String id, String username, String passwordHash, String displayName) {
        super(id, username, passwordHash, displayName, AccountRole.ADMIN);
    }
}
