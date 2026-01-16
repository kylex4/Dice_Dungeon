public class Enemy {
    private String name;
    private int health;
    private int defense;
    private int normalDefense;
    private boolean special;
    private int damage;
    private int counter;
    private boolean dead;
    private int maxHealth;

    public Enemy(){
//        if (number == 1 || number == 2){
            this.name = "Shielder";
            this.health = 40;
            this.defense = 1;
            this.normalDefense = defense;
            this.special = false;
            this.damage = 0;
            this.counter = 2;
            this.dead = false;
            this.maxHealth = health;
//        }
    }

    public int getHealth() {
        return health;
    }

    public int getDefense() {
        return defense;
    }

    public String getName() {
        return name;
    }

    public boolean isSpecial() {
        return special;
    }

    public int getDamage() {
        return damage;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void attacked(int damage, String dealer){
        if (!special){

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
    // Random chance to do a different move, if the enemy is in a middle of a move do not launch an additional attack
    public void moveset(int number, boolean special, String target, int defense1){
        if (special){
            if (defense >= 1) {
                counter--;
                System.out.println("Break through " + name + "'s defense before he unleashes his attack!");
                System.out.println(counter + " turn(s) remaining...");
                if (counter == 0) {
                    bastion(defense, target, defense1);
                    counter = 2;
                }
            }else{
                special = false;
                normalDefense = 0;
                health -= 8;
                System.out.println(name + " defense breaks early, permanently decreasing defense and loses 8 health!");
            }
        }else if (number == 1 || number == 2){
            basicAttack1(target, defense1);
        }else if (number == 3 || number == 4){
            basicAttack2(target, defense1);
        }else if (number == 5 || number == 6){
            defend(defense1);
        }


    }

    public void basicAttack1(String target, int defense1){
        Dice dice = new Dice();
        defense = dice.roll(name);
        System.out.println(name + "'s defense rose to " + defense + "!");
        damage = dice.roll(name) - defense1;
        if (damage < 0){
            damage = 0;
        }
        System.out.println(name + " bashes his shield into " + target + ", dealing " + damage + " damage!");
    }

    public int basicAttack2(String target, int defense1){
        Dice dice = new Dice();
        damage = dice.roll(name) + dice.roll(name) - defense1;
        if (damage < 0){
            damage = 0;
        }
        System.out.println(name + " quickly rams his shield into " + target + ", dealing " + damage + " damage!");
        return damage;
    }

    public int defend(int defense1){
        Dice dice = new Dice();
        defense = (int) (dice.roll(name) + dice.roll(name) * 1.5);
        System.out.println(name + " further enhances his rolls!");

        if (defense >= 9){
            System.out.println(name + " applies a massive amount of shield! Focus on shredding defense to lessen damage from " + name);
            special = true;
            return 0;
        }else if (defense > 6){
            System.out.println(name + " quickly applies a shield, increasing defense to " + defense);
            return 0;
        }else{
            damage = defense - defense1;
            defense = 1;
            System.out.println(name + " isn't satisfied with his rolls, instantly attacking with his little defense, dealing " + defense + " damage!");
            return damage;
        }
    }

    // Acts as the enemy's ultimate attack, changing behavior based on the defense it still has
    public int bastion (int defense, String target, int defense1){
        if (defense > 0 && special){
            damage = (int) (defense * 1.5) - defense1;
            if (damage < 0){
                damage = 0;
            }
            System.out.println(name + " launches himself up high into the air before slamming his shield down onto " + target + " dealing a whopping " + damage + " damage!");
            defense = normalDefense;
            special = false;
            return damage;
        }else{
            damage = defense - defense1;
            if (damage < 0){
                damage = 0;
            }
            System.out.println(name + " hard smacks " + target + " with his shield, dealing " + damage + " damage");
            defense = normalDefense;
            return damage;
        }
    }

    public boolean checkDead(){
        if (health <= 0){
            health = 0;
            dead = true;
        }
        return dead;
    }

    public void defenseCap(){
        if (defense > 18){
            defense = 18;
        }
    }
}
