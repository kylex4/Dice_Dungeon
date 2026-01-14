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

    public int roll(String character){
        dieNumber = (int) (Math.random() * 6) + 1;
        System.out.println( character + " rolls a " + dieNumber);
        return dieNumber;
    }

    public void reroll(int value, int rerolls, String character){
        if (rerolls > 0){
            System.out.println("Would you like to reroll your die?\n[1]. Reroll (Current Rerolls: " + rerolls + "\n[2]. Keep Current Die Value (" + dieNumber + ")");
            int option = scan.nextInt();

            if (option == 1 && value == 1){
                value1 = roll(character);
            }

            if (option == 1 && value == 2){
                value2 = roll(character);
            }

            if (option == 2 && value == 1){
                value1 = dieNumber;
            }

            if (option == 2 && value == 2){
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
