import java.util.List;
import java.util.Scanner;

public class ATM
{

    private Bank bank;
    private Scanner scanner;

    public ATM(Bank bank)
    {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
    }

    public void start()
    {

        System.out.println("====================================");
        System.out.println("        WELCOME TO ATM SYSTEM       ");
        System.out.println("====================================");

        Account currentAccount = login();

        if (currentAccount == null)
        {
            System.out.println("\nAccess Denied.");
            System.out.println("Thank you for using the ATM.");
            return;
        }

        System.out.println("\nLogin successful!");
        System.out.println("Welcome, " + currentAccount.getUserId() + "!");

        showMenu(currentAccount);
    }

    private Account login()
    {

        final int MAX_ATTEMPTS = 3;

        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++)
        {

            System.out.print("\nEnter User ID: ");
            String userId = scanner.nextLine().trim();

            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine().trim();

            Account account = bank.authenticate(userId, pin);

            if (account != null)
            {
                return account;
            }

            int remainingAttempts = MAX_ATTEMPTS - attempt;

            if (remainingAttempts > 0)
            {
                System.out.println("Invalid User ID or PIN.");
                System.out.println(
                        "Remaining attempts: " + remainingAttempts
                );
            }
        }

        System.out.println("\nMaximum login attempts exceeded.");

        return null;
    }

    private void showMenu(Account account)
    {

        boolean running = true;

        while (running)
        {

            System.out.println("\n====================================");
            System.out.println("             ATM MENU               ");
            System.out.println("====================================");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.println("====================================");

            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice)
            {

                case "1":
                    showTransactionHistory(account);
                    break;

                case "2":
                    withdraw(account);
                    break;

                case "3":
                    deposit(account);
                    break;

                case "4":
                    transfer(account);
                    break;

                case "5":
                    System.out.println("\n====================================");
                    System.out.println("Thank you for using our ATM.");
                    System.out.println("Have a great day!");
                    System.out.println("====================================");
                    running = false;
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please select an option from 1 to 5."
                    );
            }
        }
    }

    private void showTransactionHistory(Account account)
    {

        System.out.println("\n====================================");
        System.out.println("        TRANSACTION HISTORY         ");
        System.out.println("====================================");

        List<Transaction> transactions = account.getTransactions();

        if (transactions.isEmpty())
        {
            System.out.println("No transactions found.");
            return;
        }

        for (int i = 0; i < transactions.size(); i++)
        {
            System.out.println(
                    (i + 1) + ". " + transactions.get(i)
            );
        }

        System.out.println("====================================");
    }

    private void withdraw(Account account)
    {

        System.out.println("\n========== WITHDRAW ==========");

        double amount = readAmount("Enter amount to withdraw: ");

        if (amount <= 0)
        {
            System.out.println("Invalid amount.");
            return;
        }

        if (amount > account.getBalance())
        {
            System.out.println("Insufficient Funds.");
            System.out.printf(
                    "Available Balance: ₹%.2f%n",
                    account.getBalance()
            );
            return;
        }

        if (account.withdraw(amount))
        {

            account.addTransaction(
                    new Transaction(
                            "WITHDRAW",
                            amount,
                            "Cash withdrawal",
                            account.getBalance()
                    )
            );

            System.out.println("Withdrawal successful.");
            System.out.printf(
                    "Amount Withdrawn: ₹%.2f%n",
                    amount
            );
            System.out.printf(
                    "Remaining Balance: ₹%.2f%n",
                    account.getBalance()
            );
        }
    }

    private void deposit(Account account)
    {

        System.out.println("\n========== DEPOSIT ==========");

        double amount = readAmount("Enter amount to deposit: ");

        if (amount <= 0)
        {
            System.out.println("Invalid amount.");
            return;
        }

        account.deposit(amount);

        account.addTransaction(
                new Transaction(
                        "DEPOSIT",
                        amount,
                        "Cash deposit",
                        account.getBalance()
                )
        );

        System.out.println("Deposit successful.");
        System.out.printf(
                "Amount Deposited: ₹%.2f%n",
                amount
        );
        System.out.printf(
                "Updated Balance: ₹%.2f%n",
                account.getBalance()
        );
    }

    private void transfer(Account sender)
    {

        System.out.println("\n========== TRANSFER ==========");

        System.out.print("Enter recipient Account ID: ");
        String recipientId = scanner.nextLine().trim();

        Account receiver = bank.findByAccountId(recipientId);

        if (receiver == null)
        {
            System.out.println("Recipient account not found.");
            return;
        }

        if (receiver == sender)
        {
            System.out.println(
                    "You cannot transfer money to your own account."
            );
            return;
        }

        double amount = readAmount("Enter amount to transfer: ");

        if (amount <= 0)
        {
            System.out.println("Invalid amount.");
            return;
        }

        if (amount > sender.getBalance())
        {
            System.out.println("Insufficient Funds.");
            System.out.printf(
                    "Available Balance: ₹%.2f%n",
                    sender.getBalance()
            );
            return;
        }

        boolean success = bank.transfer(
                sender,
                receiver,
                amount
        );

        if (success)
        {
            System.out.println("Transfer successful.");
            System.out.printf(
                    "Amount Transferred: ₹%.2f%n",
                    amount
            );
            System.out.println(
                    "Recipient Account: " + receiver.getAccountId()
            );
            System.out.printf(
                    "Remaining Balance: ₹%.2f%n",
                    sender.getBalance()
            );
        }
        else
        {
            System.out.println("Transfer failed.");
        }
    }

    private double readAmount(String message)
    {

        while (true)
        {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            try
            {

                double amount = Double.parseDouble(input);

                return amount;

            }
            catch (NumberFormatException e)
            {

                System.out.println(
                        "Invalid input. Please enter a valid number."
                );
            }
        }
    }
}
