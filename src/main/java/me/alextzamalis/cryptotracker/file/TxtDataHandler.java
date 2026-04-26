package me.alextzamalis.cryptotracker.file;

import java.nio.file.Path;
import java.util.Objects;

/**
 * Plain Text (.txt) file type implementation of the Data Access Strategy
 */
public class TxtDataHandler {
    private static final String DELIMITER = "|";

    private final Path accountsPath;
    private final Path transactionsPath;

    /**
     * Creates a text handler with target files
     * @param accountsPath the accounts text path
     * @param transactionsPath the transactions text path
     */
    public TxtDataHandler(Path accountsPath, Path transactionsPath) {
        this.accountsPath = Objects.requireNonNull(accountsPath, "accountsPath");
        this.transactionsPath = Objects.requireNonNull(transactionsPath, "transactionsPath");
    }
}
