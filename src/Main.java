import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main extends JFrame implements ListSelectionListener, ItemListener, ActionListener {

    //a list of all possible 5 letter words in English
    public static HashSet<String> dictionary = new HashSet<>();

    //a smaller list of words for selecting the target word from (i.e. the word the player needs to guess)
    public static ArrayList<String> targetWords = new ArrayList<>();

    public static String targetWord = "";
    public static int ammountOfGuesses = 5;


    public Main() {
        //load in the two word lists
        try {
            Scanner in_dict = new Scanner(new File("gameDictionary.txt"));
            while (in_dict.hasNext()) {
                dictionary.add(in_dict.next());
            }

            Scanner in_targets = new Scanner(new File("targetWords.txt"));
            while (in_targets.hasNext()) {
                targetWords.add(in_targets.next());
            }
            in_dict.close();
            in_targets.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Create our GUI components
        setMinimumSize(new Dimension(500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Goldsmiths Wordle");

        //Make visible
        setVisible(true);



        getTarget();
        startGame();
        if(ammountOfGuesses == 0){
            System.out.println("You lost, you have ran out of guesses!");
            System.out.println("The word was: " + targetWord);
            System.exit(0);
        }


    }


    public static void main(String[] args) {


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });

    }




    public static void startGame(){
        while(ammountOfGuesses > 0) {
            askForGuess();
        }

    }

    //use this method for selecting a word. It's important for marking that the word you have selected is printed out to the console!
    public static String getTarget() {
        Random r = new Random();
        String target = targetWords.get(r.nextInt(targetWords.size()));
        //don't delete this line.
        System.out.println(target);
        targetWord = target;
        return targetWord;
    }

    //Checks if the guessed word is in the dictionary.
    public static boolean isGuessValid(String guess) {
        //check if the guess is a valid word
        if (dictionary.contains(guess)) {
            return true;
        } else {
            return false;
        }
    }

    //Asks the user for a guess and checks if it's valid and tells user which letters are correct
    public static void askForGuess() {
        //Takes user input and checks if it's valid
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your guess: ");
        String guess = in.nextLine();
        //Make user input lower case to prevent user inputting capital letters.
        guess = guess.toLowerCase();

        //If guess is valid then tell the user which letters are correct and also subtract one from the ammount of guesses.
        //Then loops through asking for a new guess.
        if (isGuessValid(guess)) {
            System.out.println(Arrays.toString(checkLetters(guess)));
            System.out.println(Arrays.toString(matchCorrectLetters(guess)));
            ammountOfGuesses--;
            startGame();
        } else {
            System.out.println("Invalid guess please enter again");
        }
    }

    //Returns an array of true or false values for each letter in the guess matching the target word
    //Also counts correct guesses in order to calculate the ammount of letters left to guess
    public static boolean[] checkLetters(String guess) {
        String[] guessLetterArray = guess.split("");
        String[] targetLetterArray =  targetWord.split("");
        boolean[] guessedIndex = new boolean[guessLetterArray.length];
        int correctGuesses = 0;

        //Checks if the letters in the target word match the letters in the guess and creates a boolean array
        for (int i = 0; i < guessLetterArray.length; i++) {
            if (guessLetterArray[i].equals(targetLetterArray[i])) {
                guessedIndex[i] = true;
            } else {
                guessedIndex[i] = false;
            }
        }

        //Counts the amount of correct guesses
        for (int j = 0; j < targetLetterArray.length; j++) {
            if (guessedIndex[j] == true) {
                correctGuesses++;
            }
        }

        //If there are enough correct guesses, the game is won
            if(correctGuesses == guessLetterArray.length) {
                System.out.println("You win!");
                ammountOfGuesses = 0;
                System.exit(0);
            }

            //Otherwise return the array of true or false values
            System.out.println("You have " + correctGuesses + " correct guesses");
            return guessedIndex;
    }

    //Returns an array of the correct letters in the target word
    public static String[] matchCorrectLetters(String guess) {
        String[] guessLetterArray = guess.split("");
        String[] targetLetterArray =  targetWord.split("");
        String[] correctLetters = new String[guessLetterArray.length];

        for (int i = 0; i < guessLetterArray.length; i++) {
            if (guessLetterArray[i].equals(targetLetterArray[i])) {
                correctLetters[i] = guessLetterArray[i];
            }
        }

        return correctLetters;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }




}




