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

    public int getReroll() {
        return reroll;
    }

    public int getNumber() {
        return dieNumber;
    }

    public void normalDice(){
        dieNumber = (int) (Math.random() * 6) + 1;



    }

    public int reroll(){
        if (reroll > 0){
            System.out.println("Would you like to reroll your die?\n[1]. Reroll (Current Rerolls: " + getReroll() + "\n[2]. Keep Current Die Value (" + getNumber() + ")");
            int option = scan.nextInt();

        }
    }
}
