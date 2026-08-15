import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame
{
    public static void main(String[] args)
    {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int roundNumber = 1;
        String playAgain = "yes";

        ArrayList<String> roundResults = new ArrayList<>();

        while (playAgain.equalsIgnoreCase("yes"))
        {
            int difficulty = 0;

            while (difficulty < 1 || difficulty > 3)
            {
                System.out.println("\n===== CHOOSE DIFFICULTY =====");
                System.out.println("1. Easy   (1-50, 10 attempts)");
                System.out.println("2. Medium (1-100, 7 attempts)");
                System.out.println("3. Hard   (1-200, 5 attempts)");

                System.out.print("Enter your choice: ");

                if (scanner.hasNextInt())
                {
                    difficulty = scanner.nextInt();

                    if (difficulty < 1 || difficulty > 3)
                    {
                        System.out.println("Invalid choice! Please enter 1, 2, or 3.");
                    }
                }
                else
                {
                    System.out.println("Invalid input! Please enter a number (1, 2, or 3).");
                    scanner.next();
                }
            }

            int maxNumber;
            int maxAttempts;
            String difficultyName;

            if (difficulty == 1)
            {
                maxNumber = 50;
                maxAttempts = 10;
                difficultyName = "Easy";
            }
            else if (difficulty == 2)
            {
                maxNumber = 100;
                maxAttempts = 7;
                difficultyName = "Medium";
            }
            else
            {
                maxNumber = 200;
                maxAttempts = 5;
                difficultyName = "Hard";
            }

            int number = random.nextInt(maxNumber) + 1;

            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\n===== ROUND " + roundNumber + " =====");
            System.out.println("Difficulty: " + difficultyName);
            System.out.println("I have selected a number between 1 and " + maxNumber + ".");
            System.out.println("You have " + maxAttempts + " attempts.");

            while (attempts < maxAttempts)
            {
                System.out.print("\nEnter your guess: ");

                if (!scanner.hasNextInt())
                {
                    System.out.println("Invalid input! Please enter a whole number.");
                    scanner.next();
                    continue;
                }

                int guess = scanner.nextInt();

                if (guess < 1 || guess > maxNumber)
                {
                    System.out.println("Invalid guess! Please enter a number between 1 and "
                            + maxNumber + ".");
                    continue;
                }

                attempts++;

                System.out.println("Attempt " + attempts + "/" + maxAttempts);

                if (guess > number)
                {
                    System.out.println("Too High!");
                }
                else if (guess < number)
                {
                    System.out.println("Too Low!");
                }
                else
                {
                    System.out.println("Correct!");
                    System.out.println("You guessed it in " + attempts + " attempts!");

                    guessedCorrectly = true;

                    roundResults.add("Round " + roundNumber + " — guessed in "
                            + attempts + " attempts (" + difficultyName + ")");

                    break;
                }
            }

            if (!guessedCorrectly)
            {
                System.out.println("\nYou Lost!");
                System.out.println("The number was: " + number);

                roundResults.add("Round " + roundNumber + " — You Lost! Number was "
                        + number + " (" + difficultyName + ")");
            }

            do
            {
                System.out.print("\nPlay Again? (yes/no): ");
                playAgain = scanner.next();

                if (!playAgain.equalsIgnoreCase("yes")
                        && !playAgain.equalsIgnoreCase("no"))
                {
                    System.out.println("Invalid input! Please enter yes or no.");
                }
            }
            while (!playAgain.equalsIgnoreCase("yes")
                    && !playAgain.equalsIgnoreCase("no"));

            roundNumber++;
        }

        System.out.println("\n===== GAME SUMMARY =====");

        if (roundResults.isEmpty())
        {
            System.out.println("No rounds played.");
        }
        else
        {
            for (String result : roundResults)
            {
                System.out.println(result);
            }
        }

        System.out.println("\nThanks for playing!");

        scanner.close();
    }
}
