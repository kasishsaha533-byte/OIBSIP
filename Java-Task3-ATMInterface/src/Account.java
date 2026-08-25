import java.util.ArrayList;
import java.util.List;

public class Account
{

    private String accountId;
    private String userId;
    private String pin;
    private double balance;
    private ArrayList<Transaction> transactions;

    public Account(String accountId, String userId, String pin, double balance)
    {
        this.accountId = accountId;
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public String getAccountId()
    {
        return accountId;
    }

    public String getUserId()
    {
        return userId;
    }

    public String getPin()
    {
        return pin;
    }

    public double getBalance()
    {
        return balance;
    }

    public List<Transaction> getTransactions()
    {
        return transactions;
    }

    public void deposit(double amount)
    {
        balance += amount;
    }

    public boolean withdraw(double amount)
    {
        if (amount <= balance)
        {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void addTransaction(Transaction transaction)
    {
        transactions.add(transaction);
    }
}
