import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class porject9 {
	public static void main(String[] args) throws FileNotFoundException {

		Scanner keyboard = new Scanner(System.in);
		final String fileName = "dictionary.txt";
		String[] dictionary = readDictionary(fileName);

		// Play this game repeatedly
		String repeatPlay = "yes"; // Used at the end of the loop to determine
									// if the game should be played again

		int wins = 0;
		int losses = 0;
		final int TRIES = 3; // the number of guesses a user is allowed
		while (repeatPlay.equalsIgnoreCase("yes")) {

			int randomCount = (int) (Math.random() * dictionary.length);// ?
			String chosenWord = dictionary[randomCount];
			String scrambledWord = wordScramble(chosenWord);
			// Give the user 3 chances to guess the word
			int guesses = 0;
			String guessedWord = ""; // keep the compiler happy
			while (guesses < TRIES) {
				System.out.println("The scrambled word is " + scrambledWord);
				System.out.println("What is your guess?");
				guessedWord = keyboard.nextLine();
				guessedWord = guessedWord.toLowerCase();
				++guesses;
				if (guessedWord.equals(chosenWord))
					guesses = TRIES; // stop the loop
			}
			boolean won = (guessedWord.equals(chosenWord));
			// If they didn't succeed, tell them the word, otherwise
			// congratulate them
			if (won) {
				System.out.println("Congratulations!  You guessed the word correctly!");
				++wins;
			} else {
				System.out.println("I'm sorry but you lost.  The word was: " + chosenWord);
				++losses;
			}

			// Report the number of wins and losses
			System.out.println("You have won " + wins + " times.");
			System.out.println("You have lost " + losses + " times.");

			// Let them choose to play again
			System.out.println("Do you want to play again? Yes or no");
			repeatPlay = keyboard.nextLine();

		}
	}

	public static String[] readDictionary(String fileName) throws FileNotFoundException {

		// Find the number of words in the dictionary
		int wordsInDictionary = findLength(fileName);

		String[] dictionary = new String[wordsInDictionary];
		// Open the file
		Scanner file = new Scanner(new File(fileName));

		// Read in words, one at a time from the file and put them into the
		// array
		for (int i = 0; i < wordsInDictionary && file.hasNextLine(); ++i) {
			dictionary[i] = file.nextLine();

		}
		file.close();

		return (dictionary);

	}

	public static int findLength(String fileName) throws FileNotFoundException {
		Scanner file = new Scanner(new File(fileName));
		int count = 0;
		while (file.hasNextLine() == true) {
			++count;
			file.nextLine();

		}
		file.close();
		return (count);
	}

	public static String wordScramble(String chosenWord) {
		StringBuilder tempWord = new StringBuilder(chosenWord);
		StringBuilder scrambledTempWord = new StringBuilder();

		// Repeatedly choose a letter to remove from the current word, and put
		// these randomly
		// chosen letters in order in a new word. This uses the StringBuilder
		// class, which is like String
		// but has different methods--including delete and append which are
		// useful here
		// You do not need to understand this part of the code to use it!
		while (tempWord.length() > 0) {
			int letterNumber = (int) (Math.random() * tempWord.length());
			char nextLetter = tempWord.charAt(letterNumber);
			tempWord.deleteCharAt(letterNumber);
			scrambledTempWord.append(nextLetter);
		}
		String scrambledWord = scrambledTempWord.toString();
		return (scrambledWord);
	}

}
