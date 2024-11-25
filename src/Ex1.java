import java.util.Random;
import java.util.Scanner;

/**
 * Introduction to Computer Science, Ariel University, Ex1 (manual Example + a Template for your solution)
 * See: https://docs.google.com/document/d/1C1BZmi_Qv6oRrL4T5oN9N2bBMFOHPzSI/edit?usp=sharing&ouid=113711744349547563645&rtpof=true&sd=true
 *
 * Ex1 Bulls & Cows - Automatic solution.
 * **** Add a general readme text here ****
 * Name: Harel Weinberg
 * ID: 322351883
 *
 * Add your explanation here:
 * I'm making an array of all the possible guesses combinations.
 * Then I'm running through the array and removing the values that doesn't fit the result.
 * Repeating the process until I get to the secret code.
 *
 * **** General Solution (algorithm) ****
 * Add your explanation here:
 * I started with creating an array of strings, when each string
 * representing a possible guess, so the array contain all the possible guesses.
 * Then I chose random string from the all the
 * possible guesses and check if it's the secret code.
 * According to the result I got I'm erasing the options that can't be my secret code
 * by checking each guess if it can be the secret code by comparing the code to
 * the guess I try.
 * If the result of Bulls and Cows is different from the result I got
 * from the user than it can't  be the secret code,
 * because if it was the secret code it would have given me the same result as the user.
 * Then I'm creating a new array with all the new possible guesses.
 * I'm returning on this process until I got the solution that is the same as my secret code.
 *
 * **** Results ****
 * Make sure to state the average required guesses
 * for 2,3,4,5,6 digit code: 6.022
 *
 * Average required guesses 2: ___5.19
 * Average required guesses 3: ___5.65
 * Average required guesses 4: ___5.93
 * Average required guesses 5: ___6.41
 * Average required guesses 6: ___6.93
 *
 */
public class Ex1 {

    public static double average=0;

    public static final String Title = "Ex1 demo: manual Bulls & Cows game";

    public static void main(String[] args) {
        average=0;

            BP_Server game = new BP_Server();   // Starting the "game-server"
            long myID = 322351883;             // Your ID should be written here
            int numOfDigits = 2;                // Number of digits [2,6]
            game.startGame(myID, numOfDigits);  // Starting a game
            System.out.println(Title + " with code of " + numOfDigits + " digits");
//        manualEx1Game(game);
            autoEx1Game(game); // you should implement this function )and any additional required functions).


    }

    public static void manualEx1Game(BP_Server game) {
        Scanner sc = new Scanner(System.in);
        int ind = 1;      // Index of the guess
        int numOfDigits = game.getNumOfDigits();
        double max = Math.pow(10, numOfDigits);
        while (game.isRunning()) {           // While the game is running (the code has not been found yet
            System.out.println(ind + ") enter a guess: ");
            int g = sc.nextInt();
            if (g >= 0 && g < max) {
                int[] guess = toArray(g, numOfDigits); // int to digit array
                int[] res = game.play(guess); // Playing a round and getting the B,C
                if (game.isRunning()) {     // While the game is running
                    System.out.println(ind + ") B: " + res[0] + ",  C: " + res[1]); // Prints the Bulls [0], and the Cows [1]
                    ind += 1;               // Increasing the index
                }
            } else {
                System.out.println("ERR: wrong input, try again");
            }
        }
        System.out.println(game.getStatus());
    }

    /**
     * Simple parsing function that gets an int and returns an array of digits
     * @param a    - a natural number (as a guess)
     * @param size - number of digits (to handle the 00 case).
     * @return an array of digits
     */
    private static int[] toArray(int a, int size) {
        int[] c = new int[size];
        for (int j = 0; j < c.length; j += 1) {
            c[j] = a % 10;
            a = a / 10;
        }
        return c;
    }

    /**
     * This function solves the Bulls & Cows game automatically.
     * You should implement
     * **** Add a specific explanation for each function ****
     */
    public static void autoEx1Game(BP_Server game) {
        int ind = 1;      // Index of the guess
        int numOfDigits = game.getNumOfDigits();
        double max = Math.pow(10, numOfDigits);
        String[] allGuesses = allGuesses(numOfDigits);

        while (game.isRunning()) {           // While the game is running (the code has not been found yet
            average++;
            System.out.println(ind + ") enter a guess: ");
            int g = makeGuess(allGuesses);
            if (g >= 0 && g < max) {
                int[] guess = toArray(g, numOfDigits);// int to digit array
                int[] res = game.play(guess); // Playing a round and getting the B,C
                if (game.isRunning()) {
                    System.out.println(ind + ") B: " + res[0] + ",  C: " + res[1]); // Prints the Bulls [0], and the Cows [1]
                    ind += 1;               // Increasing the index
                    allGuesses = deleteOptions(guess, res, allGuesses);// While the game is running
                }
            } else {
                System.out.println("ERR: wrong input, try again");
            }
        }
        System.out.println(game.getStatus());
    }


    /** This function is generating all possible guesses for a given number of digits.
     * @param numDigits The number of digits for each guess.
     * @return An array containing all possible guesses.*/
    public static String[] allGuesses(int numDigits) {
        // Calculating the total number of combinations
        int totalCombinations = (int) Math.pow(10, numDigits);
        // Create an array to store all possible guesses
        String[] allPossibleGuesses = new String[totalCombinations];

        //Generating all possible combinations
        for (int i = 0; i < totalCombinations; i++) {
            // Creating a guess with leading zeros to ensure the correct number of digits
            String guess = String.format("%0" + numDigits + "d", i);
            // Store the guess in the array
            allPossibleGuesses[i] = guess;
        }
        // Return the array of all possible guesses
        return allPossibleGuesses;
    }

