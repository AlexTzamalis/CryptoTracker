package me.alextzamalis.cryptotracker.models;

import java.util.Objects;
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
        this(UUID.randomUUID().toString(), username, passwordHash, displayName, role);
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
        this.id = requireText(id, "id");
        this.username = requireText(username, "username");
        this.passwordHash = requireText(passwordHash, "passwordHash");
        this.displayName = requireText(displayName, "displayName");
        this.role = Objects.requireNonNull(role, "role");
    }

    /**
     * Supported account roles
     */
    public enum AccountRole {
        CLIENT,
        ADMIN
    }

    /**
     * REturns the unique account identifier
     * @return account ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the username used for login
     * @return account username
     */
    public String getUsername() {
        return username;
    }

    /**
     * updates the username parameter
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = requireText(username, "username");
    }

    /**
     * Returns the stored password hash
     * @return password hash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Updates the stored password hash
     * @param passwordHash the new password hasgh
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash  = requireText(passwordHash, "passwordHash");
    }

    /**
     * Return the user-facing display name
     * @return display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Updates the user-facing display name
     * @param displayName the new display name
     */
    public void setDisplayName(String displayName) {
        this.displayName = requireText(displayName, "displayName");
    }

    /**
     * Returns the account role
     * @return account role
     */
    public AccountRole getRole() {
        return role;
    }

    /**
     * Validates text fields shared by model classes.
     * @param value the candidate value
     * @param fieldName the field name for error message
     * @return the trimmed value
     */
    protected static String requireText(String value, String fieldName) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank");
        }
        return value.trim();
    }
}