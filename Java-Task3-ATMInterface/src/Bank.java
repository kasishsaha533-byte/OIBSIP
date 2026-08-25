import java.util.ArrayList;

public class Bank
{

    private ArrayList<Account> accounts;

    public Bank()
    {
        accounts = new ArrayList<>();
    }

    public void addAccount(Account account)
    {
        accounts.add(account);
    }

    public Account findByUserId(String userId)
    {
        for (Account account : accounts)
        {
            if (account.getUserId().equals(userId))
            {
                return account;
            }
        }
        return null;
    }

    public Account findByAccountId(String accountId)
    {
        for (Account account : accounts)
        {
            if (account.getAccountId().equalsIgnoreCase(accountId))
            {
                return account;
            }
        }
        return null;
    }

    public Account authenticate(String userId, String pin)
    {
        Account account = findByUserId(userId);

        if (account != null && account.getPin().equals(pin))
        {
            return account;
        }

        return null;
    }

    public boolean transfer(Account sender, Account receiver, double amount)
    {

        if (sender == null || receiver == null)
        {
            return false;
        }

        if (amount <= 0 || sender.getBalance() < amount)
        {
            return false;
        }

        sender.withdraw(amount);
        receiver.deposit(amount);

        sender.addTransaction(
                new Transaction(
                        "TRANSFER",
                        amount,
                        "Transferred to " + receiver.getAccountId(),
                        sender.getBalance()
                )
        );

        receiver.addTransaction(
                new Transaction(
                        "TRANSFER",
                        amount,
                        "Received from " + sender.getAccountId(),
                        receiver.getBalance()
                )
        );

        return true;
    }
}
