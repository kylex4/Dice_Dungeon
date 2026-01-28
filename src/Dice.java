import java.util.Scanner;


/**
 * This class represents a Dice class
 * @author Kyle Xu
 */
public class Dice {
    /** The number from the first die */
    private int value1;

    /** The number from the second die */

    private int value2;

    /** The number from the current die you are rolling */
    private int dieNumber;

    /** The number of rerolls the user has */
    private int reroll;


    /** Initiates the Dice class */
    public Dice() {
        this.value1 = 0;
        this.value2 = 0;
        this.reroll = 2;
        this.dieNumber = 1;
    }

    Scanner scan = new Scanner(System.in);

    /** Returns the value on the first die
     *
     * @return The value of the first die
     */

    public int getValue1() {
        return value1;
    }

    /** Returns the value of the second die
     *
     * @return The value of the second die
     */

    public int getValue2() {
        return value2;
    }

    /** Returns the number of rerolls the user has
     *
     * @return The number of rerolls
     */

    public int getReroll() {
        return reroll;
    }

    /** Returns the current die value
     *
     * @return The current die value
     */

    public int getNumber() {
        return dieNumber;
    }

    /** Checks if three turns has passed before giving the user a reroll without repeating on the same turn it is gained.
     * Announces to the user that they'll gain a reroll
     *
     * @param turn The current turn the user is on
     * @param name The name of the user
     * @param gained Whether a reroll was already gained on the current turn
     */
    public void gainReroll(int turn, String name, boolean gained){
        if (turn % 3 == 0 && !gained){
            System.out.println(name + " gains a reroll!");
            System.out.println("");
            reroll++;
        }

        if (reroll > 5){
            System.out.println("Max rerolls reached");
            reroll = 5;
        }
    }

    /** Chooses a random number between 1 - 6 and sets it as the current die value.
     * Announces the value of the current die the user has rolled as well
     *
     * @param name The name of the user
     * @return The value of the latest die the user has rolled
     */

    public int roll(String name){
        dieNumber = (int) (Math.random() * 6) + 1;
        System.out.println( name + " rolls a " + dieNumber);
        return dieNumber;
    }

    public int rolling(String character){
        dieNumber = (int) (Math.random() * 6) + 1;
        System.out.println( character + " rerolls a " + dieNumber);
        System.out.println("");
        return dieNumber;
    }

    // Same as rolling a dice except it's used to choose the enemy's move so it won't print anything
    public int rollMove(){
        dieNumber = (int) (Math.random() * 6) + 1;
        return dieNumber;
    }

    // Prompts message to reroll only when there are available rerolls. Can only reroll once per die
    public void reroll(int value, int rerolls, String character){
        if (rerolls > 0){
            System.out.println("");
            System.out.println("Would you like to reroll your die?");
            System.out.println("[1]. Keep Current Die Value (" + dieNumber + ")");
            System.out.println("[2]. Reroll (Current Rerolls: " + rerolls + ")");
            int option = scan.nextInt();

            if (option == 2 && value == 1){
                value1 = rolling(character);
                reroll -= 1;
            }

            if (option == 2 && value == 2){
                value2 = rolling(character);
                reroll -= 1;
            }

            if (option == 1 && value == 1){
                value1 = dieNumber;
            }

            if (option == 1 && value == 2){
                value2 = dieNumber;
            }
        }else{
            if (value == 1){
                value1 = dieNumber;
            }

            if (value == 2){
                value2 = dieNumber;
            }
        }
    }
}
