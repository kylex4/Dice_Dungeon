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

    public void moveset(int number, boolean special, String target){
        if (special){
            if (defense >= 1) {
                counter--;
                if (counter == 0) {
                    bastion(defense, target);
                    counter = 2;
                }else{
                    special = false;
                    normalDefense = 0;
                    health -= 5;
                }
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
    }

    public void basicAttack2(String target){
        Dice dice = new Dice();
        damage = dice.roll(name) + dice.roll(name);
    }

    public void defend(){
        Dice dice = new Dice();
        defense += (int) (dice.roll(name) + dice.roll(name) * 1.5);
        System.out.println(name + " further enhances his rolls!");

        if (defense >= 12){
            System.out.println(name + " applies a massive amount of shield! Focus on shredding defense to lessen damage from " + name);
            special = true;
        }else if (defense > 6){
            System.out.println(name + " quickly applies a shield, increasing defense to " + defense);
        }else{
            System.out.println(name + " isn't satisfied with his rolls, instantly attacking with his little defense!");
        }
    }

    public void bastion (int defense, String target){
        if (defense > 0 && special){
            damage = (int) (defense * 1.5);
            System.out.println(name + " launches himself up high into the air before slamming his shield down onto " + target + " dealing a whopping " + damage + "damage !");
            defense = normalDefense;
            special = false;
        }else{
            damage = defense;
            System.out.println(name + " hard smacks " + target + " with his shield, dealing " + damage + " damage");
            defense = normalDefense;
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
