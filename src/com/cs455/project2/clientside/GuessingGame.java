package com.cs455.project2.clientside;

import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
  public GuessingGame() {}

  public void startGame() {
    // Game to guess random number
    // generates random number for user to guess
    Random random = new Random();
    int rand = random.nextInt(99) + 1;
    int guesses = 0;
    boolean game = false;

    System.out.println("Enter a number between 1 and 100");
    while (!game) {
      Scanner scanner = new Scanner(System.in);
      int userIn = scanner.nextInt();
      // checks if user input is correct number and gives feedback
      if (rand > userIn) {
        System.out.println("Higher!");
        guesses++;
      }
      if (rand < userIn) {
        System.out.println("Lower!");
        guesses++;
      }
      // outputs number of guesses if they win.
      if (userIn == rand) {
        guesses++;
        System.out.println("You win! it took you " + guesses + " guesses");
        game = true;
      }
    }
  }
}
