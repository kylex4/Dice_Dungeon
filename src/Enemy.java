public class Enemy {
    private String name;
    private int health;
    private int defense;
    private int normalDefense;
    private boolean special;
    private int damage;
    private int counter;
    private boolean dead;

    public Enemy(){
//        if (number == 1 || number == 2){
            this.name = "Shielder";
            this.health = 50;
            this.defense = 1;
            this.normalDefense = defense;
            this.special = false;
            this.damage = 0;
            this.counter = 2;
            this.dead = false;
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

    public void attacked(int damage){
        health -= damage;
    }

    public void moveset(int number, boolean special, String target){
        if (special){
            if (defense >= 1) {
                counter--;
                if (counter == 0) {
                    bastion(defense, target);
                    counter = 2;
                }
            }else{
                special = false;
                normalDefense = 0;
                health -= 5;
                System.out.println(name + " defense breaks early, permanently decreasing defense and loses 5 health!");
            }
        }else if (number == 1 || number == 2){
            basicAttack1(target);
        }else if (number == 3 || number == 4 || number == 5){
            basicAttack2(target);
        }else if (number == 6){
            defend();
        }


    }

    public void basicAttack1(String target){
        Dice dice = new Dice();
        defense += dice.roll(name);
        System.out.println(name + "'s defense rose to " + defense + "!");
        damage = dice.roll(name);
        System.out.println(name + " bashes his shield into " + target + ", dealing " + damage + " damage!");
    }

    public int basicAttack2(String target){
        Dice dice = new Dice();
        damage = dice.roll(name) + dice.roll(name);
        System.out.println(name + " quickly rams his shield into " + target + ", dealing " + damage + " damage!");
        return damage;
    }

    public int defend(){
        Dice dice = new Dice();
        defense += (int) (dice.roll(name) + dice.roll(name) * 1.5);
        System.out.println(name + " further enhances his rolls!");

        if (defense >= 12){
            System.out.println(name + " applies a massive amount of shield! Focus on shredding defense to lessen damage from " + name);
            special = true;
            return 0;
        }else if (defense > 6){
            System.out.println(name + " quickly applies a shield, increasing defense to " + defense);
            return 0;
        }else{
            damage = defense;
            defense = 1;
            System.out.println(name + " isn't satisfied with his rolls, instantly attacking with his little defense, dealing " + defense + " damage!");
            return damage;
        }
    }

    public int bastion (int defense, String target){
        if (defense > 0 && special){
            damage = (int) (defense * 1.5);
            System.out.println(name + " launches himself up high into the air before slamming his shield down onto " + target + " dealing a whopping " + damage + "damage !");
            defense = normalDefense;
            special = false;
            return damage;
        }else{
            damage = defense;
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
}
