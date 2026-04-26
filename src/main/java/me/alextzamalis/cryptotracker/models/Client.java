package me.alextzamalis.cryptotracker.models;

import java.util.Objects;

/**
 * Investor account that owns a "crypto/stock" portfolio
 */
public class Client extends Account{

    private final Portfolio portfolio;

    /**
     * Creates a new Client account with an empty portfolio
     * @param username
     * @param passwordHash
     * @param displayName
     */
    public Client(String username, String passwordHash, String displayName) {
        super(username, passwordHash, displayName, AccountRole.CLIENT);
        this.portfolio = new Portfolio(getId());
    }

    /**
     * Reconstructs a Client Account from storage with an empty portfolio
     */
    public Client(String id, String username, String passwordHash, String displayName) {
        this(id, username, passwordHash, displayName, new Portfolio(id));
    }

    public Client(String id, String username, String passwordHash, String displayName, Portfolio portfolio) {
        super(id, username, passwordHash, displayName, AccountRole.CLIENT);
        this.portfolio = Objects.requireNonNull(portfolio, "portfolio");
    }

    /**
     * Returns the portfolio owned by this client
     * @return client's portfolio
     */
    public Portfolio getPortfolio() {
        return portfolio;
    }
}
