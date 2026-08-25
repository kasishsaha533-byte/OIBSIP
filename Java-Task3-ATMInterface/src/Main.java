public class Main
{

    public static void main(String[] args)
    {

        Bank bank = new Bank();

        Account account1 = new Account(
                "ACC001",
                "user001",
                "1234",
                10000.00
        );

        Account account2 = new Account(
                "ACC002",
                "user002",
                "5678",
                5000.00
        );

        bank.addAccount(account1);
        bank.addAccount(account2);

        ATM atm = new ATM(bank);

        atm.start();
    }
}
