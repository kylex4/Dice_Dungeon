import java.util.Scanner;

public class Dungeon {
    public Dungeon() {}
    Scanner scan = new Scanner(System.in);

    public void startGame(){
        System.out.print("What is your name:");
        String name = scan.nextLine();
        Player player = new Player(name);
        Enemy enemy = new Enemy();

        while (!player.checkDead() && !enemy.checkDead()){

        }
    }
}
