import java.util.Scanner;

public class Dice {
    private int value1;
    private int value2;
    private int dieNumber;
    private int reroll;

    public Dice() {
        this.value1 = 0;
        this.value2 = 0;
        this.reroll = 2;
        this.dieNumber = 1;
    }

    Scanner scan = new Scanner(System.in);

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    public int getReroll() {
        return reroll;
    }

    public int getNumber() {
        return dieNumber;
    }

    // Earn a reroll every third turn
    public void getReroll(int turn, String name){
        if (turn % 3 == 0){
            System.out.println(name + " gains a reroll!");
            System.out.println("");
            reroll++;
        }

        if (reroll > 5){
            System.out.println("Max rerolls reached");
            reroll = 5;
        }
    }

    public int roll(String character){
        dieNumber = (int) (Math.random() * 6) + 1;
        System.out.println( character + " rolls a " + dieNumber);
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

    // Prompts message to reroll only when there are avaliable rerolls. Can only reroll once per die
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
