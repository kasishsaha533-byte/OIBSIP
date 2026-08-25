import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction
{

    private String type;
    private double amount;
    private String description;
    private double balanceAfter;
    private LocalDateTime dateTime;

    public Transaction(String type, double amount, String description, double balanceAfter)
    {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.balanceAfter = balanceAfter;
        this.dateTime = LocalDateTime.now();
    }

    public String getType()
    {
        return type;
    }

    public double getAmount()
    {
        return amount;
    }

    public String getDescription()
    {
        return description;
    }

    public double getBalanceAfter()
    {
        return balanceAfter;
    }

    public LocalDateTime getDateTime()
    {
        return dateTime;
    }

    @Override
    public String toString()
    {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return String.format(
                "%-12s | ₹%-10.2f | %-35s | Balance: ₹%.2f | %s",
                type,
                amount,
                description,
                balanceAfter,
                dateTime.format(formatter)
        );
    }
}
