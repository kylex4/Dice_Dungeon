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
            this.name = "Shielder";
            this.health = 25;
            this.defense = 1;
            this.normalDefense = defense;
            this.special = false;
            this.damage = 0;
            this.counter = 2;
            this.dead = false;
            this.maxHealth = health;
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

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void attacked(int damage, String dealer){
        if (damage != -1) {
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
    // Random chance to do a different move, if the enemy is in the middle of a move do not launch an additional attack
    public void moveset(int number, boolean special, String target, int defense1){
        if (special){
            if (defense > 0) {
                counter--;
                System.out.println("Break through " + name + "'s defense before he unleashes his attack!");
                System.out.println("Remaining defense: " + getDefense());
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
        defense += normalDefense;
    }
    // Both attacks should state the damage considering the opponents' defense without changing their defense yet
    public int basicAttack1(String target, int defense1){
        Dice dice = new Dice();
        defense = dice.roll(name);
        System.out.println(name + "'s defense rose to " + (defense) + "!");
        damage = dice.roll(name);
        if (defense1 > damage){
            defense1 = damage;
        }
        System.out.println(name + " bashes his shield into " + target + ", dealing " + (damage - defense1) + " damage!");
        return damage;
    }

    public int basicAttack2(String target, int defense1){
        Dice dice = new Dice();
        damage = dice.roll(name) + dice.roll(name) - defense1;
        if (defense1 > damage){
            defense1 = damage;
        }
        System.out.println(name + " quickly rams his shield into " + target + ", dealing " + (damage - defense1) + " damage!");
        return damage;
    }

    // Dice determines amount of defense gained, if defense gained is above a certain amount then do a certain action
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
            damage = defense;
            defense = 1;
            if (defense1 > damage){
                defense1 = damage;
            }
            System.out.println(name + " isn't satisfied with his rolls, instantly attacking with his little defense, dealing " + (damage - defense1) + " damage!");
            return damage;
        }
    }

    // Acts as the enemy's ultimate attack, changing behavior based on the defense it still has
    public int bastion (int defense, String target, int defense1){
        if (defense > 0 && special){
            damage = (int) (defense * 1.5);
            defenseCap();
            if (defense1 > damage){
                defense1 = damage;
            }
            System.out.println(name + " launches himself up high into the air before slamming his shield down onto " + target + " dealing a whopping " + (damage - defense1) + " damage!");
            setDefense(0);
            special = false;
            return damage;
        }else{
            damage = defense - defense1;
            if (defense1 > damage){
                defense1 = damage;
            }
            setDefense(0);
            System.out.println(name + " hard smacks " + target + " with his shield, dealing " + (damage - defense1) + " damage");
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

    // Since this enemy can stack defense this will ensure it will never go above a certain limit in defense
    public void defenseCap(){
        if (defense > 18){
            defense = 18;
        }
    }
}
