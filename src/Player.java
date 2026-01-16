public class Player {
    private String name;
    private int health;
    private int defense;
    private int damage;
    private boolean dead;
    private int maxHealth;
    private int value1;
    private int value2;

//    public int getRerolls() {
//        return rerolls;
//    }

    public Player(String name){
        this.name = name;
        this.health = 30;
        this.defense = 0;
        this.damage = 0;
        this.dead = false;
        this.maxHealth = health;
        this.value1 = 0;
        this.value2 = 0;

    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    public int getDefense() {
        return defense;
    }

    public void attacked(int damage, String dealer, boolean special){
        if (!special) {

            if (damage > defense) {
                health -= damage - defense;
            } else {
                System.out.println(name + " completely blocks " + dealer + "'s attack!");
            }

            if (defense <= damage) {
                defense = 0;
            } else {
                defense -= damage;
            }
        }
    }

    public int attack1 (int value1, int value2, String target, int defense2){
        if (value1 == value2){
            System.out.println(name + " perfectly balances their defense and attack as all things should be...");
            System.out.println("Defense and attack increase by an additional 2 for this turn!");
            value1 += 2;
            value2 += 2;
        }

        damage = value1 - defense2;
        defense = value2;
        if (damage < 0){
            damage = 0;
        }
        System.out.println(name + "'s defense becomes " + defense + "!");
        System.out.println(name + " swings their sword at " + target + " dealing " + damage + " damage!");
        return damage;
    }

    public int attack2 (int value1, int value2, String target, int defense2){
        if (value1 == value2){
            System.out.println(name + " perfectly balances their attack, increasing damage by an additional 4!");
            value1 += 2;
            value2 += 2;
        }
        damage = value1 + value2 - defense2;
        if (damage < 0){
            damage = 0;
        }
        System.out.println(name + " strikes their sword at " + target + " dealing " + damage + " damage!");
        return damage;
    }

    public boolean checkDead(){
        if (health <= 0){
            health = 0;
            dead = true;
        }
        return dead;
    }




}
