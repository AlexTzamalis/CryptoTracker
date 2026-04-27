package me.alextzamalis.cryptotracker.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Records a client buy or sell order for a cryptocurrency coin
 */
public class Transaction {
    private final String id;
    private final String clientId;
    private final String coinSymbol;
    private final TransactionType type;
    private final BigDecimal quantity;
    private final BigDecimal pricePerCoin;
    private final LocalDateTime timestamp;

    /**
     * Creates a transaction with a generated identifier and current timestamp.
     *
     * @param clientId the client account identifier
     * @param coinSymbol the traded coin symbol
     * @param type the transaction type
     * @param quantity the traded quantity
     * @param pricePerCoin the execution price per coin
     */
    public Transaction(String clientId,
                       String coinSymbol,
                       TransactionType type,
                       BigDecimal quantity,
                       BigDecimal pricePerCoin)
    {
        this(UUID.randomUUID().toString(), clientId, coinSymbol, type, quantity, pricePerCoin, LocalDateTime.now());
    }

    /**
     * Reconstructs a transaction from storage.
     *
     * @param id the persisted transaction identifier
     * @param clientId the client account identifier
     * @param coinSymbol the traded coin symbol
     * @param type the transaction type
     * @param quantity the traded quantity
     * @param pricePerCoin the execution price per coin
     * @param timestamp the execution timestamp
     */
    public Transaction(
            String id,
            String clientId,
            String coinSymbol,
            TransactionType type,
            BigDecimal quantity,
            BigDecimal pricePerCoin,
            LocalDateTime timestamp)
    {
        this.id = Account.requireText(id, "id");
        this.clientId = Account.requireText(clientId, "clientId");
        this.coinSymbol = Account.requireText(coinSymbol, "coinSymbol").toUpperCase();
        this.type = Objects.requireNonNull(type, "type");
        this.quantity = requirePositive(quantity, "quantity");
        this.pricePerCoin = requirePositive(pricePerCoin, "pricePerCoin");
        this.timestamp = Objects.requireNonNull(timestamp, "timestamp");
    }

    /**
     * Returns the transaction identifier.
     * @return transaction identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the client account identifier.
     * @return client identifier
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Returns the traded coin symbol.
     * @return coin symbol
     */
    public String getCoinSymbol() {
        return coinSymbol;
    }

    /**
     * Returns the transaction type.
     * @return transaction type
     */
    public TransactionType getType() {
        return type;
    }

    /**
     * Returns the traded quantity.
     * @return quantity
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * Returns the execution price per coin.
     * @return price per coin
     */
    public BigDecimal getPricePerCoin() {
        return pricePerCoin;
    }

    /**
     * Returns the execution timestamp.
     * @return timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Calculates the gross value of the transaction.
     * @return quantity multiplied by execution price
     */
    public BigDecimal getTotalValue() {
        return quantity.multiply(pricePerCoin);
    }

    private static BigDecimal requirePositive(BigDecimal value, String fieldName) {
        Objects.requireNonNull(value, fieldName);
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(fieldName + " must be positive");
        }
        return value;
    }

    /**
     * Supported transaction directions.
     */
    public enum TransactionType {
        BUY,
        SELL
    }
}
