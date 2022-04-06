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

    //Statistics
    public static int statsFirstGuess = 0, statsSecondGuess = 0, statsThirdGuess = 0, statsFourthGuess = 0, statsFifthGuess = 0, statsSixthGuess = 0, wordsGuessed, wordsMissed, wordStreak;

    //Initialise GUI elements
    static JTextField guessField1, guessField2, guessField3, guessField4, guessField5, guessField6;
    static JLabel guessesLeft;

    JLabel guessLabel1, guessLabel2, guessLabel3, guessLabel4, guessLabel5, guessLabel6;
    JLabel guessedLabel1, guessedLabel2, guessedLabel3, guessedLabel4, guessedLabel5, guessedLabel6;
    JButton submit;

    JComboBox<String> menu;

    GridBagConstraints gbc = new GridBagConstraints();

    //GUI
    public Main(){
        //Initialise the GUI
        this.setMinimumSize(new Dimension(600, 700));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new GridBagLayout());
        this.setTitle("Wordle");
        this.getContentPane().setBackground(new Color(66, 64, 64));

        //Set title
        JLabel title = new JLabel("<html><span style=\"color:#FFFF00\">W</span><span style=\"color:#808080\">O</span><span style=\"color:#00FF00\">R</span><span style=\"color:#FFFF00\">D</span><span style=\"color:#808080\">L</span><span style=\"color:#00FF00\">E</span></html>");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 64f));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.add(title, gbc);

        //Create menu
        String option[]={"Menu", "New Game", "Stats", "Help", "Exit"};
        menu = new JComboBox(option);
        menu.addItemListener(this);
        gbc.gridx=0;
        gbc.gridy=1;
        menu.setPreferredSize(new Dimension(400,30));
        this.add(menu,gbc);

        //Guess 1
        //Guess label
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0,30,0,30);
        guessLabel1 = new JLabel("Guess 1:");
        guessLabel1.setFont(guessLabel1.getFont().deriveFont(24.0f));
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(guessLabel1,gbc);
        //Guess field
        guessField1 = new JTextField(5);
        guessField1.setFont(guessField1.getFont().deriveFont(18.0f));
        guessField1.addKeyListener(this);
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(guessField1,gbc);
        //Guessed word label
        guessedLabel1 = new JLabel("Guessed 1:");
        guessedLabel1.setFont(guessedLabel1.getFont().deriveFont(Font.BOLD, 30f));
        guessedLabel1.setBackground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(guessedLabel1,gbc);

        //Guess 2
        //Guess label
        guessLabel2 = new JLabel("Guess 2:");
        guessLabel2.setFont(guessLabel2.getFont().deriveFont(24.0f));
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(guessLabel2,gbc);
        //Guess field
        guessField2 = new JTextField(5);
        guessField2.setEnabled(false);
        guessField2.setFont(guessField2.getFont().deriveFont(18.0f));
        guessField2.addKeyListener(this);
        gbc.gridx = 1;
        gbc.gridy = 4;
        this.add(guessField2,gbc);
        //Guessed word label
        guessedLabel2 = new JLabel("Guessed 2:");
        guessedLabel2.setFont(guessedLabel2.getFont().deriveFont(Font.BOLD, 30f));
        gbc.gridx = 0;
        gbc.gridy = 5;
        this.add(guessedLabel2,gbc);

        //Guess 3
        //Guess label
        guessLabel3 = new JLabel("Guess 3:");
        guessLabel3.setFont(guessLabel3.getFont().deriveFont(24.0f));
        gbc.gridx = 0;
        gbc.gridy = 6;
        this.add(guessLabel3,gbc);
        //Guess field
        guessField3 = new JTextField(5);
        guessField3.setEnabled(false);
        guessField3.setFont(guessField3.getFont().deriveFont(18.0f));
        guessField3.addKeyListener(this);
        gbc.gridx = 1;
        gbc.gridy = 6;
        this.add(guessField3,gbc);
        //Guessed word label
        guessedLabel3 = new JLabel("Guessed 3:");
        guessedLabel3.setFont(guessedLabel3.getFont().deriveFont(Font.BOLD, 30f));
        gbc.gridx = 0;
        gbc.gridy = 7;
        this.add(guessedLabel3,gbc);

        //Guess 4
        //Guess label
        guessLabel4 = new JLabel("Guess 4:");
        guessLabel4.setFont(guessLabel4.getFont().deriveFont(24.0f));
        gbc.gridx = 0;
        gbc.gridy = 8;
        this.add(guessLabel4,gbc);
        //Guess field
        guessField4 = new JTextField(5);
        guessField4.setEnabled(false);
        guessField4.setFont(guessField4.getFont().deriveFont(18.0f));
        guessField4.addKeyListener(this);
        gbc.gridx = 1;
        gbc.gridy = 8;
        this.add(guessField4,gbc);
        //Guessed word label
        guessedLabel4 = new JLabel("Guessed 4:");
        guessedLabel4.setFont(guessedLabel4.getFont().deriveFont(Font.BOLD, 30f));
        gbc.gridx = 0;
        gbc.gridy = 9;
        this.add(guessedLabel4,gbc);

        //Guess 5
        //Guess label
        guessLabel5 = new JLabel("Guess 5:");
        guessLabel5.setFont(guessLabel5.getFont().deriveFont(24.0f));
        gbc.gridx = 0;
        gbc.gridy = 10;
        this.add(guessLabel5,gbc);
        //Guess field
        guessField5 = new JTextField(5);
        guessField5.setEnabled(false);
        guessField5.setFont(guessField5.getFont().deriveFont(18.0f));
        guessField5.addKeyListener(this);
        gbc.gridx = 1;
        gbc.gridy = 10;
        this.add(guessField5,gbc);
        //Guessed word label
        guessedLabel5 = new JLabel("Guessed 5:");
        guessedLabel5.setFont(guessedLabel5.getFont().deriveFont(Font.BOLD, 30f));
        gbc.gridx = 0;
        gbc.gridy = 11;
        this.add(guessedLabel5,gbc);

        //Guess 6
        //Guess label
        guessLabel6 = new JLabel("Guess 6:");
        guessLabel6.setFont(guessLabel6.getFont().deriveFont(24.0f));
        gbc.gridx = 0;
        gbc.gridy = 12;
        this.add(guessLabel6,gbc);
        //Guess field
        guessField6 = new JTextField(5);
        guessField6.setEnabled(false);
        guessField6.setFont(guessField6.getFont().deriveFont(18.0f));
        guessField6.addKeyListener(this);
        gbc.gridx = 1;
        gbc.gridy = 12;
        this.add(guessField6,gbc);
        //Guessed word label
        guessedLabel6 = new JLabel("Guessed 6:");
        guessedLabel6.setFont(guessedLabel6.getFont().deriveFont(Font.BOLD, 30f));
        gbc.gridx = 0;
        gbc.gridy = 13;
        this.add(guessedLabel6,gbc);

        //Submit button
        submit = new JButton("Submit");;
        submit.addActionListener(this);
        //Allow enter to be pressed as default button to submit
        this.getRootPane().setDefaultButton(submit);
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.gridwidth = 2;
        submit.setPreferredSize(new Dimension(270,30));
        this.add(submit,gbc);

        //Guesses left counter
        guessesLeft = new JLabel("Guesses left: 6");
        guessesLeft.setFont(guessesLeft.getFont().deriveFont(14f));
        gbc.gridx = 0;
        gbc.gridy = 15;
        this.add(guessesLeft,gbc);

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

    //Start game
       startGame();

    }

    //Starts the game
    private static void startGame() {
        //Starts the game
        getTarget();
        ammountOfGuesses = 6;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });

    }

    //Use this method for selecting a word. It's important for marking that the word you have selected is printed out to the console!
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
            guessesLeft.setText("Guesses left: " + (ammountOfGuesses-1));
            return true;
        } else {
            return false;
        }
    }

    //Checks if letters are in the word but placed wrong
    public static boolean[] wrongPlacedLetters(String guess){
        String[] guessLetterArray = guess.split("");
        String[] targetLetterArray =  targetWord.split("");
        boolean[] wrongPlaceLetters = new boolean[guessLetterArray.length];
        for (int i = 0; i < guessLetterArray.length; i++) {
            for (int j = 0; j < targetLetterArray.length; j++) {
                if (guessLetterArray[i].equals(targetLetterArray[j]) && j != i) {
                    wrongPlaceLetters[i] = true;
                }
            }
        }
        return wrongPlaceLetters;
    }

    //Checks if letters are placed correctly
    public static boolean[] correctPlacedLetters(String guess) {
        //Creates an array of the letters in the guess that are not in the target word
        String[] guessLetterArray = guess.split("");
        String[] targetLetterArray = targetWord.split("");
        boolean[] falseLettersIndex = new boolean[guessLetterArray.length];
      //If array contains element, set index of boolean array to true
        for (int i = 0; i < guessLetterArray.length; i++) {
            if(guessLetterArray[i].equals(targetLetterArray[i])){
                falseLettersIndex[i] = true;
            }
        }

        return falseLettersIndex;

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        //Menu
        if(e.getStateChange() == ItemEvent.SELECTED){
            //Get option by index selected
            if(menu.getSelectedIndex() == 1){
                //Restart game
                 this.dispose();
             startGame();
            }
            if(menu.getSelectedIndex() == 2){
                //Stats
                JOptionPane.showMessageDialog(null, "Words guessed on 1st try: " + statsFirstGuess + "\n"
                        + "Words guessed on 2nd try: " + statsSecondGuess + "\n"
                        + "Words guessed on 3rd try: " + statsThirdGuess + "\n"
                        + "Words guessed on 4th try: " + statsFourthGuess + "\n"
                        + "Words guessed on 5th try: " + statsFifthGuess + "\n"
                        + "Words guessed on 6th try: " + statsSixthGuess + "\n"
                        + "Words guessed total: " + wordsGuessed + "\n"
                        + "Words streak: " + wordStreak + "\n"
                        + "Words missed: " + wordsMissed + "\n");

            }
            if (menu.getSelectedIndex() == 3){
                //Help
                JOptionPane.showMessageDialog(null, "1: You have to guess the Wordle in six goes or less\n" +
                        "2: Every word you enter must be in the word list. That hasn't been disclosed, but presumably it's based on a dictionary.\n" +
                        "3: A correct letter turns green\n" +
                        "4: A correct letter in the wrong place turns yellow\n" +
                        "5: An incorrect letter turns gray\n" +
                        "6:Letters can be used more than once\n" +
                        "7:Have fun!\n");
            }
            if(menu.getSelectedIndex() == 4){
                //Exit
                System.exit(0);
            }
        }
    }

    //Method for colour coding letters based on condition
    public static String[] colourLetters(String guess){
        String [] guessArray;
        guessArray = guess.split("");
        for (int i = 0; i < guessArray.length; i++) {
            //If letter is not in word at all
            if(!correctPlacedLetters(guess)[i] && !wrongPlacedLetters(guess)[i]){
                guessArray[i] = "<font color=\"gray\">"+ guessArray[i] +"</font>";
            }
            //If letter is in the wrong place
            if(wrongPlacedLetters(guess)[i] && !correctPlacedLetters(guess)[i]){
                guessArray[i] = "<font color=\"yellow\">"+ guessArray[i] +"</font>";
            }
            //If letter is placed correctly within the word
            else{
                guessArray[i] = "<font color=\"green\">"+ guessArray[i] +"</font>";
            }
        }
        //Return all letters as caps for better display
        for (int i = 0; i < guessArray.length; i++) {
            guessArray[i] = guessArray[i].toUpperCase(Locale.ROOT);
        }
        return guessArray;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String guess = "";
            if(e.getActionCommand().equals("Submit") && ammountOfGuesses == 6){
               guess = guessField1.getText();
                guess = guess.toLowerCase();
        //Makes sure guess is in word list
               if(isGuessValid(guess)){
                   //Split our string and colour code the letters
                   String[] guessArray = colourLetters(guess);
                   //Disable the field and enable the next
                   guessField1.setEnabled(false);
                   guessField2.setEnabled(true);
                   //Print result within lable
                   guessedLabel1.setText("<html>" + "Guessed 1: " + guessArray[0] + guessArray[1] +  guessArray[2] + guessArray[3] + guessArray[4] +"</html>");
               }

            }
            if(e.getActionCommand().equals("Submit") && ammountOfGuesses == 5){
                guess = guessField2.getText();
                guess = guess.toLowerCase();
                if(isGuessValid(guess)){
                    String[] guessArray = colourLetters(guess);
                    guessField2.setEnabled(false);
                    guessField3.setEnabled(true);
                    guessedLabel2.setText("<html>" + "Guessed 2: " +guessArray[0] + guessArray[1] +  guessArray[2] + guessArray[3] + guessArray[4] +"</html>");
                }

            }
            if(e.getActionCommand().equals("Submit") && ammountOfGuesses == 4){
             guess = guessField3.getText();
                guess = guess.toLowerCase();
                if(isGuessValid(guess)){
                    String[] guessArray = colourLetters(guess);
                    guessField3.setEnabled(false);
                    guessField4.setEnabled(true);
                    guessedLabel3.setText("<html>" + "Guessed 3: " +guessArray[0] + guessArray[1] +  guessArray[2] + guessArray[3] + guessArray[4] +"</html>");
                }
            }
            if(e.getActionCommand().equals("Submit") && ammountOfGuesses == 3){
                guess = guessField4.getText();
                guess = guess.toLowerCase();
                if(isGuessValid(guess)){
                    String[] guessArray = colourLetters(guess);
                    guessField4.setEnabled(false);
                    guessField5.setEnabled(true);
                    guessedLabel4.setText("<html>" + "Guessed 4: " + guessArray[0] + guessArray[1] +  guessArray[2] + guessArray[3] + guessArray[4] +"</html>");
                }
            }
            if(e.getActionCommand().equals("Submit") && ammountOfGuesses == 2){
                guess = guessField5.getText();
                guess = guess.toLowerCase();
                if(isGuessValid(guess)){
                    String[] guessArray = colourLetters(guess);
                    guessField5.setEnabled(false);
                    guessField6.setEnabled(true);
                    guessedLabel5.setText("<html>" + "Guessed 5: " +guessArray[0] + guessArray[1] +  guessArray[2] + guessArray[3] + guessArray[4] +"</html>");
                }
            }
            if(e.getActionCommand().equals("Submit") && ammountOfGuesses == 1){
                guess = guessField6.getText();
                guess = guess.toLowerCase();
                if(isGuessValid(guess)){
                    String[] guessArray = colourLetters(guess);
                    guessField6.setEnabled(false);
                    guessedLabel6.setText(guess);
                    guessedLabel6.setText("<html>" + "Guessed 6: " +guessArray[0] + guessArray[1] +  guessArray[2] + guessArray[3] + guessArray[4] +"</html>");
                }
            }

        //If guess is valid then tell the user which letters are correct and also subtract one from the ammount of guesses.
        //Then loops through asking for a new guess.
        if (isGuessValid(guess)) {
            //Win conditional + statistics counter
            if (guess.equals(targetWord)) {
                if(ammountOfGuesses == 6){
                    statsFirstGuess += 1;
                }else if(ammountOfGuesses == 5){
                    statsSecondGuess += 1;
                } else if(ammountOfGuesses == 4){
                    statsThirdGuess += 1;
                } else if(ammountOfGuesses == 3){
                    statsFourthGuess += 1;
                } else if(ammountOfGuesses == 2){
                    statsFifthGuess += 1;
                } else if(ammountOfGuesses == 1){
                    statsSixthGuess += 1;
                }
                wordsGuessed += 1;
                wordStreak += 1;
                JOptionPane.showMessageDialog(null, "You win! The word was: " + targetWord.toUpperCase(Locale.ROOT));
            }
            //If guess is valid but not equal to target word we subtract one from the ammount of guesses.
            ammountOfGuesses--;
            //Conditional if the user has no guesses left.
            if(ammountOfGuesses == 0){
                wordStreak = 0;
                wordsMissed += 1;
                JOptionPane.showMessageDialog(null, "You lost! The word was: " + targetWord.toUpperCase(Locale.ROOT));
            }
        } else {
            //If the guess is invalid we print a message but do not subtract a guess
            JOptionPane.showMessageDialog(null, "Invalid guess, please try again");
        }

    }



    //Methods for implementations of the ActionListener interface
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}




