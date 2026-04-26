package me.alextzamalis.cryptotracker.file;

import java.util.Objects;
import java.util.Properties;

/**
 * This Class reads application property configurations and creates.
 * configured infrastructure objects.
 *
 * @uel 2872177
 * @author Alexandros Tzamalis
 */
public class ConfigManager {
    private static final String CONFIG_RESOURCE = "/config.properties";
    private static final String STORAGE_PATH = "storage.type";
    private static final String ACCOUNTS_PATH = "data.accounts.path";
    private static final String TRANSACTIONS_PATH = "data.transactions.path";
    private static final String DEFAULT_STORAGE_TYPE = "csv";
    private static final String DEFAULT_ACCOUNTS_PATH = "data/accounts.csv";
    private static final String DEFAULT_TRANSACTIONS_PATH = "data/transactions.csv";

    private final Properties properties;

    /**
     * ConfigManager Constructor, that is created by explicit properties.
     * specialized by each property.
     * @param properties
     */
    public ConfigManager(Properties properties) {
        this.properties = new Properties();
        this.properties.putAll(Objects.requireNonNull(properties, "properties"));
    }


}
