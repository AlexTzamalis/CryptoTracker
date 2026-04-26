package me.alextzamalis.cryptotracker.file;

import me.alextzamalis.cryptotracker.models.Account;
import me.alextzamalis.cryptotracker.models.Transaction;

import java.io.IOException;
import java.util.List;

/**
 * Interface Strategy for file-based application persistance.
 */
public interface DataAccessStrategy {

    /**
     * Loads all peristed accounts
     * @return accounts from storage
     * @throws IOException when storage cannot be read
     */
    List<Account> loadAccounts() throws IOException;

    /**
     * Saves all accounts to storage
     * @param accounts accounts to persist
     * @throws IOException when storage cannot be written to
     */
    void saveAccounts(List<Account> accounts) throws IOException;

    /**
     * Loads all persisted transactions
     * @return transactions from storage
     * @throws IOException when storage cannot be read by
     */
    List<Transaction> loadTransactions() throws IOException;

    /**
     * Saves all transactions to storage
     * @param transactions transactions to persist
     * @throws IOException when storage cannot be written to
     */
    void saveTransactions(List<Transaction> transactions) throws IOException;
}
