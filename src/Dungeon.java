import java.util.Scanner;

public class Dungeon {
    public Dungeon() {}
    Scanner scan = new Scanner(System.in);

    public void startGame(){
        System.out.print("What is your name: ");
        String name = scan.nextLine();
        Player player = new Player(name);
        Enemy enemy = new Enemy();
        Dice dice = new Dice();

        int count = 1;

        while (!player.checkDead() && !enemy.checkDead()){
            System.out.println("");
            System.out.println("•─────⋅☾Turn " + count + "☽⋅─────•");
            System.out.println("Health: " + player.getHealth() + "/" + player.getMaxHealth());
            System.out.println("");
            System.out.println("What will you do?");
            System.out.println("[1]. Attack " + enemy.getName());
            System.out.println("[2]. Observe " + enemy.getName() + "'s stats");
            int option = scan.nextInt();

            if (option == 1){
                System.out.println("What style do you want to do?");
                System.out.println("[1]. Balanced (Defense, Attack)");
                System.out.println("[2]. Aggressive (Attack, Attack)");
                scan.nextLine();
                String style = scan.nextLine();
                dice.roll(name);
                dice.reroll(1, player.getRerolls(), name);
                dice.roll(name);
                dice.reroll(2, player.getRerolls(), name);

                if (style.equals("1")){
                    int x = player.attack1(dice.getValue1(), dice.getValue2(), enemy.getName());
                    enemy.attacked(x);
                }

                if (style.equals("2")){
                    int x = player.attack2(dice.getValue1(), dice.getValue2(), enemy.getName());
                    enemy.attacked(x);
                }
                player.checkDead();
                System.out.println("");
                enemy.moveset(dice.rollMove(), enemy.isSpecial(), name);
                player.attacked(enemy.getDamage());
                enemy.checkDead();
                count++;
            }

            if (option == 2){
                System.out.println("Didn't finish yet");
            }
        }
    }
}
