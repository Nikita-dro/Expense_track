package bank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Transaction {
    private UUID id;
    private BigDecimal amount;
    private TransactionType type;
    private Category category;
    private LocalDate date;
    private String description;

    public Transaction(BigDecimal amount, TransactionType type, Category category, String description){
        this.id = UUID.randomUUID();
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.date = LocalDate.now();
        this.description = description;
    }

    public UUID getId() {return id;}
    public BigDecimal getAmount() {return amount;}
    public TransactionType getType() {return type;}
    public Category getCategory() {return category;}
    public LocalDate getDate() {return date;}
    public String getDescription() {return description;}
}
