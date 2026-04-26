package me.alextzamalis.cryptotracker.file;

import java.nio.file.Path;
import java.util.Objects;

/**
 * Column Separated Values (.csv) file type Implementation of the file DataAccessStrategy
 */
public class CsvDataHandler {
    private static final String ACCOUNTS_HEADER = "role,id,username,passwordHash,displayName";
    private static final String TRANSACTIONS_HEADER = "id,clientId,coinSymbol,type,quantity,pricePerCoin,timestamp";

    private final Path accountsPath;
    private final Path transactionsPath;

    /**
     * Creates a CSV Handler with target files
     * @param accountsPath the accounts CSV Path
     * @param transactionsPath the transactions CSV Path
     */
    public CsvDataHandler(Path accountsPath, Path transactionsPath) {
        this.accountsPath = Objects.requireNonNull(accountsPath, "accountsPath");
        this.transactionsPath = Objects.requireNonNull(transactionsPath, "transactionsPath");
    }
}
