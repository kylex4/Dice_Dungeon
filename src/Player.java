public class Player {
    private String name;
    private int health;
    private int defense;
    private int rerolls;
    private int damage;
    private boolean dead;

    public int getRerolls() {
        return rerolls;
    }

    public Player(String name){
        this.name = name;
        this.health = 30;
        this.defense = 0;
        this.rerolls = 2;
        this.damage = 0;
        this.dead = false;

    }

    public void attack1 (int value1, int value2, String target){
        if (value1 == value2){
            System.out.println(name + " perfectly balances their defense and attack as all things should be...");
            System.out.println("Defense and attack increase by 1 for this turn!");
            value1 += 1;
            value2 += 1;
        }

        damage = value1;
        defense += value2;
    }

    public void attack2 (int value1, int value2, String target){
        damage = value1 + value2;
        System.out.println(name + "swings his sword at" + target + " dealing " + damage + " damage!");
    }

    public boolean checkDead(){
        if (health <= 0){
            health = 0;
            dead = true;
        }
        return dead;
    }




}
