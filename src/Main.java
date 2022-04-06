import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main extends JFrame implements ListSelectionListener, ItemListener, ActionListener, KeyListener {

    //a list of all possible 5 letter words in English
    public static HashSet<String> dictionary = new HashSet<>();

    //a smaller list of words for selecting the target word from (i.e. the word the player needs to guess)
    public static ArrayList<String> targetWords = new ArrayList<>();

    public static String targetWord = "";
    public static int ammountOfGuesses = 6;

    JLabel guessLabel1, guessLabel2, guessLabel3, guessLabel4, guessLabel5, guessLabel6;
    JLabel guessedLabel1, guessedLabel2, guessedLabel3, guessedLabel4, guessedLabel5, guessedLabel6;

    static JTextField guessField1, guessField2, guessField3, guessField4, guessField5, guessField6;
    JButton submit;


    GridBagConstraints gbc = new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 30, 0, 30), 0, 0);
    public Main(){
        this.setMinimumSize(new Dimension(500, 700));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new GridBagLayout());


        JLabel title = new JLabel("<html><span style='color: teal;'>Wordle</span></html>");
        title.setFont (title.getFont().deriveFont(64.0f));
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(title, gbc);

        guessLabel1 = new JLabel("Guess 1:");
        guessLabel1.setFont(guessLabel1.getFont().deriveFont(24.0f));
        gbc.gridx = 0;
        gbc.gridy = 1;

        this.add(guessLabel1,gbc);
        guessField1 = new JTextField(5);
        guessField1.setFont(guessField1.getFont().deriveFont(18.0f));
        guessField1.addKeyListener(this);
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(guessField1,gbc);
        guessedLabel1 = new JLabel("Guessed 1:");
        guessedLabel1.setFont(guessedLabel1.getFont().deriveFont(30.0f));
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(guessedLabel1,gbc);

        guessLabel2 = new JLabel("Guess 2:");
        guessLabel2.setFont(guessLabel2.getFont().deriveFont(24.0f));
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(guessLabel2,gbc);
        guessField2 = new JTextField(5);
        guessField2.setEnabled(false);
        guessField2.setFont(guessField2.getFont().deriveFont(18.0f));
        guessField2.addKeyListener(this);
        gbc.gridx = 1;
        gbc.gridy = 3;
        this.add(guessField2,gbc);
        guessedLabel2 = new JLabel("Guessed 2:");
        guessedLabel2.setFont(guessedLabel2.getFont().deriveFont(30.0f));
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(guessedLabel2,gbc);


        guessLabel3 = new JLabel("Guess 3:");
        guessLabel3.setFont(guessLabel3.getFont().deriveFont(24.0f));
        gbc.gridx = 0;
        gbc.gridy = 5;
        this.add(guessLabel3,gbc);
        guessField3 = new JTextField(5);
        guessField3.setEnabled(false);
        guessField3.setFont(guessField3.getFont().deriveFont(18.0f));
        guessField3.addKeyListener(this);
        gbc.gridx = 1;
        gbc.gridy = 5;
        this.add(guessField3,gbc);
        guessedLabel3 = new JLabel("Guessed 3:");
        guessedLabel3.setFont(guessedLabel3.getFont().deriveFont(30.0f));
        gbc.gridx = 0;
        gbc.gridy = 6;
        this.add(guessedLabel3,gbc);

        guessLabel4 = new JLabel("Guess 4:");
        guessLabel4.setFont(guessLabel4.getFont().deriveFont(24.0f));
        gbc.gridx = 0;
        gbc.gridy = 7;
        this.add(guessLabel4,gbc);
        guessField4 = new JTextField(5);
        guessField4.setEnabled(false);
        guessField4.setFont(guessField4.getFont().deriveFont(18.0f));
        guessField4.addKeyListener(this);
        gbc.gridx = 1;
        gbc.gridy = 7;
        this.add(guessField4,gbc);
        guessedLabel4 = new JLabel("Guessed 4:");
        guessedLabel4.setFont(guessedLabel4.getFont().deriveFont(30.0f));
        gbc.gridx = 0;
        gbc.gridy = 8;
        this.add(guessedLabel4,gbc);

        guessLabel5 = new JLabel("Guess 5:");
        guessLabel5.setFont(guessLabel5.getFont().deriveFont(24.0f));
        gbc.gridx = 0;
        gbc.gridy = 9;
        this.add(guessLabel5,gbc);
        guessField5 = new JTextField(5);
        guessField5.setEnabled(false);
        guessField5.setFont(guessField5.getFont().deriveFont(18.0f));
        guessField5.addKeyListener(this);
        gbc.gridx = 1;
        gbc.gridy = 9;
        this.add(guessField5,gbc);
        guessedLabel5 = new JLabel("Guessed 5:");
        guessedLabel5.setFont(guessedLabel5.getFont().deriveFont(30.0f));
        gbc.gridx = 0;
        gbc.gridy = 10;
        this.add(guessedLabel5,gbc);

        guessLabel6 = new JLabel("Guess 6:");
        guessLabel6.setFont(guessLabel6.getFont().deriveFont(24.0f));
        gbc.gridx = 0;
        gbc.gridy = 11;
        this.add(guessLabel6,gbc);
        guessField6 = new JTextField(5);
        guessField6.setEnabled(false);
        guessField6.setFont(guessField6.getFont().deriveFont(18.0f));
        guessField6.addKeyListener(this);
        gbc.gridx = 1;
        gbc.gridy = 11;
        this.add(guessField6,gbc);
        guessedLabel6 = new JLabel("Guessed 6:");
        guessedLabel6.setFont(guessedLabel6.getFont().deriveFont(30.0f));
        gbc.gridx = 0;
        gbc.gridy = 12;
        this.add(guessedLabel6,gbc);

        submit = new JButton("Submit");;
        submit.addActionListener(this);
        this.getRootPane().setDefaultButton(submit);
        gbc.gridx = 0;
        gbc.gridy = 13;
        this.add(submit,gbc);



    }


    public static void main(String[] args) {




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


        getTarget();
//        startGame();

                SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });

    }


    public static void startGame(){
        while(ammountOfGuesses > 0) {
//            askForGuess();
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
    public static void askForGuess(String guess) {
        //Takes user input and checks if it's valid


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
//        correctLetters = removeNull(correctLetters);
        return correctLetters;
    }

    public static String[] containsLetters(String guess){
        String[] guessLetterArray = guess.split("");
        String[] targetLetterArray =  targetWord.split("");
        String[] wrongPlaceLetters = new String[guessLetterArray.length];
        for (int i = 0; i < guessLetterArray.length; i++) {
            for (int j = 0; j < targetLetterArray.length; j++) {
                if (guessLetterArray[i].equals(targetLetterArray[j]) && j != i) {
                    wrongPlaceLetters[i] = guessLetterArray[i];
                }
            }
        }
//        wrongPlaceLetters = removeNull(wrongPlaceLetters);
        return wrongPlaceLetters;
    }

    public static String[] falseLetters(String guess) {
        //Creates an array of the letters in the guess that are not in the target word
        String[] guessLetterArray = guess.split("");
        String[] targetLetterArray = targetWord.split("");
        String[] falseLetters = new String[guessLetterArray.length];
        int falseLettersCount = 0;

        for (int i = 0; i < guessLetterArray.length; i++) {
            boolean isInTarget = false;
            for (int j = 0; j < targetLetterArray.length; j++) {
                if (guessLetterArray[i].equals(targetLetterArray[j])) {
                    isInTarget = true;
                }
            }
            if (!isInTarget) {
                falseLetters[falseLettersCount] = guessLetterArray[i];
                falseLettersCount++;
            }

        }
//        falseLetters = removeNull(falseLetters);
        return falseLetters;

    }

    //method to remove null from array
    public static String[] removeNull(String[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                count++;
            }
        }
        String[] newArray = new String[count];
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                newArray[i] = array[i];
            }
        }
        return newArray;
    }




    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String guess = "";

            if(e.getActionCommand().equals("Submit") && ammountOfGuesses == 6){
               guess = guessField1.getText();
                guess = guess.toLowerCase();
               if(isGuessValid(guess)){
                   guessField1.setEnabled(false);
                   guessField2.setEnabled(true);
                   guessedLabel1.setText(guess);
               }

            }
            if(e.getActionCommand().equals("Submit") && ammountOfGuesses == 5){
                guess = guessField2.getText();
                guess = guess.toLowerCase();
                if(isGuessValid(guess)){
                    guessField2.setEnabled(false);
                    guessField3.setEnabled(true);
                    guessedLabel2.setText(guess);
                }

            }
            if(e.getActionCommand().equals("Submit") && ammountOfGuesses == 4){
             guess = guessField3.getText();
                guess = guess.toLowerCase();

                if(isGuessValid(guess)){
                    guessField3.setEnabled(false);
                    guessField4.setEnabled(true);
                    guessedLabel3.setText(guess);
                }
            }
            if(e.getActionCommand().equals("Submit") && ammountOfGuesses == 3){
                guess = guessField4.getText();
                guess = guess.toLowerCase();

                if(isGuessValid(guess)){
                    guessField4.setEnabled(false);
                    guessField5.setEnabled(true);
                    guessedLabel4.setText(guess);
                }
            }
            if(e.getActionCommand().equals("Submit") && ammountOfGuesses == 2){
                guess = guessField5.getText();
                guess = guess.toLowerCase();

                if(isGuessValid(guess)){
                    guessField5.setEnabled(false);
                    guessField6.setEnabled(true);
                    guessedLabel5.setText( guess);
                }
            }
            if(e.getActionCommand().equals("Submit") && ammountOfGuesses == 1){
                guess = guessField6.getText();
                guess = guess.toLowerCase();

                if(isGuessValid(guess)){
                    guessField6.setEnabled(false);
                    guessedLabel6.setText(guess);
                }
            }

        //Make user input lower case to prevent user inputting capital letters.


        //If guess is valid then tell the user which letters are correct and also subtract one from the ammount of guesses.
        //Then loops through asking for a new guess.
        if (isGuessValid(guess)) {
            System.out.println(Arrays.toString(checkLetters(guess)));
            System.out.println(Arrays.toString(matchCorrectLetters(guess)) + " letters are in correct place");
            System.out.println(Arrays.toString(containsLetters(guess)) + " letters are in incorrect place");
            System.out.println(Arrays.toString(falseLetters(guess)) + " letters are not in the word");
            ammountOfGuesses--;
            if(ammountOfGuesses == 0){
                System.out.println("You lost, you have ran out of guesses!");
                System.out.println("The word was: " + targetWord);
                System.exit(0);
            }
        } else {
            System.out.println("Invalid guess please enter again");
        }


    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}




