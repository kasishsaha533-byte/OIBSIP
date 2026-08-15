# Number Guessing Game

## Project Overview

The Number Guessing Game is a Java console-based game where the computer generates a random number and the player tries to guess it.

After each guess, the player receives a hint indicating whether the guess is too high or too low. The game continues until the player guesses the correct number or reaches the maximum number of attempts.

## Features

* Random number generation
* Easy, Medium, and Hard difficulty levels
* Attempt limits for each difficulty
* Too High and Too Low hints
* Correct answer notification
* Attempt counter
* Play Again option
* Multiple round support
* Round and score summary
* Invalid input handling
* Correct number revealed when the player loses

## Difficulty Levels

| Difficulty | Number Range | Maximum Attempts |
| ---------- | ------------ | ---------------- |
| Easy       | 1 - 50       | 10               |
| Medium     | 1 - 100      | 7                |
| Hard       | 1 - 200      | 5                |

## Technologies Used

* Java
* `Random`
* `Scanner`
* `ArrayList`
* `while` loops
* `if-else` statements

## How to Run

1. Open the project in IntelliJ IDEA.
2. Open `NumberGuessingGame.java`.
3. Run the `main()` method.
4. Select a difficulty level.
5. Enter guesses according to the selected range.
6. Continue playing or choose `no` when asked.

## Project Structure

```text
Java-Task2-NumberGuessingGame/
├── screenshots/
│   ├── OutputSS1.png
│   └── OutputSS2.png
├── src/
│   └── NumberGuessingGame.java
├── .gitignore
└── README.md
```

## Screenshots

### Game Output 1

![Game Output 1](screenshots/OutputSS1.png)

### Game Output 2

![Game Output 2](screenshots/OutputSS2.png)

## OIBSIP

This project was developed as part of the OIBSIP Java Development Internship.

## Author

Kasish Saha
