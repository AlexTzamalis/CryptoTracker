package me.alextzamalis.cryptotracker.file;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Locale;
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
    private static final String STORAGE_TYPE = "storage.type";
    private static final String ACCOUNTS_PATH = "data.accounts.path";
    private static final String TRANSACTIONS_PATH = "data.transactions.path";
    private static final String DEFAULT_STORAGE_TYPE = "csv";
    private static final String DEFAULT_ACCOUNTS_PATH = "data/accounts.csv";
    private static final String DEFAULT_TRANSACTIONS_PATH = "data/transactions.csv";

    private final Properties properties;

    /**
     * ConfigManager Constructor, that loads configurations from the default classpath resource
     */
    public ConfigManager() {
        this.properties = new Properties();
        loadDefaults();
        loadFromClassPath();
    }

    /**
     * ConfigManager Constructor, that is created by explicit properties
     * specialized by each property.
     * @param properties application config properties
     */
    public ConfigManager(Properties properties) {
        this.properties = new Properties();
        loadDefaults();
        this.properties.putAll(Objects.requireNonNull(properties, "properties"));
    }

    public DataAccessStrategy createDataAccessStrategy() {
        return switch (getStorageType()) {
            case "csv" -> new CsvDataHandler(getAccountPath(), getTranssactionsPath());
            case "txt" -> new TxtDataHandler(getAccountPath(), getTranssactionsPath());
            default -> throw new IllegalStateException("Unsupported storage.type: " + getStorageType());
        };
    }

    /**
     * returns the configured storage type
     * @return storage type, csv or txt if no "IllegalStateException"
     */
    public String getStorageType() {
        return properties.getProperty(STORAGE_TYPE, DEFAULT_STORAGE_TYPE).trim().toLowerCase(Locale.ROOT);
    }

    /**
     * Returns the configured accounts file path
     * @return accounts path
     */
    public Path getAccountPath() {
        return Path.of(properties.getProperty(ACCOUNTS_PATH, DEFAULT_ACCOUNTS_PATH));
    }

    /**
     * returns the configured transactions file path
     * @return transactions path
     */
    public Path getTranssactionsPath() {
        return Path.of(properties.getProperty(TRANSACTIONS_PATH, DEFAULT_TRANSACTIONS_PATH));
    }

    /**
     * Sets essential configuration properties to their predefined default values.
     */
    private void loadDefaults() {
        properties.setProperty(STORAGE_TYPE, DEFAULT_STORAGE_TYPE);
        properties.setProperty(ACCOUNTS_PATH, DEFAULT_ACCOUNTS_PATH);
        properties.setProperty(TRANSACTIONS_PATH, DEFAULT_TRANSACTIONS_PATH);
    }

    /**
     * Loads configuration properties from a resource located on the classpath.
     * This method attempts to find and load the resource identified by {@code CONFIG_RESOURCE}
     * using the class loader associated with {@code ConfigManager}. If the resource
     * is successfully found and read, its key-value pairs are loaded into the internal
     * properties object. If the resource is not found, no properties are loaded,
     * and the method completes without error.
     *
     * @throws IllegalStateException If an {@link IOException IOException} occurs
     *                                        during the process of reading or loading the configuration
     *                                        resource, indicating that the properties could not be loaded.
     */
    private void loadFromClassPath() {
        try(InputStream inputStream = ConfigManager.class.getResourceAsStream(CONFIG_RESOURCE)) {
            if(inputStream != null) {
                properties.load(inputStream);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load " + CONFIG_RESOURCE, e);
        }
    }


}
