package me.alextzamalis.cryptotracker.models;

import java.util.UUID;

/**
 * Base account type for every user that can sign-in to the application
 */
public abstract class Account {
    private final String id;
    private final AccountRole role;
    private String username;
    private String passwordHash;
    private String displayName;

    /**
     * Created an account with generated identifier (ID)
     * @param username
     * @param passwordHash
     * @param displayName
     * @param role
     */
    protected Account(String username, String passwordHash, String displayName, AccountRole role) {

    }

    /**
     * Created an account with an existing identifier from storage
     * @param id
     * @param username
     * @param passwordHash
     * @param displayName
     * @param role
     */
    protected Account(String id, String username, String passwordHash, String displayName, AccountRole role) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.displayName = displayName;
        this.role = role;
    }

    /**
     * Supported account roles.
     */
    public enum AccountRole {
        CLIENT,
        ADMIN
    }

}