    /**This function is Making a guess by selecting a random non-"A" guess from the provided array of guesses.
    //* @param allGuesses An array containing all possible guesses.
    // * @return The selected guess, reversed as an integer, or 0 if no valid guess is found.*/
    public static int makeGuess(String[] allGuesses) {
        // Counting the number of non-"A" guesses
        int counter = 0;
        for (int i = 0; i < allGuesses.length; i++) {
            if (!allGuesses[i].equals("A"))
                counter++;
        }
        // Generate a random number within the range of non-"A" guesses
        Random random = new Random();
        int randomNum = random.nextInt(counter);
        // Select a random non-"A" guess based on the generated random number we did.
        for (int i = 0; i < allGuesses.length; i++) {
            if (!allGuesses[i].equals("A")) {
                randomNum--;
                if (randomNum <= 0) {
                    // Return the selected guess, reversed as an integer
                    return Integer.parseInt(new StringBuilder(allGuesses[i]).reverse().toString());
                }
            }
        }
        // Return 0 if no valid guess is found.
        return 0;
    }

    /**This function calculates the number of bulls (b) and cows (c) for a given guess.
    // * @param allGuess The target guess string.
    // * @param guess An array representing the current guess.
    // * @return An array containing the number of bulls (b) and cows (c) respectively. */
    static int[] calBandC(String allGuess, int[] guess) {
        // Initialize counters for bulls (b) and cows (c)
        int b = 0;
        int c = 0;
        // Convert the guess array to a string representation
        String guessCopy = arrayToString(guess);
        // Create a copy of the target guess string
        String allGuessCopy = allGuess;

        // Calculate bulls (b)
        int r = guessCopy.length();
        for (int i = 0; i < r; i++) {
            if (allGuess.charAt(i) == (char) guessCopy.charAt(i)) {
                // If the characters match, increment the bulls count (b)
                b++;
                // Remove the matched characters from both strings to avoid counting them as cows later
                allGuessCopy = removeChar(allGuessCopy, i);
                guessCopy = removeChar(guessCopy, i);
            }
        }

        // Calculate cows (c)
        for (int i = 0; i < guess.length; i++) {
            for (int j = 0; j < allGuessCopy.length(); j++) {
                if (allGuessCopy.charAt(j)!= 'x' && guessCopy.charAt(i)!= 'x' && allGuessCopy.charAt(j) == guessCopy.charAt(i)) {
                    // If a cow is found, increment the cows count (c)
                    c++;
                    // Remove the matched characters from both strings to avoid counting them as additional cows
                    allGuessCopy = removeChar(allGuessCopy, j);
                    guessCopy = removeChar(guessCopy, i);
                    // Break out of the inner loop to avoid counting the same character multiple times
                    break;
                }
            }
        }
        // Return an array containing the number of bulls (b) and cows (c)
        return new int[]{b, c};
    }

    /**This function is deleting options from the array of all guesses based on the provided guess and result.
     @param guess An array representing the current guess.
     @param res An array representing the result of the current guess.
     @param allGuesses An array containing all possible guesses.
     @return The updated array of all guesses with invalid options removed. */
    public static String[] deleteOptions(int[] guess, int[] res, String[] allGuesses) {
        // Iterate through all possible guesses
        for (int i = 0; i < allGuesses.length; i++) {
            // Check if the guess is not already marked as invalid
            if (!allGuesses[i].equals("A")) {
                // Calculate the bulls and cows for the current guess
                int[] currentRes = calBandC(allGuesses[i], guess);
                // If the calculated result does not match the provided result, mark the guess as invalid
                if (res[0] != currentRes[0] || res[1] != currentRes[1]) {
                    allGuesses[i] = "A";
                }
            }
        }
        // Return the updated array of all guesses
        return allGuesses;
    }

    /**This function removes a character at the specified index from the given string.
     @param string The input string from which to remove the character.
     @param index The index of the character to remove.
     @return The string with the character removed. */
    public static String removeChar(String string, int index) {
        // Initialize an empty string to store the modified string
        String newString = "";
        // Iterate through each character of the input string
        for (int i = 0; i < string.length(); i++) {
            // Check if the current index matches the index of the character to remove
            if (i == index) {
                // If it matches, replace the character with 'x'
                newString += "x";
            } else {
                // If it doesn't match, keep the original character
                newString += string.charAt(i);
            }
        }
        // Return the modified string with the character removed
        return newString;
        }

    /**This function converts an integer array to a string representation.
     @param arr The input integer array to convert.
     @return The string representation of the input array. */
    public static String arrayToString(int[] arr) {
        // Initialize an empty string to store the converted array
        String str = "";
        // Iterate through each element of the input array
        for (int i = 0; i < arr.length; i++) {
            // Append the current element to the string
            str += arr[i];
        }
        // Return the string representation of the array
        return str;
    }

    /** This function simulate the main that use to check the average...
      */
    public static double avmain(long myID,int numOfDigits){
        average=0;
        BP_Server game = new BP_Server();   // Starting the "game-server"

        game.startGame(myID, numOfDigits);  // Starting a game
        System.out.println(Title + " with code of " + numOfDigits + " digits");
//        manualEx1Game(game);
        autoEx1Game(game); // you should implement this function )and any additional required functions).
        // Returning the average value
        return average;
    }

}