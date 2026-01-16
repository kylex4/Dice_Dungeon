import java.util.Scanner;

public class Dungeon {
    private int count;
    private boolean gained;

    // Used to count turns
    public Dungeon() {
        this.count = 1;
        this.gained = false;
    }

    Scanner scan = new Scanner(System.in);

    public void startGame(){
        System.out.print("What is your name: ");
        String name = scan.nextLine();
        Player player = new Player(name);
        Enemy enemy = new Enemy();
        Dice dice = new Dice();

        while (!player.checkDead() && !enemy.checkDead()){
            // Ensures damage values won't carry over if an enemy or player does something that doesn't change damage value
            player.setDamage(-1);
            enemy.setDamage(-1);

            System.out.println("");
            System.out.println("•─────⋅☾Turn " + count + "☽⋅─────•");
            System.out.println("Health: " + player.getHealth() + "/" + player.getMaxHealth());
            System.out.println("Defense: " + player.getDefense());
            System.out.println("");
            dice.gainReroll(count, name, gained);
            if (count % 3 == 0 && !gained){
                gained = true;
            }
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
                System.out.println("");
                dice.roll(name);
                dice.reroll(1, dice.getReroll(), name);
                dice.roll(name);
                System.out.println("");
                dice.reroll(2, dice.getReroll(), name);

                if (style.equals("1")){
                    int x = player.attack1(dice.getValue1(), dice.getValue2(), enemy.getName(), enemy.getDefense());
                    enemy.attacked(x, name);
                }

                if (style.equals("2")){
                    int x = player.attack2(dice.getValue1(), dice.getValue2(), enemy.getName(), enemy.getDefense());
                    enemy.attacked(x, name);
                }
                enemy.checkDead();
                if (enemy.checkDead()){
                    System.out.println("You've won!");
                    break;
                }
                System.out.println("");
                enemy.moveset(dice.rollMove(), enemy.isSpecial(), name, player.getDefense());
                enemy.defenseCap();
                player.attacked(enemy.getDamage(), enemy.getName(), enemy.isSpecial());
                player.checkDead();
                if (player.checkDead()){
                    System.out.println("You lose");
                }
                System.out.println("Press enter to continue.");
                String enter = scan.nextLine();

                count++;
                gained = false;
            }

            if (option == 2){
                System.out.println("------" + enemy.getName() + "'s Stats------");
                System.out.println("Health: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
                System.out.println("Defense: " + enemy.getDefense());
                scan.nextLine();
                System.out.println("Press enter to return to actions.");
                String enter = scan.nextLine();
            }
        }
    }
}
